/**
 * 
 */
package no.hvl.dat159.crypto;

import java.io.UnsupportedEncodingException;
import java.security.*;

import javax.xml.bind.DatatypeConverter;

/**
 * @author tdoy
 *
 */
public class DigitalSignature {
	
	//public static final String SIGNATURE_SHA256WithDSA = "SHA256WithDSA";
	public static final String SIGNATURE_SHA256WithRSA = "SHA256WithRSA";
	
	public static byte[] sign(String message, PrivateKey privateKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
		
		// implement me
		
		return null;
		
	}
	
	public static boolean verify(String message, byte[] digitalSignature, PublicKey publickey, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
		Signature sig = Signature.getInstance(algorithm);
		sig.initVerify(publickey);
		sig.update(message.getBytes());
		return sig.verify(digitalSignature);
	}
	
	public static String getHexValue(byte[] signature) {
		
		return DatatypeConverter.printHexBinary(signature);
	}
	
	public static byte[] getEncodedBinary(String signatureinhex) {
		
		return DatatypeConverter.parseHexBinary(signatureinhex);
	}

}
