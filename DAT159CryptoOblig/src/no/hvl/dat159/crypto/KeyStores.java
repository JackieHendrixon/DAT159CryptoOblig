/**
 * 
 */
package no.hvl.dat159.crypto;

import javax.net.ssl.KeyManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;


/**
 * @author tdoy
 *
 */
public class KeyStores {

	
	/**
	 * 
	 * @param keyStoreFile
	 * @param alias
	 * @param keyStorePassword
	 * @return
	 */
	public static PrivateKey getPrivateKeyFromKeyStore(String keyStoreFile, String alias, String keyStorePassword)
			throws GeneralSecurityException, IOException {
		
		// Load the keystore (programmatically) and extract the private key from the keystore

		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		try(InputStream in = new FileInputStream(keyStoreFile)){
			keyStore.load(in, keyStorePassword.toCharArray());
		}
		catch(Exception e)
		{

		}
		KeyManagerFactory keyManagerFactory =
				KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

		PrivateKey key = (PrivateKey) keyStore.getKey(alias,keyStorePassword.toCharArray());
		
		return key;
	}
}
