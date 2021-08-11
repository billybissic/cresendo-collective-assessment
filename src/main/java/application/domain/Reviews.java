/**
 * 
 */
package application.domain;

/**
 * @author Billy Bissic
 *
 */
public class Reviews {

	private Review[] reviews;
	private Integer total;
	private String[] possible_languages;
	
	/**
	 * @return the reviews
	 */
	public Review[] getReviews() {
		return reviews;
	}
	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(Review[] reviews) {
		this.reviews = reviews;
	}
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * @return the possible_languages
	 */
	public String[] getPossible_languages() {
		return possible_languages;
	}
	/**
	 * @param possible_languages the possible_languages to set
	 */
	public void setPossible_languages(String[] possible_languages) {
		this.possible_languages = possible_languages;
	}
	public Reviews(Review[] reviews, Integer total, String[] possible_languages) {
		this.reviews = reviews;
		this.total = total;
		this.possible_languages = possible_languages;
	}
	public Reviews() {
	}
	
	
}
