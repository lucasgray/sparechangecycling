package com.sparechangecycling.daos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BikesDAOTest {

	public static void main(String[] args) throws ParseException {
		BikesDAO dao = new BikesDAO();
		
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm/dd/yyyy");
		Date date = sdf.parse("04/11/1985");
		
		//List li = dao.getBikesPostedAfterDate(date);
		
		//assert(li.size() > 0);
		
		//System.out.println("found some bikes: " +li.size());
		
		
	}
}
