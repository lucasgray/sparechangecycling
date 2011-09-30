package com.sparechangecycling.pojos;

import java.util.Arrays;
import java.util.List;


public enum AdType {
	
	ROAD ("Road bikes","98084"),
	MTN ("Mtn bikes","98083"),
	KIDS ("Kids' bikes","64647"),
	FIXIE ("Fixed gear bikes","159089"),
	HYBRID ("Hybrid bikes","106945"),
	CRUISER ("Cruiser bikes","106945"),
	BMX ("Bmx bikes","98082"),
	TRI ("Tri bikes","7294"),
	TANDEM ("Tandem bikes","56192"),
	RECUMBENT ("Recumbent bikes","106949"),
	
	WHEEL ("Wheels/rims","58099"),
	SEAT ("Seats","22693"),
	SEAT_POST ("Seat posts","58101"),
	SHIFTER ("Shifters","36140"),
	FRAME ("Frames","33503"),
	BIKE_RACK ("Bike racks","56199"),
	LIGHT ("Headlights","22689"),
	PANNIERS ("Panniers","62131"),
	MISC("Misc.","22688"),
	
	AD("ad",""),
	UNKNOWN ("unknown","7294"),
	ALL ("all","7294");
	
	private String strName;
	//FROM: http://sporting-goods.listings.ebay.com/_W0QQfclZ3QQloctZShowCatIdsQQsacatZ382QQsalocationZatsQQsocmdZListingCategoryOverview
	//TODO: categories needing more work from ebay: cruiser/hybrid, tri
	private String ebayCategory;
	
	AdType(String strName,String ebayCategory) {
		this.strName = strName;
		this.ebayCategory = ebayCategory;
	}
	
	public static List<AdType> getBikeTypes() {
		AdType[] types = {
				AdType.ROAD ,
				AdType.MTN ,
				AdType.KIDS,
				AdType.FIXIE,
				AdType.HYBRID,
				AdType.CRUISER,
				AdType.BMX,
				AdType.TRI,
				AdType.TANDEM,
				AdType.RECUMBENT};
		
		return Arrays.asList(types);
	}
	
	public static List<AdType> getPartTypes() {
		
		AdType[] types = {
				AdType.WHEEL,
				AdType.SEAT,
				AdType.SHIFTER,
				AdType.FRAME,
				AdType.BIKE_RACK,
				AdType.LIGHT,
				AdType.PANNIERS,
				AdType.MISC};
		
		return Arrays.asList(types);
	}
	
	public static List<String> getBikeTypesStr() {
		String[] types = {
				AdType.ROAD.toString() ,
				AdType.MTN.toString() ,
				AdType.KIDS.toString(),
				AdType.FIXIE.toString(),
				AdType.HYBRID.toString(),
				AdType.CRUISER.toString(),
				AdType.BMX.toString(),
				AdType.TRI.toString(),
				AdType.TANDEM.toString(),
				AdType.RECUMBENT.toString()};
		
		return Arrays.asList(types);
	}
	
	public static List<String> getPartTypesStr() {
		
		String[] types = {
				AdType.WHEEL.toString(),
				AdType.SEAT.toString(),
				AdType.SHIFTER.toString(),
				AdType.FRAME.toString(),
				AdType.BIKE_RACK.toString(),
				AdType.LIGHT.toString(),
				AdType.PANNIERS.toString(),
				AdType.MISC.toString()};
		
		return Arrays.asList(types);
	}

	//since we are losing AdType and getting String instead from the db,
	//this is easier however clunky
	public static boolean isPart(String test) {
		return getPartTypesStr().contains(test);	
	}
	public static boolean isBike(String test) {
		return getBikeTypesStr().contains(test);
	}
	
	public String getEbayCategory() {
		return ebayCategory;
	}
	
	@Override
	public String toString() {
		return strName;
	}
	/**for jstl**/
	public String getString() {
		return strName;
	}
}
