package com.sparechangecycling.main;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.dao.CraigsDAO;
import com.sparechangecycling.helper.CraigThread;
import com.sparechangecycling.helper.FeedTimer;

public class CraigsRssCrawler {

	private static final int DEFAULT_THREAD_POOL_SIZE = 3;
	private static final Logger logger = LoggerFactory.getLogger(CraigsRssCrawler.class);
	private CraigsDAO dao;
	private static int poolSize;
	private static boolean ignoreTimer;
	
	public static void main(String[] args) throws Exception {
		if(args.length > 0) {
			poolSize = Integer.valueOf(args[0]);
			ignoreTimer = Boolean.parseBoolean(args[1]);
		} else {
			poolSize = DEFAULT_THREAD_POOL_SIZE;
		}
		new CraigsRssCrawler().go();
	}
	
	public void go() throws Exception {
		dao = new CraigsDAO();
		long start = System.currentTimeMillis();
		List<String> commies = dao.getCommunityNames();
		Collections.sort(commies);
		ExecutorService executor = Executors.newFixedThreadPool(poolSize);
		for (String commie : commies) {
			FeedTimer timer = new FeedTimer(dao, commie);
			if(ignoreTimer || timer.read()) {
				executor.submit(new CraigThread(dao, commie, timer));
			}
		}
		executor.shutdown();
		executor.awaitTermination(60, TimeUnit.MINUTES);
		logger.info("Finished in " + (System.currentTimeMillis() - start) + " milliseconds.");
	}
	
}
