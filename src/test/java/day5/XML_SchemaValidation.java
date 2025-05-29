package day5;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class XML_SchemaValidation {
	
	@Test
	void xmlSchemaValidation() {
		
		given()
		
		.when()
		.get("file:///C:/API%20Testing/XmlFile/store.xml")

		.then()
		
		.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("stor_schema.xsd"));
		
	}

}
