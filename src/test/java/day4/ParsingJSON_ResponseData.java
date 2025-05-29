package day4;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParsingJSON_ResponseData {

	@Test(priority = 1)
	void testJsonResponse() {
		baseURI = "http://localhost:3000";

		/* given()
	        .contentType("application/json")

	    .when()
	        .get("/products")

	    .then()
	        .statusCode(200)
	       // .log().body()
	        .body("[5].name", equalTo("Pack of Batteries")); */


		Response res = given()
				.contentType("application/json")

				.when()
				.get("/products");

		Assert.assertEquals(res.getStatusCode(), 200);

	//	System.out.println(res.getHeaders());
		//System.out.println(res.body());
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		String product = res.jsonPath().get("[5].name").toString();
		Assert.assertEquals(product, "Pack of Batteries");
	}

//	@Test(priority = 2)
//	void testJSON_ResponseBody() {
//
//		baseURI = "http://localhost:3000";
//
//		Response res =	given()
//				.contentType("application/json")
//
//				.when()
//				.get("/products");
//
//		JSONObject jo = new JSONObject(res.toString()); //converting response to JSON object type
//
//		int arrayLength = jo.getJSONArray("products").length(); // finding the JSON array length
//
//		for (int i = 0; i < arrayLength; i++) {
//
//			String productName = jo.getJSONArray("products").getJSONObject(i).get("name").toString();
//			System.out.println(productName);
//
//		}
//
//	}
	@Test(priority = 2) 
	void testJSON_ResponseBody() {

	    baseURI = "http://localhost:3000";

	    Response res = given()
	            .contentType("application/json")
	            .when()
	            .get("/products");

	    // Get the actual JSON response body as a string
	    String jsonResponse = res.getBody().asString();

	    // Since your response is a JSON array, use JSONArray directly
	    JSONArray jsonArray = new JSONArray(jsonResponse);

	    for (int i = 0; i < jsonArray.length(); i++) {
	        JSONObject product = jsonArray.getJSONObject(i);
	        String productName = product.getString("name");
	        System.out.println(productName);
	    }
	}



}
