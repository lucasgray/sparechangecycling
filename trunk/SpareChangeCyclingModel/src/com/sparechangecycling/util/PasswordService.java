package com.sparechangecycling.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import sun.misc.BASE64Encoder;

public enum PasswordService {
	
	INSTANCE;

	public String encrypt(String plaintext, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest md = null;
		md = MessageDigest.getInstance("SHA-256");
		md.reset();
		md.update(salt);
		byte[] raw = md.digest(plaintext.getBytes("UTF-8"));

		String hash = (new BASE64Encoder()).encode(raw);
		return hash; // step 6
	}
	
	public byte[] generateEightByteSalt() throws NoSuchAlgorithmException {
		
		// Uses a secure Random not a simple Random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // Salt generation 64 bits long
        byte[] bSalt = new byte[8];
        random.nextBytes(bSalt);
		
		return bSalt;
	}

	public static PasswordService getInstance() {
		return INSTANCE;
	}


}