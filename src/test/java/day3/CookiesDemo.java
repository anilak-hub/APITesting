package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class CookiesDemo {
	@Test(priority = 1)
	void testCookies() {
		
		given()
		
		.when()
		   .get("https://www.google.com")
		.then()
		   //.cookie("AEC","AVcja2f5Fcqnj_9no1Ht2IrSX82VjzAPSNgjoHaXzlK9uqoD9Vgoqmpq0yE")
		   .log().all();
		
	}
	@Test(priority = 2)
	void getCookiesinfo() {
		
		Response res = given()
		
		.when()
		.get("https://www.google.com");
//		.then()
//		.cookie("AEC","AVcja2f5Fcqnj_9no1Ht2IrSX82VjzAPSNgjoHaXzlK9uqoD9Vgoqmpq0yE")
//		.log().all();
		
		String cookie = res.getCookie("AEC");
		System.out.println("Cookie value is "+cookie);
		
		Map<String, String> cookies = res.getCookies();
		//System.out.println(cookies.keySet());
		
		for(String k:cookies.keySet()) {
			
			String cookie_Value = res.getCookie(k);
			
			System.out.println(k+" : "+cookie_Value);
		}
	
	}

}
