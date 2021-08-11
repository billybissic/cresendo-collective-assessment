/**
 * 
 */
package application.domain;

/**
 * @author Billy Bissic
 *
 */
public class Review {

	private String id;
	private String url;
	private String text;
	private Integer rating;
	private String time_created;
	private User user;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the rating
	 */
	public Integer getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	/**
	 * @return the time_created
	 */
	public String getTime_created() {
		return time_created;
	}
	/**
	 * @param time_created the time_created to set
	 */
	public void setTime_created(String time_created) {
		this.time_created = time_created;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	public Review(String id, String url, String text, Integer rating, String time_created, User user) {
		this.id = id;
		this.url = url;
		this.text = text;
		this.rating = rating;
		this.time_created = time_created;
		this.user = user;
	}
	public Review() {
	}
	
	
}
