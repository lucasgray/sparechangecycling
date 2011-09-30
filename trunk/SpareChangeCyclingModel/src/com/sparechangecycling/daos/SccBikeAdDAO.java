package com.sparechangecycling.daos;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.pojos.AdType;
import com.sparechangecycling.pojos.Pic;
import com.sparechangecycling.pojos.SccBikeAd;
import com.sparechangecycling.pojos.Thumb;
import com.sparechangecycling.pojos.User;
import com.sparechangecycling.util.ImageHandler;

public class SccBikeAdDAO extends SccDAO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void addAnAd(Long userId, SccBikeAd ad) {
		
		Session session = getSession();

		try {
			User user = (User) session.createCriteria(User.class).add(
					Restrictions.eq("id", userId)).uniqueResult();
			
			for (Pic p: ad.getPics()) {
				session.save(p);
			}
			
			for (Thumb t: ad.getThumbs()) {
				session.save(t);
			}
			
			ad.setUser(user);
			session.save(ad);
			
			user.addOneAd(ad);
			session.update(user);
			

		} catch (Exception e) {
			logger.info("prob saving ad: "+e.getMessage());
		}
	}
	
	public void removeAnAd(Long userId, Long bikeId) {
		Session session = getSession();

	
		logger.debug("removing ad: "+bikeId+" by userid "+userId);
		try {
			SccBikeAd bike = (SccBikeAd) session.createCriteria(SccBikeAd.class).add(
					Restrictions.eq("id", bikeId)).uniqueResult();
			
			User user = bike.getUser();
			assert(user.getId() == userId);
			user.getAds().remove(bike);
			
//			String thumbUrl = bike.getThumbs().get(0).getHref();
//			String picUrl = bike.getPics().get(0).getHref();
			
//			ImageHandler.deleteImage(ImageHandler.convertApacheUrlToFilepath(thumbUrl, true));
//			ImageHandler.deleteImage(ImageHandler.convertApacheUrlToFilepath(picUrl, false));
			
			session.delete(bike);
			session.merge(user);

			
		} catch (Exception e) {

		}
	}
//	public List<SccBikeAd> getAdsById(Long userId) {
//		Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
//		Transaction tx = session.beginTransaction();
//		User user = (User) session.createCriteria(User.class).add(
//				Restrictions.eq("id", userId)).uniqueResult();
//		
//		List li = new ArrayList();
//		li.addAll(user.getAds());
//		Collections.reverse(li);
//
//		return li;
//	}

	public SccBikeAd getSingleAdById(Long adId) {
		
		Session session = getSession();

		SccBikeAd ad = (SccBikeAd) session.createCriteria(SccBikeAd.class).add(
				Restrictions.eq("id", adId)).uniqueResult();

		return ad;
	}

	public void mergeAd(SccBikeAd ad) {
		SccBikeAd oldAd = getSingleAdById(ad.getId());
		Session session = getSession();
		
		ad.setDate(oldAd.getDate());
		ad.setUser(oldAd.getUser());
		ad.setZip(oldAd.getZip());
		
		if (ad.getPics().size() == 0) {
			ad.setPics(oldAd.getPics());
			ad.setThumbs(oldAd.getThumbs());
		}
		if (AdType.isPart(oldAd.getType())) {
			ad.setSecondTitle(oldAd.getSecondTitle());
		}
		
		for (Pic p: ad.getPics()) {
			session.save(p);
		}
		
		for (Thumb t: ad.getThumbs()) {
			session.save(t);
		}
		
		session.evict(oldAd);
		session.update(ad);

	}
}
