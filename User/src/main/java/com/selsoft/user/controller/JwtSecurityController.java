package com.selsoft.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.jose4j.http.Response;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.selsoft.user.model.User;
import com.selsoft.user.utils.MongoDBSingleton;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController("/security")
public class JwtSecurityController {

	static Logger logger = Logger.getLogger(JwtSecurityController.class);
	static List<JsonWebKey> jwkList = null;

	static {
		logger.info("Inside static initializer...");
		jwkList = new LinkedList<>();
		for (int kid = 1; kid <= 3; kid++) {
			JsonWebKey jwk = null;
			try {
				jwk = RsaJwkGenerator.generateJwk(2048);
				logger.info("PUBLIC KEY (" + kid + "): " + jwk.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
			} catch (JoseException e) {
				e.printStackTrace();
			}
			jwk.setKeyId(String.valueOf(kid));
			jwkList.add(jwk);
		}
	}

	@RequestMapping(value="/authenticate",  produces="APPLICATION_JSON")
	  public String authenticateCredentials(@RequestHeader("username") 
		String username, @RequestHeader("password") String password)
	      throws JsonGenerationException, JsonMappingException, 
								IOException {
	    logger.info("Authenticating User Credentials...");
	    User user = validUser(username, password); 
	    RsaJsonWebKey senderJwk = (RsaJsonWebKey) jwkList.get(0);
	    senderJwk.setKeyId("1");
	    logger.info("JWK (1) ===> " + senderJwk.toJson());
	    // Create the Claims, which will be the content of the JWT
	    JwtClaims claims = new JwtClaims();
	    claims.setIssuer("trackme.com");
	    claims.setExpirationTimeMinutesInTheFuture(10);
	    claims.setGeneratedJwtId();
	    claims.setIssuedAtToNow();
	    claims.setNotBeforeMinutesInThePast(2);
	    claims.setSubject(user.getEmail());
	    claims.setStringListClaim("roles", user.getUserType()); 
	 
	    JsonWebSignature jws = new JsonWebSignature();
	    jws.setPayload(claims.toJson());
	    jws.setKeyIdHeaderValue(senderJwk.getKeyId());
	    jws.setKey(senderJwk.getPrivateKey());
	    jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256); 

	    String jwt = null;
	    try {
	      jwt = jws.getCompactSerialization();
	    } catch (JoseException e) {
	      e.printStackTrace();
	    }

	    return jwt;
	  }

	// --- Protected resource using JWT Tokens` ---
	@Path("/finditembyid")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findItemById(@HeaderParam("token") String token, @QueryParam("itemid") String item_id)
			throws JsonGenerationException, JsonMappingException, IOException {

		Item item = null;

		logger.info("Inside findOrderById...");

		if (token == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		JsonWebKeySet jwks = new JsonWebKeySet(jwkList);
		JsonWebKey jwk = jwks.findJsonWebKey("1", null, null, null);
		logger.info("JWK (1) ===> " + jwk.toJson());

		// Validate Token's authenticity and check claims
		JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime().setAllowedClockSkewInSeconds(30)
				.setRequireSubject().setExpectedIssuer("avaldes.com").setVerificationKey(jwk.getKey()).build();

		try {
			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
			logger.info("JWT validation succeeded! " + jwtClaims);
		} catch (InvalidJwtException e) {
			logger.error("JWT is Invalid: " + e);
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		MongoDBSingleton mongoDB = MongoDBSingleton.getInstance();
		MongoDatabase db = mongoDB.getDatabase();

		BasicDBObject query = new BasicDBObject();
		query.put("_id", item_id);
		List<Document> results = db.getCollection("items").find(query).into(new ArrayList<Document>());
		int size = results.size();

		if (size == 0) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
			statusMessage.setMessage("Unable to find that item !!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
		}

		for (Document current : results) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				logger.info(current.toJson());
				item = mapper.readValue(current.toJson(), Item.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return Response.status(200).entity(item).build();
	}

	// --- Protected resource using JWT Token ---
	@Path("/showallitems")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllItems(@HeaderParam("token") String token)
			throws JsonGenerationException, JsonMappingException, IOException {

		Item item = null;

		logger.info("Inside showAllItems...");

		if (token == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		JsonWebKeySet jwks = new JsonWebKeySet(jwkList);
		JsonWebKey jwk = jwks.findJsonWebKey("1", null, null, null);
		logger.info("JWK (1) ===> " + jwk.toJson());

		// Validate Token's authenticity and check claims
		JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime().setAllowedClockSkewInSeconds(30)
				.setRequireSubject().setExpectedIssuer("avaldes.com").setVerificationKey(jwk.getKey()).build();

		try {
			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
			logger.info("JWT validation succeeded! " + jwtClaims);
		} catch (InvalidJwtException e) {
			logger.error("JWT is Invalid: " + e);
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		MongoDBSingleton mongoDB = MongoDBSingleton.getInstance();
		MongoDatabase db = mongoDB.getDatabase();

		List<Document> results = db.getCollection("items").find().into(new ArrayList<Document>());
		int size = results.size();

		if (size == 0) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
			statusMessage.setMessage("There are no Items to display !!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(statusMessage).build();
		}

		List<Item> allItems = new ArrayList<Item>();
		for (Document current : results) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				logger.info(current.toJson());
				item = mapper.readValue(current.toJson(), Item.class);
				allItems.add(item);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return Response.status(200).entity(allItems).build();
	}

	private User validUser(String username, String password) {
		MongoDBSingleton mongoDB = MongoDBSingleton.getInstance();
		MongoDatabase db = mongoDB.getDatabase();
		List<Document> results = null;

		results = db.getCollection("users").find(new Document("username", username)).limit(1)
				.into(new ArrayList<Document>());
		int size = results.size();

		if (size == 1) {
			for (Document current : results) {
				ObjectMapper mapper = new ObjectMapper();
				User user = null;
				try {
					// logger.info(current.toJson());
					user = mapper.readValue(current.toJson(), User.class);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					return user;
				} else {
					return null;
				}
			}
			return null;
		} else {
			return null;
		}
	}
}

}
