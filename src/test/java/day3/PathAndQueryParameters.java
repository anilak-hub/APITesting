package day3;

import org.testng.annotations.Test;

//Rest Assured static imports
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.*;


public class PathAndQueryParameters {
	
	//https://reqres.in/api/users?page=2&id=7

	@Test
	void testPathAndQueryParameters() {
		
		given()
		.header("x-api-key", "reqres-free-v1")      // ← API Key added here
		    .pathParam("mypath", "users") //Path parameters
		    .queryParam("page", 2) //query parameters
		    .queryParam("id", 7)  //query parameters
		.when()
		    .get("https://reqres.in/api/{mypath}")
		
		.then()
		    .statusCode(200)
		    .log().all();
		
	}
	
	
}
