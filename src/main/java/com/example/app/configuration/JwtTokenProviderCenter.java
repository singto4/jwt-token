package com.example.app.configuration;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.app.constant.JwtTokenError;
import com.example.app.models.UserProfile;

@Component
public class JwtTokenProviderCenter {

	private static final Logger logger = LogManager.getLogger(JwtTokenProviderCenter.class);

	private static final String ISSUER = "System";
	
	@Value("${jwt-private-key}")
	private String privateKey;

	public String genarateToken(UserProfile userProfile) throws Exception {

		String jwt = null;

		try {
			
			AesKey key = new AesKey(privateKey.getBytes());

			JwtClaims claims = new JwtClaims();
			claims.setIssuer(ISSUER);
			claims.setExpirationTimeMinutesInTheFuture(10);
			claims.setNotBeforeMinutesInThePast(2);
			claims.setSubject(userProfile.getUsername());
			claims.setClaim("userId", userProfile.getUserId());

			JsonWebSignature jws = new JsonWebSignature();
			jws.setPayload(claims.toJson());
			jws.setKey(key);
			jws.setKeyIdHeaderValue(key.getAlgorithm());
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);

			jwt = jws.getCompactSerialization();

		} catch (JoseException ex) {
			logger.error("Unknown error: {}", ExceptionUtils.getStackTrace(ex));
			throw ex;
		}

		return jwt;
	}

	public boolean validateToken(String token) throws Exception {

		if (token == null) {
			throw new Exception(JwtTokenError.ACCESS_DENIED);
		}

		try {
			
			AesKey key = new AesKey(privateKey.getBytes());

			// Validate Token's authenticity and check claims
			JwtConsumer jwtConsumer = new JwtConsumerBuilder()
					.setRequireExpirationTime() 
					.setRequireSubject()
					.setExpectedIssuer(ISSUER)
					.setVerificationKey(key)
					.setJwsAlgorithmConstraints(ConstraintType.PERMIT, AlgorithmIdentifiers.HMAC_SHA256) 
					.build();

			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
			logger.info("JWT validation succeeded! : {}", jwtClaims);
			
			return true;

		} catch (InvalidJwtException ex) {
			logger.error("JWT is Invalid: {}", ExceptionUtils.getStackTrace(ex));
		}
		
		return false;
	}

}
