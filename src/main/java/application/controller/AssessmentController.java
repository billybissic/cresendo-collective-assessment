/**
 * 
 */
package application.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageSource;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	
	@RequestMapping(value = "/getYelpReviews", method = RequestMethod.GET, produces = "application/json")
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

			/* Removing for block until fixed. See notes inside of for for fix */
			//for (int i = 0; i < reviews.getReviews().length; i++) 
			//{
			//	String filePath = reviews.getReviews()[i].getUser().getImage_url();
				
				/* DUE TO THE FOLLOWING WARNING */
				/* Caution: When fetching images from HTTP/HTTPS URLs, Google cannot 
				 * guarantee that the request will be completed. Your request may fail 
				 * if the specified host denies the request (for example, due to request 
				 * throttling or DOS prevention), or if Google throttles requests to the 
				 * site for abuse prevention. You should not depend on externally-hosted 
				 * images for production applications. */
				/*TODO: Need to code procedure to download local image from yelp into local 
				 * directory. */
				
				/* Remove comments once the above has been implemented */
				//detectFacesGcs(filePath); /* for remote */
				//detectFaces(filePath); /* for local */
			//}
		    
			return new ResponseEntity<Reviews>(reviews, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}

	  // Detects faces in the specified remote image on Google Cloud Storage.
	  public static void detectFacesGcs(String gcsPath) throws IOException {
	    List<AnnotateImageRequest> requests = new ArrayList<>();

	    ImageSource imgSource = ImageSource.newBuilder().setGcsImageUri(gcsPath).build();
	    Image img = Image.newBuilder().setSource(imgSource).build();
	    Feature feat = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();

	    AnnotateImageRequest request =
	        AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
	    requests.add(request);

	    // Initialize client that will be used to send requests. This client only needs to be created
	    // once, and can be reused for multiple requests. After completing all of your requests, call
	    // the "close" method on the client to safely clean up any remaining background resources.
	    try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
	      BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
	      List<AnnotateImageResponse> responses = response.getResponsesList();

	      for (AnnotateImageResponse res : responses) {
	        if (res.hasError()) {
	          System.out.format("Error: %s%n", res.getError().getMessage());
	          return;
	        }

	        // For full list of available annotations, see http://g.co/cloud/vision/docs
	        for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
	          System.out.format(
	              "anger: %s%njoy: %s%nsurprise: %s%nposition: %s",
	              annotation.getAngerLikelihood(),
	              annotation.getJoyLikelihood(),
	              annotation.getSurpriseLikelihood(),
	              annotation.getBoundingPoly());
	        }
	      }
	    }
	  }
	  
	// Detects faces in the specified local image.
	  public static void detectFaces(String filePath) throws IOException {
	    List<AnnotateImageRequest> requests = new ArrayList<>();

	    ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

	    Image img = Image.newBuilder().setContent(imgBytes).build();
	    Feature feat = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();
	    AnnotateImageRequest request =
	        AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
	    requests.add(request);

	    // Initialize client that will be used to send requests. This client only needs to be created
	    // once, and can be reused for multiple requests. After completing all of your requests, call
	    // the "close" method on the client to safely clean up any remaining background resources.
	    try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
	      BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
	      List<AnnotateImageResponse> responses = response.getResponsesList();

	      for (AnnotateImageResponse res : responses) {
	        if (res.hasError()) {
	          System.out.format("Error: %s%n", res.getError().getMessage());
	          return;
	        }

	        // For full list of available annotations, see http://g.co/cloud/vision/docs
	        for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
	          System.out.format(
	              "anger: %s%njoy: %s%nsurprise: %s%nposition: %s",
	              annotation.getAngerLikelihood(),
	              annotation.getJoyLikelihood(),
	              annotation.getSurpriseLikelihood(),
	              annotation.getBoundingPoly());
	        }
	      }
	    }
	  }
	
	@RequestMapping(value = "/searchBusinessesByCategory", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> searchBusinessesByCategory(@RequestParam String latitude, @RequestParam String longitude, @RequestParam String categories) {
		try {
			OkHttpClient client = new OkHttpClient().newBuilder()
				      .build();
				    Request request = new Request.Builder()
				      .url("https://api.yelp.com/v3/businesses/search?latitude=" + latitude + "&longitude=" + longitude +"&categories=" + categories)
				      .method("GET", null)
				      .addHeader("Authorization", "Bearer " + apiKey)
				      .build();
				    Response response = client.newCall(request).execute();
				    ResponseBody responseBody = response.peekBody(Long.MAX_VALUE);
				    response.close();
				    
				    String json = responseBody.string();
				    Businesses businesses = new ObjectMapper().readValue(json, Businesses.class);
				    
				    System.out.println(response.body().string());
				    
				    return new ResponseEntity<Businesses>(businesses, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
}
