/**
 * 
 */
package application.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import application.domain.Reviews;
import application.object.Business;
import application.object.Businesses;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Billy Bissic
 *
 */
@Controller
@RequestMapping(path = "/CollectiveController")
@Component
public class AssessmentController {

	@Value("${api.key}")
	private String apiKey;
	
	@RequestMapping(value = "/getYelpReviews/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getYelpReviews(@RequestParam String latitude, @RequestParam String longitude) {
		try {
			
			OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(0, TimeUnit.MILLISECONDS).build();
			Request request = new Request.Builder()
					.url("https://api.yelp.com/v3/businesses/search?latitude=" + latitude + "&longitude=" + longitude)
					.method("GET", null)
					.addHeader("Authorization", "Bearer " + apiKey)
					.build();
			Response response = client.newCall(request).execute();
			ResponseBody responseBody = response.peekBody(Long.MAX_VALUE);
			response.close();

			String json = responseBody.string();
			Businesses businesses = new ObjectMapper().readValue(json, Businesses.class);
			Business business = businesses.getBusinesses()[0];
			String businessId = business.getId();

			OkHttpClient reviewClient = new OkHttpClient().newBuilder().build();
			Request reviewRequest = new Request.Builder()
					.url("https://api.yelp.com/v3/businesses/" + businessId + "/reviews").method("GET", null)
					.addHeader("Authorization", "Bearer " + apiKey)
					.build();
			Response reviewResponse = reviewClient.newCall(reviewRequest).execute();
			ResponseBody reviewsResponseBody = reviewResponse.peekBody(Long.MAX_VALUE);
			reviewResponse.close();

			String reviewsJson = reviewsResponseBody.string();
			Reviews reviews = new ObjectMapper().readValue(reviewsJson, Reviews.class);

			return new ResponseEntity<Reviews>(reviews, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
}
