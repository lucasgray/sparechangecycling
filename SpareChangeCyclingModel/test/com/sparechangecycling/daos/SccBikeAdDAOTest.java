package com.sparechangecycling.daos;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.sparechangecycling.hibernate.HibernateSessionFactory;
import com.sparechangecycling.pojos.AdType;
import com.sparechangecycling.pojos.Pic;
import com.sparechangecycling.pojos.SccBikeAd;

public class SccBikeAdDAOTest {

	public static void main(String[] args) throws ParseException, NoSuchAlgorithmException, UnsupportedEncodingException, MalformedURLException {
		SccBikeAdDAO dao = new SccBikeAdDAO();

		SccBikeAd ad = new SccBikeAd();
		ad.setDescription("this is mah bike");
		ad.setFirstTitle("jamis");
		ad.setSecondTitle("citizen");
		ad.setYear("2007");
		ad.setType(AdType.HYBRID.toString());
		ad.setPrice(new BigDecimal(123.45));
		Pic pic = new Pic("http://localhost/images/thumbs/Firefox_wallpaper_tn.png");
		
		HibernateSessionFactory.getSession().save(pic);
		List li = new ArrayList();
		li.add(pic);
		ad.setPics(li);
		dao.addAnAd(1l, ad);
	}
}
