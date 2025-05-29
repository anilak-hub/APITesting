package day5;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
//Make sure to import jackson ObjectMapper (NOT io.object Mapper)
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.StudentPOJO;

import static io.restassured.matcher.RestAssuredMatchers.*;


// POJO ---Serilize--> JSON object -- de-serilize---->POJO
public class SerilizationDeserilization {

	
	//POJO ------> JSON (Serilization)
	@Test(priority = 1)
	void convertPOJO_to_JSON() throws JsonProcessingException {
		StudentPOJO student = new StudentPOJO();
		
		student.setName("Anil");
		student.setLocation("AP");
		student.setPhone("938402398");
		student.setCourses(Arrays.asList("Java","RESTassured"));
    	
		// Convert java object---> JsonObject (serialization)
		
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
		System.out.println(jsonData);
		
		
		//JSON--->POJO (De-Serilization) Same class JsonData
		StudentPOJO studentpojo = objMapper.readValue(jsonData, StudentPOJO.class);
		
		System.out.println("Name: "+studentpojo.getName());
		System.out.println("Location: "+studentpojo.getLocation());
		System.out.println("Phone No: "+studentpojo.getPhone());
		System.out.println("Courses: "+studentpojo.getCourses());
	}
	
	
	//JSON--->POJO (De-Serilization) Random JsonData
	@Test(priority = 2)
	void convertJson2Pojo() throws JsonMappingException, JsonProcessingException {
		String jsonData = "{\r\n"
				+ "  \"name\" : \"Anil\",\r\n"
				+ "  \"location\" : \"AP\",\r\n"
				+ "  \"phone\" : \"938402398\",\r\n"
				+ "  \"courses\" : [ \"Java\", \"RESTassured\" ]\r\n"
				+ "}";
		
		//convert JonData into POJO object
		
		ObjectMapper objMa = new ObjectMapper();
		StudentPOJO studentpojo = objMa.readValue(jsonData, StudentPOJO.class);
		System.out.println("Name: "+studentpojo.getName());
		System.out.println("Location: "+studentpojo.getLocation());
		System.out.println("Phone No: "+studentpojo.getPhone());
		System.out.println("Courses: "+studentpojo.getCourses());
		
		}
	
}
