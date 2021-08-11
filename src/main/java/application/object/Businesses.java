/**
 * 
 */
package application.object;


/**
 * @author Billy Bissic
 *
 */
public class Businesses {
	
	private Business[] businesses;
	private Integer total;
	private Region region;
	
	/**
	 * @return the businesses
	 */
	public Business[] getBusinesses() {
		return businesses;
	}
	/**
	 * @param businesses the businesses to set
	 */
	public void setBusinesses(Business[] businesses) {
		this.businesses = businesses;
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
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
}
