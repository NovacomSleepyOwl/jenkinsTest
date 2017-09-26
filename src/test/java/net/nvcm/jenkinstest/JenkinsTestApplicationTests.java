package net.nvcm.jenkinstest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class JenkinsTestApplicationTests extends AbstractTestNGSpringContextTests {


	@Autowired
	private TestRestTemplate restTemplate;


	@DataProvider
	public Object[][]devideTestdata(){
		String a = "6";
		String b = "2";
		String b2 = "3";
		String error = "0";
		return new Object[][]{
				{a, b},
				{a, b2},
				{a, error}
		};
	}

	@Test(dataProvider = "devideTestdata")
	public void tryToCalcluate(String a, String b) {
		Assert.assertTrue(autoTest(a, b));

	}

	private boolean autoTest(String a, String b){
		Boolean isOk;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity body = new HttpEntity<>(headers);
			ResponseEntity<String> response = restTemplate.exchange("/" + a + "/" + b, HttpMethod.GET,  body, String.class );
			isOk = (response.getStatusCode() == HttpStatus.OK);

			System.out.println("\n\n-----------------------------");
			System.out.println(response.getBody());
			System.out.println("-----------------------------\n\n");
		}catch (Throwable e){
			isOk = false;
		}

		return isOk;
	}


}
