package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateJobAPITest {
	
	
	
	@Test
	public void createJobAPITest() {
		
		
		// create the object of CreateJobPayload
		Customer customer = new Customer("Pranay", "Raut", "9874563210", "", "ppr@gmail.com", "");

		CustomerAddress customerAddress = new CustomerAddress("A 404 ", "GB Nagar ", "GG Road", "Inorbit", "Mumbai", "411002", "India", "Maharashtra"); 

		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "98053710550731", "98053710550731", "98053710550731", "2025-04-06T18:30:00.000Z", 1, 1);
				
		Problems problems = new Problems(1, "Battery Issue") ;
		
		Problems[] problemArray = new Problems[1];
		problemArray[0] = problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemArray);
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when().post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK());

	}

}
