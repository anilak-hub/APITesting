package day3;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class LoginDemo {
	@Test
	void testLog() {
		given()
		
		.when()
		  .get("https://reqres.in/api/users?page=2")
		
		.then()
		  .log().all();
	}

}
