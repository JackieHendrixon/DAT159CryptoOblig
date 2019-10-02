/**
 * 
 */
package no.hvl.dat159.crypto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * @author tdoy
 *
 */
public class Certificates {

	/**
	 * Given a certificate, extract the public key for operations such as encryption/signature
	 */
	
	/**
	 * Client side public key methods
	 * @param certfile
	 * @return
	 */
	public static PublicKey getPublicKey(String certfile) throws CertificateException, FileNotFoundException {
		FileInputStream fin = new FileInputStream(certfile);
		CertificateFactory f = CertificateFactory.getInstance("X.509");
		X509Certificate certificate = (X509Certificate) f.generateCertificate(fin);
		PublicKey pk = certificate.getPublicKey();
		return pk;
	}

}
