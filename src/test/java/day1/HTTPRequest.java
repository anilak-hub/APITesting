 package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import static io.restassured.matcher.RestAssuredMatchers.*;


/*
 given()-- Pre-requisites
    content type, set cookies, add auth, add param, set headers info etc,..
    
 When()-- Requests
    get, post, put, delete
  
 then()-- Validations
    
 
 
 */
public class HTTPRequest {
int id;
	@Test(priority = 1)
	void getUsers()
	{
		given()
        .header("x-api-key", "reqres-free-v1")
		.when()
		 .get("https://reqres.in/api/users?page=2")
		 .then()
		   .statusCode(200)
		   .body("page",equalTo(2))
		   .log().all();
		 
		
		
	}
	
	@Test(priority = 2)
	void createUser() {
	    HashMap<String, String> data = new HashMap<>();
	    data.put("name", "anil");
	    data.put("job", "sdet"); // lowercase key: "job" instead of "Job"

	   id= given()
	        .baseUri("https://reqres.in")
	        .contentType("application/json")
	        .header("x-api-key", "reqres-free-v1")
	        .body(data)
	    .when()
	        .post("/api/users")
	        .jsonPath().getInt("id");
//	    .then()
//	        .statusCode(201)
//	        .log().all();
	}
	
	@Test(priority = 3, dependsOnMethods = {"createUser"})
	void updateUser() 
	{
		 HashMap<String, String> data = new HashMap<>();
		    data.put("name", "anil");
		    data.put("job", "automation tester"); // lowercase key: "job" instead of "Job"

		    given()
		        .baseUri("https://reqres.in")
		        .contentType("application/json")
		        .header("x-api-key", "reqres-free-v1")
		        .body(data)
		    .when()
		        .put("/api/users/"+id)
		       
		    .then()
		        .statusCode(200)
		        .log().all();
		
	}
	@Test(priority = 4)
	void deleteUser() {
		given()
		 .header("x-api-key", "reqres-free-v1")
		.when()
		  .delete("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(204)
        .log().all();
	}
	
}
