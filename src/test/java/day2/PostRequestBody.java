package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


/*
 * Different Ways of creating POST request body
 * 1) HashMap
 * 2)Using org.json(JSON Library)
 * 3)using POJO(Plain Old Java Object)
 * Using external json file
 */


public class PostRequestBody {

    static String studentId;

   // @BeforeMethod
    void cleanupTestData() {
        // Fetch all students
        Response response = given()
            .contentType(ContentType.JSON)
        .when()
            .get("http://localhost:3000/students");

        // Extract all IDs and delete them
        response.jsonPath().getList("id").forEach(id -> {
            given()
            .when()
                .delete("http://localhost:3000/students/" + id)
            .then()
                .statusCode(anyOf(equalTo(200), equalTo(204)));
        });

        System.out.println("✔ Test data cleaned up.");
    }

    @Test(priority = 1)
    void testPostUsingHashMap() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "scott");
        data.put("location", "France");
        data.put("phone", "0928e09128");
        data.put("courses", new String[]{"C", "C++"});

        Response response = given()
            .contentType(ContentType.JSON)
            .body(data)
        .when()
            .post("http://localhost:3000/students");

        studentId = response.jsonPath().getString("id");

        response.then()
            .statusCode(201)
            .body("name", equalTo("scott"))
            .body("location", equalTo("France"))
            .body("phone", equalTo("0928e09128"))
            .body("courses[0]", equalTo("C"))
            .body("courses[1]", equalTo("C++"))
            .header("Content-Type", containsString("application/json"))
            .log().all();
    }
    
    

   // @Test(priority = 2)
    void testDeleteStudent() {
        given()
        .when()
            .delete("http://localhost:3000/students/" + studentId)
        .then()
            .statusCode(anyOf(equalTo(200), equalTo(204)))
            .log().all();
    }
    @Test(priority = 2)
    void testPostUsingJsonLibrary() {
    	JSONObject data = new JSONObject();
    	data.put("name", "Anil");
    	data.put("location", "Bangalore");
    	data.put("phone", "0912840912");
    	String[] courseArr= {"java","Selenium"};
    	
    	data.put("courses",courseArr);
    	
    	given()
    	.contentType(ContentType.JSON)
    	
    	//If the data is in JSON file then we have to convert the data into String
    	.body(data.toString())
    	.when()
    	.post("http://localhost:3000/students")
    	.then()
    	.statusCode(anyOf(equalTo(200), equalTo(201)))
    	
    	.body("name",equalTo("Anil"))
    	.body("location",equalTo("Bangalore"))
    	.body("phone",equalTo("0912840912"))
    	.body("courses[0]",equalTo("java"))
    	.body("courses[1]",equalTo("Selenium"))
    	.log().all();
    	
    }
    
    @Test
    void postDataUsingPOJO_Class() {
    	
    	//creating object of StudentPOJO
    	StudentPOJO data = new StudentPOJO();
    	
    	data.setName("Anil");
    	data.setLocation("AP");
    	data.setPhone("938402398");
    	data.setCourses(Arrays.asList("Java","RESTassured"));
    	
    	given()
    	.contentType(ContentType.JSON)
    	.body(data)
    	.when()
    	.post("http://localhost:3000/students")
    	.then()
    	.statusCode(anyOf(equalTo(201),equalTo(201)))
    	.body("name",equalTo("Anil"))
    	.body("location",equalTo("AP"))
    	.body("phone",equalTo("938402398"))
    //	.body("name",equalTo("Anil"))
    	.log().all();
    	
    }
    
    @Test
    void postDataUsingExternalJsonFile() throws IOException {
    	
    	//Given the path of External JSON File
    	File f = new File(".\\body.json");
    	FileReader fr = new FileReader(f);
    	JSONTokener jt = new JSONTokener(fr);
    	JSONObject data = new JSONObject(jt);
    	
    	given()
    	.contentType(ContentType.JSON)
    	
    	//If the data is in JSON file then we have to convert the data into String
    	.body(data.toString())
    	.when()
    	.post("http://localhost:3000/students")
    	.then()
    	.statusCode(anyOf(equalTo(201),equalTo(201)))
    	.body("name",equalTo("Scott"))
    	.body("location",equalTo("France"))
    	.body("phone",equalTo("12345"))
    	.body("courses[0]",equalTo("C"))
    	.body("courses[1]",equalTo("Python"))
    //	.body("name",equalTo("Anil"))
    	.log().all();
    }
}
