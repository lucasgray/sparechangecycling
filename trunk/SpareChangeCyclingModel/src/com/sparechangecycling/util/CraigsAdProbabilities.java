package com.sparechangecycling.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.sparechangecycling.pojos.AdType;

public class CraigsAdProbabilities {
	
	private static Map<String,Double> probs;

	static {
		probs = new HashMap<String,Double>();
		for (AdType type:AdType.values()) {
			probs.put(type.toString(), 0d);
		}
	}

	// TODO do this on construction if title and desc are passed!
	public static synchronized String guessType(String title, String detail) {
		
		filterWantAd(title,detail);
		
		Double mtnBike = probs.get(AdType.MTN.toString());
		if (title.matches(".*(?i)mountain.*")) {
			mtnBike++;
		}
		if (detail.matches(".*(?i)mountain.*")) {
			mtnBike++;
		}
		if (title.matches(".*(?i)mtn.*")) {
			mtnBike++;
		}
		if (detail.matches(".*(?i)mtn.*")) {
			mtnBike++;
		}
		if (title.matches(".*(?i)mt\\..*")) {
			mtnBike++;
		}
		if (detail.matches(".*(?i)mt\\..*")) {
			mtnBike++;
		}
		if (title.matches(".*(?i)xc.*")) {
			mtnBike++;
		}
		if (detail.matches(".*(?i)xc.*")) {
			mtnBike++;
		}
		probs.put(AdType.MTN.toString(), mtnBike);

		Double roadBike = probs.get(AdType.ROAD.toString());
		if (title.matches(".*(?i)touring.*")) {
			roadBike = roadBike +2;
		}
		if (detail.matches(".*(?i)touring.*")) {
			roadBike++;
		}
		if (title.matches(".*(?i)road.*")) {
			roadBike = roadBike +2;
		}
		if (detail.matches(".*(?i)road.*")) {
			roadBike++;
		}
		if (title.matches(".*(?i)road bike.*")) {
			roadBike = roadBike +3;
		}
		if (title.matches(".*(?i)carbon.*")) {
			roadBike++;
		}
		if (detail.matches(".*(?i)carbon.*")) {
			roadBike++;
		}
		if (title.matches(".*(?i)raleigh.*")) {
			roadBike = roadBike +2;
		}
		if (title.matches(".*(?i)world sport.*")) {
			roadBike = roadBike +2;
		}
		probs.put(AdType.ROAD.toString(), roadBike);
		
		Double fixieBike = probs.get(AdType.FIXIE.toString());
		if (title.matches(".*(?i)fixed.*")) {
			fixieBike++;
		}
		if (detail.matches(".*(?i)fixed.*")) {
			fixieBike++;
		}
		if (title.matches(".*(?i)fixie.*")) {
			fixieBike++;
		}
		if (detail.matches(".*(?i)fixie.*")) {
			fixieBike++;
		}
		if (title.matches(".*(?i)single speed.*")) {
			fixieBike++;
		}
		if (detail.matches(".*(?i)single speed.*")) {
			fixieBike++;
		}
		probs.put(AdType.FIXIE.toString(), fixieBike);
		
		Double bmxBike = probs.get(AdType.BMX.toString());
		if (title.matches(".*(?i)bmx.*")) {
			bmxBike++;
		}
		if (detail.matches(".*(?i)bmx.*")) {
			bmxBike++;
		}
		if (title.matches(".*(?i)trick.*")) {
			bmxBike++;
		}
		if (detail.matches(".*(?i)trick.*")) {
			bmxBike++;
		}
		probs.put(AdType.BMX.toString(), bmxBike);

		Double triBike = probs.get(AdType.TRI.toString());
		if (title.matches(".*(?i)tri bike.*")) {
			triBike++;
		}
		if (detail.matches(".*(?i)tri bike.*")) {
			triBike++;
		}
		if (title.matches(".*(?i)triathlon.*")) {
			triBike++;
		}
		if (detail.matches(".*(?i)triathlon.*")) {
			triBike++;
		}
		probs.put(AdType.TRI.toString(), triBike);

		Double cruiserBike = probs.get(AdType.CRUISER.toString());
		if (title.matches(".*(?i)cruiser.*")) {
			cruiserBike++;
		}
		if (detail.matches(".*(?i)cruiser.*")) {
			cruiserBike++;
		}
		probs.put(AdType.CRUISER.toString(), cruiserBike);

		Double kidsBike = probs.get(AdType.KIDS.toString());
		if (title.matches(".*(?i)mongoose.*")) {
			kidsBike++;
		}
		if (detail.matches(".*(?i)mongoose.*")) {
			kidsBike++;
		}
		if (title.matches(".*(?i)kids.*")) {
			kidsBike++;
		}
		if (detail.matches(".*(?i)kids.*")) {
			kidsBike++;
		}
		if (title.matches(".*(?i)boys.*")) {
			kidsBike++;
		}
		if (detail.matches(".*(?i)boys.*")) {
			kidsBike++;
		}
		if (title.matches(".*(?i)girls.*")) {
			kidsBike = kidsBike + .5;
		}
		if (detail.matches(".*(?i)girls.*")) {
			kidsBike = kidsBike + .5;
		}
		if (title.matches(".*(?i)chopper.*")) {
			kidsBike++;
		}
		if (detail.matches(".*(?i)chopper.*")) {
			kidsBike++;
		}
		if (title.matches(".*(?i)childs.*")) {
			kidsBike++;
		}
		if (detail.matches(".*(?i)childs.*")) {
			kidsBike++;
		}
		probs.put(AdType.KIDS.toString(), kidsBike);

		if (title.matches(".*(?i)bmx.*")) {
			bmxBike++;
		}
		if (detail.matches(".*(?i)bmx.*")) {
			bmxBike++;
		}
		if (title.matches(".*(?i)freestyle.*")) {
			bmxBike++;
		}
		if (detail.matches(".*(?i)freestyle.*")) {
			bmxBike++;
		}
		probs.put(AdType.BMX.toString(), bmxBike);

		Double hybridBike = probs.get(AdType.HYBRID.toString());
		if (title.matches(".*(?i)hybrid.*")) {
			hybridBike++;
		}
		if (detail.matches(".*(?i)hybrid.*")) {
			hybridBike++;
		}
		if (title.matches(".*(?i)cyprus.*")) {
			hybridBike++;
		}
		if (detail.matches(".*(?i)cyprus.*")) {
			hybridBike++;
		}
		probs.put(AdType.HYBRID.toString(), hybridBike);		

		Double tandemBike = probs.get(AdType.TANDEM.toString());
		if (title.matches(".*(?i)tandem.*")) {
			tandemBike = tandemBike + 10;
		}
		probs.put(AdType.TANDEM.toString(), tandemBike);
		
		Double recumbentBike = probs.get(AdType.RECUMBENT.toString());
		if (title.matches(".*(?i)recumbent.*")) {
			recumbentBike = recumbentBike + 10;
		}
		probs.put(AdType.RECUMBENT.toString(), recumbentBike);
		
		Double frame = probs.get(AdType.FRAME.toString());
		if (title.matches(".*(?i)frame.*")) {
			frame = frame + .5d;
		}
		if (detail.matches(".*(?i)frame.*")) {
			frame = frame + .25d;
		}
		if (detail.matches(".*(?i)bike frame.*")) {
			frame = frame + 1d;
		}
		probs.put(AdType.FRAME.toString(), frame);
		
		Double shifter = probs.get(AdType.SHIFTER.toString());
		if (title.matches(".*(?i)crankset.*")) {
			shifter = shifter + .5;
		}
		if (title.matches(".*(?i)crank.*")) {
			shifter = shifter + .5;
		}
		if (title.matches(".*(?i)shifter.*")) {
			shifter = shifter + .5;
		}
		if (title.matches(".*(?i)shifters.*")) {
			shifter = shifter + .5;
		}
		probs.put(AdType.SHIFTER.toString(), shifter);
		
		Double rack = probs.get(AdType.BIKE_RACK.toString());
		if (title.matches(".*(?i)yakima.*")) {
			rack = rack +3;
		}
		if (title.matches(".*(?i)bike rack.*")) {
			rack = rack +3;
		}
		if (title.matches(".*(?i)thule.*")) {
			rack = rack +3;
		}
		if (title.matches(".*(?i)trunk.*")) {
			rack = rack +1;
		}
		probs.put(AdType.BIKE_RACK.toString(), rack);
		
		Double wheels = probs.get(AdType.WHEEL.toString());
		if (title.matches(".*(?i)wheels.*")) {
			wheels = wheels +2;
		}
		if (title.matches(".*(?i)rims.*")) {
			wheels = wheels +2;
		}
		probs.put(AdType.WHEEL.toString(), wheels);
		
		Double panniers = probs.get(AdType.PANNIERS.toString());
		if (title.matches(".*(?i)pannier.*")) {
			panniers = panniers +3;
		}
		if (title.matches(".*(?i)basket.*")) {
			panniers = panniers +3;
		}
		if (title.matches(".*(?i)bag.*")) {
			panniers = panniers +3;
		}
		probs.put(AdType.PANNIERS.toString(), panniers);
		
		Double seatPost = probs.get(AdType.SEAT_POST.toString());
		if (title.matches(".*(?i)seat post.*")) {
			seatPost = seatPost +3;
		}
		probs.put(AdType.SEAT_POST.toString(), seatPost);
		
		Double seat = probs.get(AdType.SEAT.toString());
		if (title.matches(".*(?i)seat.*")) {
			seat = seat + .5;
		}
		if (title.matches(".*(?i)saddle.*")) {
			seat = seat + .5;
		}
		probs.put(AdType.SEAT.toString(), seat);
		
		return max();
	}
	
	private static String max() {
		double greatest = 0;
		String greatestType = AdType.UNKNOWN.toString();
		
		Set<Entry<String,Double>> set = probs.entrySet();
		
		for (Entry<String,Double> entry : set) {
			if (entry.getValue()>greatest) {
				greatest = entry.getValue();
				greatestType = entry.getKey();
			}
			
		}
		
		//now clear it all out for the next run
		for (Entry<String,Double> entry : set) {
			entry.setValue(0d);
		}  
		
		return greatestType;
	}
	
	private static void filterWantAd(String title,String detail) {
		Double wantAd =probs.get(AdType.AD.toString());

		if (title.matches(".*(?i)wanted.*")) {			
			wantAd = wantAd +5;
		}
		if (title.matches(".*(?i)wtb.*")) {
			wantAd = wantAd +5;
		}
		if (title.matches(".*(?i)looking for.*")) {
			wantAd = wantAd +5;
		}

	}
}
