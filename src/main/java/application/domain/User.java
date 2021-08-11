/**
 * 
 */
package application.domain;

/**
 * @author Billy Bissic
 *
 */
public class User {
    private String id;
    private String profile_url;
    private String image_url;
    private String name;
    
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
	 * @return the profile_url
	 */
	public String getProfile_url() {
		return profile_url;
	}
	/**
	 * @param profile_url the profile_url to set
	 */
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
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
   
    
}
