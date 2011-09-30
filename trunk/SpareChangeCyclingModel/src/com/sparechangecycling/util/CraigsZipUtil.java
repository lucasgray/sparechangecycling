package com.sparechangecycling.util;

import java.util.HashMap;
import java.util.Map;

public class CraigsZipUtil {
	
	//TODO: do we have to finaggle for each craigs city or can
	//we automate?

	private static Map<String,StateCityZipBean> theZips;
	
	static {
		theZips = new HashMap<String,StateCityZipBean>();
		
		theZips.put("madison", new StateCityZipBean("WI","Madison","53786"));
		theZips.put("milwaukee", new StateCityZipBean("WI","Milwaukee","53220"));
		theZips.put("appleton", new StateCityZipBean("WI","Appleton","54914"));
		theZips.put("lacrosse", new StateCityZipBean("WI","Lacrosse","54602"));
		theZips.put("duluth", new StateCityZipBean("WI","Duluth","55812"));
		theZips.put("eauclaire", new StateCityZipBean("WI","Eau Claire","54702"));
		theZips.put("greenbay", new StateCityZipBean("WI","Green Bay","54306"));
		theZips.put("janesville", new StateCityZipBean("WI","Janesville","53546"));
		theZips.put("racine", new StateCityZipBean("WI","Racine","53405"));
		theZips.put("sheboygan", new StateCityZipBean("WI","Sheboygan","53082"));
		theZips.put("wausau", new StateCityZipBean("WI","Wausau","54402"));
		
		theZips.put("ames", new StateCityZipBean("IA","Ames","50012"));
		theZips.put("cedarrapids", new StateCityZipBean("IA","Cedar Rapids","52401"));
		theZips.put("desmoines", new StateCityZipBean("IA","Des Moines","50305"));
		theZips.put("dubuque", new StateCityZipBean("IA","Dubuque","52003"));
		theZips.put("iowacity", new StateCityZipBean("IA","Iowa City","52243"));
		theZips.put("omaha", new StateCityZipBean("IA","Omaha","68139"));
		theZips.put("quadcities", new StateCityZipBean("IA","Quad Cities","52808"));
		theZips.put("siouxcity", new StateCityZipBean("IA","Sioux City","51101"));
		theZips.put("waterloo", new StateCityZipBean("IA","Waterloo","50704"));
		
		theZips.put("bn", new StateCityZipBean("IL","Bloomington Normal","61701"));
		theZips.put("chambana", new StateCityZipBean("IL","Urbana Champaign","61821"));
		theZips.put("chicago", new StateCityZipBean("IL","Chicago","60601"));
		theZips.put("decatur", new StateCityZipBean("IL","Decatur","62522"));
		theZips.put("mattoon", new StateCityZipBean("IL","Mattoon-Charleston","61938"));
		theZips.put("quadcities", new StateCityZipBean("IL","Quad Cities","61201"));
		theZips.put("rockford", new StateCityZipBean("IL","Rockford","61101"));
		theZips.put("carbondale", new StateCityZipBean("IL","Carbondale","62901"));
		theZips.put("springfieldil", new StateCityZipBean("IL","Springfield","62701"));
		theZips.put("stlouis", new StateCityZipBean("IL","St. Louis","63101"));
		theZips.put("quincy", new StateCityZipBean("IL","Quincy","62301"));
	}

	public static StateCityZipBean getZipByCity(String city) {	
		return theZips.get(city);
	}

	public static Map<String, StateCityZipBean> getTheZips() {
		return theZips;
	}

	public static void setTheZips(Map<String, StateCityZipBean> theZips) {
		CraigsZipUtil.theZips = theZips;
	}
	

}
