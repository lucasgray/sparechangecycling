package com.sparechangecycling.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;


//import org.jdesktop.swingx.graphics.GraphicsUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class ImageHandler {
//
//
//	private static final Logger log = LoggerFactory.getLogger(ImageHandler.class);
//	//TODO definitely a tuning constant to refactor
//	//TODO change all URL types to String - blobs in the db SUCK
//	
//	private static final String THUMBS_PATH = "thumbs/";
//	private static final String PUBLIC_HTML = "/public_html/";
//	private static final Double MAX_WIDTH = 800d;
//	private static final Double MAX_HEIGHT = 600d;
//	private static final int SIZE_OF_THUMB = 200;
//	//1mb
//	private static final Long MAX_SIZE_IN_BYTES = 1000000l;
//	
//	private static final String PATH;
//	private static final String PREFIX;
//	
//	static {
//		// Read properties file.
//	    Properties properties = new Properties();
//	    try {
//	        properties.load(new FileInputStream("//home//lucas//javastuff//scc_files.properties"));
//	    } catch (IOException e) {
//	    }
//
//	    PATH = properties.getProperty("scc.thumbnail.server.filepath");
//		PREFIX = properties.getProperty("scc.thumbnail.img.src");
//		
//		log.info("prefix: "+PREFIX);
//		log.info("path: "+PATH);
//	}
//	
//	public static String getHttpPrefix() {return PREFIX;}
//	public static String getThumbHttpPrefix() {return THUMBS_PATH;}
// 	
//	/**
//	 * 
//	 * Save the file to the correct apache directory, return the filename
//	 * we do this so dev/prod manipulation is easier. 
//	 * @param file
//	 * @return String name of file
//	 */
//	public static String saveImageToDisk(FileBean file) {
//		String fileName = file.getFileName().substring(0,file.getFileName().lastIndexOf('.')-1);
//		String fileExt = file.getFileName().substring(file.getFileName().lastIndexOf('.'));
//		
//		log.info("fileName: "+fileName);
//		log.info("fileExt: "+fileExt);
//		
//		String fullPicName = new StringBuilder().append(
//				file.getFileName().substring(0, 5)).append(randomSequence())
//				.append(fileExt).toString();
//		
//		try {
//			log.info("saving pic: "+PATH+fullPicName);
//			
//			if (file.getSize()>MAX_SIZE_IN_BYTES) {
//				log.error("file was too big!");
//			}
//			
//			file.save(new File(new StringBuilder().append(PATH).append(fullPicName).toString()));
//		} catch (IOException e) {
//			log.error("problem saving file");
//		}
//		return fullPicName;
//	}
//	
//	private static String randomSequence() {
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append((int)(Math.random()*10000));
//		
//		return sb.toString();
//	}
//
//	public static String getThumbnailFromImage(String fullPicName)  {
//		String fullPath = "";
//		String fn = "";
//		try {
//		BufferedImage bufferedImage = ImageIO.read(new File(PATH+fullPicName));
//		Double origHeight = new Double(bufferedImage.getHeight());
//		Double origWidth = new Double(bufferedImage.getWidth());
//
//		if ((origWidth  >  MAX_WIDTH) || (origHeight > MAX_HEIGHT)) {
//			log.error("image width or height too big");
//		}
//		
//		Double ratio = ((double)SIZE_OF_THUMB) / origWidth;
//		Double height = origHeight * ratio;
//		
//		log.debug("thumb size: "+SIZE_OF_THUMB+"x"+height.toString());  
//		
//		//uses swingx packages - better quality
//		BufferedImage scaledBI = GraphicsUtilities.createThumbnailFast(bufferedImage, SIZE_OF_THUMB, height.intValue());
//		
//		fn = fullPicName.substring(0,fullPicName.lastIndexOf('.'));
//		fullPath = PATH+THUMBS_PATH+fn+"_tn.png";
//		log.info("saving thumb: "+fullPath);
//		File newFile = new File(fullPath);
//		
//		ImageIO.write(scaledBI,"png", newFile);
//		} catch (IOException e) {
//			log.error("problem saving file");
//		}
//		return fn+"_tn.png";
//	}
//	
////	public static void main(String[] args) throws MalformedURLException {
////	URL url = new URL("http://www.sparechangecycling:1986/images/thumbs/09_co641_tn.png");
////	System.out.println(convertApacheUrlToFilepath(url,true));
////	deleteImage(convertApacheUrlToFilepath(url,true));
////}
//	
//	public static String convertFilepathToApacheUrl(String filePath) {
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append(PREFIX);
//		sb.append(filePath.substring((filePath.indexOf(PUBLIC_HTML)+PUBLIC_HTML.length())));
//		
//		return sb.toString();
//	}
//	
//	public static String convertApacheUrlToFilepath(String url,boolean thumb) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(PATH);
//		if (thumb) sb.append(THUMBS_PATH);	
//		sb.append(url.substring(url.toString().lastIndexOf('/')+1));
//		
//		return sb.toString();
//	}
//
//	public static void deleteImage(String location) {
//		File f = new File(location);
//		if (f.exists()) {
//			log.debug("deleting file: "+location);
//			f.delete();
//		}
//	}
}
