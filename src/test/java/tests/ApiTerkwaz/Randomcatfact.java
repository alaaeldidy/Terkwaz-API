package tests.ApiTerkwaz;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Randomcatfact {

	
	public static RequestSpecification requestSpec;	
	
	
@BeforeClass
public void declaration()
{
	requestSpec=new RequestSpecBuilder().setBaseUri("https://cat-fact.herokuapp.com")
			.build();

}	
	

@Test
public void getcatfactrandomly() throws IOException
{

	Response createrequest= given().log().all().spec(requestSpec).queryParam("animal_type","cat").queryParam("amount","1")
		          .get("/facts/random").then().log().all()
		          .contentType(ContentType.JSON).statusCode(200).extract().response();
	
	
	
	String responsebody=given().log().all().spec(requestSpec).queryParam("animal_type","cat").queryParam("amount","1")
	          .get("/facts/random").then().statusCode(200)
	  		.extract().asString();
	
	@SuppressWarnings("unused")
	File fileObj = new File("response.json");
	FileWriter myWriter = new FileWriter("response.json");
	myWriter.write(responsebody);
	myWriter.close();
	
	
	String responsetext=createrequest.jsonPath().getString("text");
	 assertNotNull(responsetext);
	
	 


	  
}





}
