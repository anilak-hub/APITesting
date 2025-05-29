package day5;


import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class JSON_SchemaValidation {
	
	@Test
	void schemaValidation() {
		
		given()
		
		.when()
		
		.get("http://localhost:3000/products")
		
		.then()
		
		//JSON Schema validation syntax
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeSchema.json"));
		
	}

}
