package com.sparechangecycling.helper;

import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sparechangecycling.dao.CraigsDAO;

public class FeedTimerTest {
	
	private static final String COMMUNITY = "madison";
	private FeedTimer timer;
	private CraigsDAO dao;
	private int prevRate;
	private Calendar mockCalendar;
	
	@Before
	public void setUp() {
		mockCalendar = createMock(Calendar.class);
		dao = new CraigsDAO();
		prevRate = dao.getRate(COMMUNITY);
		timer = new FeedTimer(dao, COMMUNITY) {
			@Override
			protected Calendar getCalendar() {
				return mockCalendar;
			}
		};
	}
	
	@After
	public void tearDown() {
		dao.updateRate(prevRate, COMMUNITY);
	}
	
	@Test
	public void testUpdateRate() {
		assertEquals(1, dao.getRate(COMMUNITY));
		timer.updateRate(0);
		assertEquals(4, dao.getRate(COMMUNITY));
		timer.updateRate(1);
		assertEquals(4, dao.getRate(COMMUNITY));
		timer.updateRate(5);
		assertEquals(3, dao.getRate(COMMUNITY));
		timer.updateRate(10);
		assertEquals(2, dao.getRate(COMMUNITY));
	}
	
	@Test
	public void testReadRate1() {
		dao.updateRate(1, COMMUNITY);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(0);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(1);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(2);
		replay(mockCalendar);
		assertTrue(timer.read());
		assertTrue(timer.read());
		assertTrue(timer.read());
		verify(mockCalendar);
	}
	
	@Test
	public void testReadRate2() {
		dao.updateRate(2, COMMUNITY);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(0);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(1);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(2);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(3);
		replay(mockCalendar);
		assertTrue(timer.read());
		assertFalse(timer.read());
		assertTrue(timer.read());
		assertFalse(timer.read());
		verify(mockCalendar);
	}
	
	@Test
	public void testReadRate3() {
		dao.updateRate(3, COMMUNITY);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(0);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(1);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(2);
		expect(mockCalendar.get(Calendar.HOUR_OF_DAY)).andReturn(3);
		replay(mockCalendar);
		assertTrue(timer.read());
		assertFalse(timer.read());
		assertFalse(timer.read());
		assertTrue(timer.read());
		verify(mockCalendar);
	}
	
	

}
