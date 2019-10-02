package no.hvl.dat159.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.*;

import javax.crypto.NoSuchPaddingException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import no.hvl.dat159.config.ServerConfig;
import no.hvl.dat159.crypto.DigitalSignature;
import no.hvl.dat159.crypto.KeyStores;

public class TCPClientSSLRSA {
	
	private String server;
	private int port;
	
	public TCPClientSSLRSA(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	public void clientProcess(String msg) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, NoSuchPaddingException {

		try {
			
			SocketFactory ssf = SSLSocketFactory.getDefault();
			Socket csocket = ssf.createSocket(server, port);
			
			PrintWriter outmsg = new PrintWriter(csocket.getOutputStream(), true);
			BufferedReader inmsg = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
			
			System.out.println("Message to TCPServer: "+msg);
			// sign the message and append the signature to the message to the server
			
			// implement me

			String signatureinhex = DigitalSignature.getHexValue(DigitalSignature.sign(msg,getPrivateKey(),DigitalSignature.SIGNATURE_SHA256WithRSA));
			
			msg = msg + "-"+signatureinhex;			// format message as: Message-Signature
			
			// send msg + sign to the server
			outmsg.println(msg);

			//read the response from the server
			StringBuffer sb = new StringBuffer();
			String line = "";
			while((line = inmsg.readLine())!=null) {
				sb.append(line+"\n");
			}
			
			System.out.println("Response from Server: "+sb);	
			
			outmsg.close();
			inmsg.close();
			csocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

	}
	
	private PrivateKey getPrivateKey() throws NoSuchAlgorithmException, NoSuchPaddingException, GeneralSecurityException, IOException {
		
		PrivateKey privatekey = KeyStores.getPrivateKeyFromKeyStore("/usr/local/keys/tcp_keystore","tcpexample","qwerty");

		return privatekey;
	}

	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, NoSuchPaddingException {
		// set the truststore dynamically using the system property
		System.setProperty("javax.net.ssl.trustStore", "/usr/local/keys/tcp_truststore");
		System.setProperty("javax.net.ssl.trustStorePassword", "qwerty");
		// implement me
		
		String message = "Message from TCP SSLClient";
		TCPClientSSLRSA c = new TCPClientSSLRSA(ServerConfig.SERVER, ServerConfig.PORT);
		c.clientProcess(message);

	}

}
