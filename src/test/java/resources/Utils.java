package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	
	@SuppressWarnings("resource")
	public io.restassured.specification.RequestSpecification RequestSpecification() throws IOException {
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		
		requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalPropertiesValue("baseURI")).setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return requestSpecification;
	}
	
	public io.restassured.specification.ResponseSpecification ResponseSpecification() {
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return responseSpecification;
	}
	
	public static String getGlobalPropertiesValue(String Key) throws IOException	{	
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"src\\test\\java\\resources\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(Key);
		
	}
}
