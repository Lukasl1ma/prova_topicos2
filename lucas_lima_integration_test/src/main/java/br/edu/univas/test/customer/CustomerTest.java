package br.edu.univas.test.customer;

import static org.junit.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import br.edu.univas.test.customer.dto.CustomerDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CustomerTest {
	
	private static final String CUSTOMER_URL = "http://localhost:8082/customers/";
	private static final String BASE_URL = "http://localhost:8082/";
	
	@Before
	public void setUp() {
		RestAssured.baseURI = BASE_URL;
	}
	
	@Test
	public void testCreateCustomer_withSuccess() {
		String id = "1";
		Response response = createCustomerWithId(id);
		response.then().assertThat().statusCode(HttpStatus.SC_CREATED);
	}

	@Test
	public void testCreateCustomer_withExistingCode() {
		String id = "1";
		Response response = RestAssured.get(CUSTOMER_URL + id);

		if (response.getStatusCode() == HttpStatus.SC_OK) {
			RestAssured.given()
				.contentType(ContentType.JSON)
				.post(CUSTOMER_URL)
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_BAD_REQUEST);
		} else {
			Response write = createCustomerWithId(id);
			write.then().assertThat().statusCode(HttpStatus.SC_CREATED);
		}
	}

	@Test
	public void testCreateCustomer_withWrongJson() {
		RestAssured.given()
			.contentType(ContentType.JSON)
			.post(CUSTOMER_URL)
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void testGetCustomerById_withSuccess() {
		String id = "1";
		createCustomerWithId(id);
		Response response = RestAssured.get(CUSTOMER_URL + id);
		response.then().assertThat().statusCode(HttpStatus.SC_OK);
	}

	@Test
	public void testGetCustomerById_withFail() {
		String nonExistingCustomerId = "1";
		Response response = RestAssured.get(BASE_URL + nonExistingCustomerId);
		response.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
	}


	@Test
	public void testPutCustomerById_withSuccess() {
		String id = "1";
		Response action = RestAssured.put(BASE_URL + id);
		action.then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
		assertEquals(HttpStatus.SC_NO_CONTENT, action.getStatusCode());

		Response response = RestAssured.get(BASE_URL + id);
		response.then().assertThat().statusCode(HttpStatus.SC_OK);
	}

	@Test
	public void testPutCustomer_withExistingCode() {
		String id = "1";
		Response response = RestAssured.get(BASE_URL + id);
		if (response.getStatusCode() == HttpStatus.SC_OK) {
			Response action = RestAssured.put(BASE_URL + id);
			action.then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
			assertEquals(HttpStatus.SC_NO_CONTENT, action.getStatusCode());
		} else {
			response.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
		}
	}

	private Response createCustomerWithId(String idNumber) {
		CustomerDTO newCustomer = new CustomerDTO(idNumber, "lucas", "lucas@email.com", "123456789");

		Response response = RestAssured.given()
			.body(newCustomer)
			.contentType(ContentType.JSON)
			.post(CUSTOMER_URL);
		return response;
	}

}
