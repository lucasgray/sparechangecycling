package com.sparechangecycling.proxy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.xml.XmlPage;
import com.sparechangecycling.pojos.AdType;
import com.sparechangecycling.pojos.EbayBikeAd;
import com.sparechangecycling.util.SearchPreferences;


public class EbayProxy {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<EbayBikeAd> getEbayAds(SearchPreferences sp) throws FailingHttpStatusCodeException, IOException, ParseException {
		
		List<AdType> type = sp.getType();
		String zip = sp.getZip();
		Integer distance = sp.getDistance();
		Integer lowPrice = sp.getLowPrice();
		Integer highPrice = sp.getHighPrice();
		String textMatch = sp.getTextMatch();
		
		if (type == null){
			type = new ArrayList<AdType>();
		}
		if (type.size()==0){
			type.add(AdType.ALL);
		}
		
		logger.info("getting ebay ads for zip: "+zip+" and type "+type.get(0));
		
		String getDatas = "callname=FindItemsAdvanced"+
			   "&appid=lucasgra-0b5f-4a97-98bb-578b4548b3ff"+
			   "&version=517"+
			   "&siteid=0"+
			   "&MaxEntries=100"+
			   "&CategoryID=" + type.get(0).getEbayCategory()+
			   "&HideDuplicateItems=true"+
			   "&responseencoding=XML";
		logger.info("low price: "+lowPrice);
		logger.info("high price: "+highPrice);
		if (lowPrice != null && lowPrice > 0) {
			getDatas = getDatas + "&PriceMin.value=" + lowPrice;
		}
		if (highPrice != null && highPrice > 0) {
			getDatas = getDatas + "&PriceMax.value=" + highPrice;
		}
		if (zip != null) {
			getDatas = getDatas + "&PostalCode=" + zip + "&MaxDistance="
					+ distance.intValue();
		}
		if (textMatch != null && !textMatch.trim().equals("")) {
			getDatas = getDatas+"&QueryKeywords=" + URLEncoder.encode(textMatch,"UTF-8");
		}
		String url = "http://open.api.ebay.com/shopping?";
		WebClient webClient = new WebClient();
		XmlPage page = (XmlPage) webClient.getPage(url+getDatas);
		//System.out.println(page.asText());

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc;
		
		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			doc = db.parse(new ByteArrayInputStream(page.asXml().getBytes("UTF-8")));
			Element docEle = doc.getDocumentElement();
			NodeList nl = docEle.getElementsByTagName("Item");

			List<EbayBikeAd> ads = new ArrayList<EbayBikeAd>();
			for (int x=0; x< nl.getLength();x++) {
				Element el = (Element)nl.item(x);
				
				EbayBikeAd ad = new EbayBikeAd();
				ad.setTitle(getTextValue(el,"Title"));
				ad.setLink(new URL(getTextValue(el,"ViewItemURLForNaturalSearch")));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				ad.setEndDateTime(sdf.parse(getTextValue(el,"EndTime").split("\\.")[0].replaceAll("T", "")));
				
				if (getTextValue(el,"GalleryURL") != null) {
					ad.setPicLink(new URL(getTextValue(el,"GalleryURL")));
				}
				
				ad.setCurrentPrice(getTextValue(el,"ConvertedCurrentPrice"));
				ads.add(ad);
			}
			Collections.sort(ads);
			return ads;

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}

		return null;
		
		//logger.info(page.asText());
		/*
		<Item>
        <ItemID>
          230316726733
        </ItemID>
        <EndTime>
          2009-01-05T17:24:07.000Z
        </EndTime>
        <ViewItemURLForNaturalSearch>
          http://cgi.ebay.com/New-Felt-F1-Sprint-Slipstream-Frameset-56cm-Carbon_W0QQitemZ230316726733QQihZ013QQcategoryZ98084QQcmdZViewItem
        </ViewItemURLForNaturalSearch>
        <ListingType>
          FixedPriceItem
        </ListingType>
        <GalleryURL>
          http://thumbs2.ebaystatic.com/pict/2303167267338080_1.jpg
        </GalleryURL>
        <PrimaryCategoryID>
          98084
        </PrimaryCategoryID>
        <PrimaryCategoryName>
          Sporting Goods:Outdoor Sports:Cycling:Bicycles &amp; Frames:Road Bikes
        </PrimaryCategoryName>
        <ConvertedCurrentPrice currencyID="USD">
          1699.0
        </ConvertedCurrentPrice>
        <ListingStatus>
          Active
        </ListingStatus>
        <TimeLeft>
          P2DT22H6M44S
        </TimeLeft>
        <Title>
          New Felt F1 Sprint Slipstream Frameset 56cm Carbon 
        </Title>
        <ShippingCostSummary>
          <ShippingServiceCost currencyID="USD">
            0.0
          </ShippingServiceCost>
          <ShippingType>
            Flat
          </ShippingType>
        </ShippingCostSummary>
      </Item>
		 */
		
	}
	
//	public static void main(String[] args) throws FailingHttpStatusCodeException, IOException, ParseException {
//		EbayProxy proxy = new EbayProxy();
//		SearchPreferences sp = new SearchPreferences(null,null,null,null,null,null);
//		proxy.getEbayAds(sp);
//		
//	}
	
	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		if (textVal != null) {
			textVal = textVal.trim();
		}
		return textVal;
	}

}
