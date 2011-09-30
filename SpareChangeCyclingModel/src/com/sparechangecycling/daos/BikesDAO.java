package com.sparechangecycling.daos;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.pojos.AdType;
import com.sparechangecycling.pojos.CraigsBikeAd;
import com.sparechangecycling.pojos.SccBikeAd;
import com.sparechangecycling.pojos.ZipCode;
import com.sparechangecycling.property.PropertyManager;
import com.sparechangecycling.util.SearchPreferences;

public class BikesDAO extends SccDAO {

	private static final Logger log = LoggerFactory.getLogger(BikesDAO.class);
	
	private static final int MAX_RESULTS = new PropertyManager("scc_files.properties")
		.getInt("scc.max.ad.results");
	
	public List<SccBikeAd> getSccBikes(SearchPreferences sp) throws SQLException, ParseException {	
		return getBikesHelper(sp, SccBikeAd.class, MAX_RESULTS);
	}
	public List<CraigsBikeAd> getCraigsBikes(SearchPreferences sp) throws SQLException, ParseException {
		return getBikesHelper(sp, CraigsBikeAd.class, MAX_RESULTS);
	}
	public List<SccBikeAd> getLatestSccForIndex(SearchPreferences sp) throws SQLException, ParseException {
		return getBikesHelper(sp, SccBikeAd.class, 5);
	}
	
	/**
	 * max is the max amt of ads to return
	 * 
	 * @param sp
	 * @param fromWhere
	 * @param max
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> getBikesHelper(SearchPreferences sp, Class<T> clazz, int max) throws SQLException, ParseException{
		
		Session session = getSession();
		Criteria criteria = session.createCriteria(clazz);
		
		criteria.addOrder(Order.desc("date"));
		
		criteria.setMaxResults(max);
		
		List<AdType> type = new ArrayList<AdType>(sp.getType());
		
		type.remove(AdType.ALL);
		
		if (type != null && !(type.size() == 0)){
			List<String> tmpLst = new ArrayList<String>();
			for (AdType t: type) {
					tmpLst.add(t.toString());
					log.debug(t.toString());
			}
			criteria.add(Restrictions.in("type", tmpLst));
		}
		if (sp.getLowPrice() != null && sp.getLowPrice() > 0) {
			criteria.add(Expression.ge("price",new BigDecimal(sp.getLowPrice())));
		}
		if (sp.getHighPrice() != null && sp.getHighPrice() > 0) {
			criteria.add(Expression.le("price",new BigDecimal(sp.getHighPrice())));
		}
		if (sp.getTextMatch() != null && !sp.getTextMatch().trim().equals("")) {
			String like = "%"+sp.getTextMatch()+"%";
			if (clazz == CraigsBikeAd.class) {
				criteria.add(Restrictions.or(
						Restrictions.ilike("title", like),
						Restrictions.ilike("detail", like)));
			} else {
				criteria.add(Restrictions.or(
						Restrictions.or(
								Restrictions.ilike("firstTitle", like),
								Restrictions.ilike("secondTitle", like)
						),
						Restrictions.ilike("description", like)
				));
			}
		}
		
		if (sp.getZip() != null && !sp.getZip().equals("") /*&& sp.getDistance() > 0*/){
			
			if (sp.getDistance() == null || sp.getDistance() == 0) {
				sp.setDistance(10);
			}
			
			//we can fudge the craigs distance stuff.  the zip codes suck anyway
			Integer distance = sp.getDistance();
			if (clazz == CraigsBikeAd.class) {
				distance +=25;
			}
			
			PreparedStatement st = session.connection().prepareStatement("call GetNearbyZipCodes(?,?);");
			st.setString(1, sp.getZip());
			st.setInt(2, sp.getDistance());
			ResultSet rslts = st.executeQuery();
			
			List<ZipCode> zipCodes = new ArrayList<ZipCode>();
			while(rslts.next()) {
				zipCodes.add(new ZipCode(rslts.getString(1)));
			}
			st.close();
			criteria.add(Restrictions.in("zip", zipCodes));
		}
		
		return criteria.list();
		
	}


	
}
