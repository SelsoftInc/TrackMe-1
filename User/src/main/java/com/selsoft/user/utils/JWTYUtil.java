package com.selsoft.user.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.selsoft.user.constants.Constants;
import com.selsoft.user.controller.UserController;
import com.selsoft.user.model.User;
import org.apache.log4j.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTYUtil {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	private SecretKey saveSecretKey() {
		SecretKey secretKey = null;
		KeyStore ks = null;
		try {
			secretKey = KeyGenerator.getInstance("AES").generateKey();
			char[] password = "keystorepassword".toCharArray();
			FileOutputStream fos = null;
			try {
				ks = KeyStore.getInstance("JCEKS");
				ks.load(null, password);
				fos = new FileOutputStream(JWTYUtil.class.getResource("/KEYSTORE.jks").getFile());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ks != null) {
					KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);
					KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secretKey);
					ks.setEntry("secretKeyAlias", skEntry, protParam);
					ks.store(fos, password);
					if (fos != null) {
						fos.close();
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		logger.info("Save Secret Key  " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
		return secretKey;
	}

	private SecretKey getSecretKey() {
		KeyStore ks;
		try {
			ks = KeyStore.getInstance("JCEKS");
			FileInputStream fis = new FileInputStream(JWTYUtil.class.getResource("/KEYSTORE.jks").getFile());
			ks.load(fis, "keystorepassword".toCharArray());
			return (SecretKey) ks.getKey("secretKeyAlias", "keystorepassword".toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String createJWT(User userData) {
		try {
			SecretKey secretKey = saveSecretKey();
			// The JWT signature algorithm we will be using to sign the token
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);

			// We will sign our JWT with our ApiKey secret
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put(Constants.USER_EMAIL, String.valueOf(userData.getEmail()));
			claims.put(Constants.USER_FIRST_NAME, userData.getFirstName());
			claims.put(Constants.USER_TYPE, userData.getUserType());

			// Let's set the JWT Claims
			JwtBuilder builder = Jwts.builder().setClaims(claims).setId(String.valueOf(userData.getEmail())).setIssuedAt(now).setSubject("Authentication Token").setIssuer("Mexus.CoooM").signWith(signatureAlgorithm, signingKey);


			// Builds the JWT and serializes it to a compact, URL-safe string
			return builder.compact();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// Sample method to validate and read the JWT
	public String parseJWT(String jwtToken) {
		try {
			SecretKey secretKey = getSecretKey();
			if (secretKey != null) {
				System.out.println("Get Secret Key  " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
				// This line will throw an exception if it is not a signed JWS
				// (as expected)
				Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(Base64.getEncoder().encodeToString(secretKey.getEncoded()))).parseClaimsJws(jwtToken).getBody();
				return (String) claims.get(Constants.USER_EMAIL);
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	
	
	
}

