package com.sparechangecycling.helper;

import java.util.Calendar;
import java.util.TreeMap;

import com.sparechangecycling.dao.CraigsDAO;

/**
 * Determines if the current craigslist community
 * should be read at this time, and when it should be
 * ran next.
 */
public class FeedTimer {
	
	private static final int SLOWEST = 4;
	private static TreeMap<Integer, Integer> map;
	
	static {
		// >10 = every hour, 5-10 = every 2 hours, 2-4 = every 3 hours, <=1 = every 4 hours
		map = new TreeMap<Integer, Integer>();
		map.put(Integer.MIN_VALUE, SLOWEST);
		map.put(5, 3);
		map.put(10, 2);
		map.put(Integer.MAX_VALUE, 1);
	}
	
	private CraigsDAO dao;
	private String community;
	
	public FeedTimer(CraigsDAO dao, String community) {
		this.dao = dao;
		this.community = community;
	}

	public boolean read() {
		int hour = getCalendar().get(Calendar.HOUR_OF_DAY);
		int rate = dao.getRate(community);
		return hour % rate == 0;
	}

	protected Calendar getCalendar() {
		return Calendar.getInstance();
	}
	
	public void updateRate(int newPosts) {
		Integer value = map.floorEntry(newPosts).getValue();
		dao.updateRate(value, community);
	}
	
	

}
