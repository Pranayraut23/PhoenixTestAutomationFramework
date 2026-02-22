package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;

import java.io.IOException;


import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() throws IOException  {
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.post("master")// default content type added by RA is application/url-formencoded
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("data", hasKey("mst_oem"))
		.body("data", hasKey("mst_model"))
		.body("$", hasKey("message"))
		.body("$", hasKey("data"))
		.body("data.mst_oem.size()", equalTo(2))// check size of json array for mst_oem
		.body("data.mst_model.size()", greaterThan(0))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_oem.name", everyItem(notNullValue()))
		.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
	}
	
	@Test
	public void invaliTokenMasterAPITest() throws IOException {
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.post("master")// default content type added by RA is application/url-formencoded
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
		
	}

}
