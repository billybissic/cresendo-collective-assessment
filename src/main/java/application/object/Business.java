/**
 * 
 */
package application.object;

import java.io.Serializable;

/**
 * @author Billy Bissic
 *
 */

public class Business implements Serializable {

	private String id;
	private String alias;
	private String name;
	private String image_url;
	private boolean is_closed;
	private String url;
	private Integer review_count;
	private Iterable<Category> categories;
	private Integer rating;
	private Coordinates coordinates;
	private Iterable<String> transactions;
	private String price;
	private Location location;
	private String phone;
	private String display_phone;
	private Integer distance;
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
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the image_url
	 */
	public String getImage_url() {
		return image_url;
	}
	/**
	 * @param image_url the image_url to set
	 */
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	/**
	 * @return the is_closed
	 */
	public boolean isIs_closed() {
		return is_closed;
	}
	/**
	 * @param is_closed the is_closed to set
	 */
	public void setIs_closed(boolean is_closed) {
		this.is_closed = is_closed;
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
	 * @return the review_count
	 */
	public Integer getReview_count() {
		return review_count;
	}
	/**
	 * @param review_count the review_count to set
	 */
	public void setReview_count(Integer review_count) {
		this.review_count = review_count;
	}
	/**
	 * @return the categories
	 */
	public Iterable<Category> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Iterable<Category> categories) {
		this.categories = categories;
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
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}
	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	/**
	 * @return the transactions
	 */
	public Iterable<String> getTransactions() {
		return transactions;
	}
	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(Iterable<String> transactions) {
		this.transactions = transactions;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the display_phone
	 */
	public String getDisplay_phone() {
		return display_phone;
	}
	/**
	 * @param display_phone the display_phone to set
	 */
	public void setDisplay_phone(String display_phone) {
		this.display_phone = display_phone;
	}
	/**
	 * @return the distance
	 */
	public Integer getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
	public Business(String id, String alias, String name, String image_url, boolean is_closed, String url,
			Integer review_count, Iterable<Category> categories, Integer rating, Coordinates coordinates,
			Iterable<String> transactions, String price, Location location, String phone, String display_phone,
			Integer distance) {
		this.id = id;
		this.alias = alias;
		this.name = name;
		this.image_url = image_url;
		this.is_closed = is_closed;
		this.url = url;
		this.review_count = review_count;
		this.categories = categories;
		this.rating = rating;
		this.coordinates = coordinates;
		this.transactions = transactions;
		this.price = price;
		this.location = location;
		this.phone = phone;
		this.display_phone = display_phone;
		this.distance = distance;
	}
	
	public Business() {
	}
}
