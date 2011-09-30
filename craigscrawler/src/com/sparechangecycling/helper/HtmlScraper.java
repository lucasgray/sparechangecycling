package com.sparechangecycling.helper;

import java.io.IOException;
import java.io.StringReader;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public final class HtmlScraper {
	
	public static String scrape(String str) {
		Html2Text parser = new HtmlScraper().new Html2Text();
		try {
			str = str.replaceAll("(?i)<a.*>.*</a.*>", "")
				.replaceAll("(?i)<img.*>.*</img.*>", "");
			StringReader in = new StringReader(str);
			new ParserDelegator().parse(in, parser, Boolean.TRUE);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parser.s.toString();
	}

	private class Html2Text extends HTMLEditorKit.ParserCallback {
		
		private StringBuilder s;
	
		public Html2Text() {
			s = new StringBuilder();
		}
	
		public void handleText(char[] text, int pos) {
			s.append(text);
		}
	}
}
