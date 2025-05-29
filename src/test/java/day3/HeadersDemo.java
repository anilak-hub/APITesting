package day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class HeadersDemo {
	@Test(priority = 1)
	void testHeaders() {
		
		given()
		
		
		  .when()
		    .get("https://www.google.com")
		  
		  .then()
		    .header("Cache-Control", "private, max-age=0")
		    .header("Content-Type", "text/html; charset=ISO-8859-1")
		    .header("Accept-CH", "Sec-CH-Prefers-Color-Scheme");
		   // .log().all();
		    
		
	}
	@Test(priority = 2)
	void getHeaders() {
		
	Response res= given()
		
		
		.when()
		.get("https://www.google.com");
		
		
		//get single header info
	String header_value = res.getHeader("Content-Type");
	System.out.println("Value of header 'content-Type' : "+header_value);
		
	Headers headers = res.getHeaders();
	
	for (Header header : headers) {
	String value = header.getValue();
	System.out.println("Value of header '"+header+"' : "+value);
	}
	}
	

}
