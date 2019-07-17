
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	private String				sistemName;
	private String				countryCode;
	private String				bannerURL;
	private String				welcomeEN;
	private String				welcomeSP;
	private Collection<String>	topics;
	private Collection<String>	makes;


	@NotBlank
	@Range(min = 1, max = 999)
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	@URL
	@NotBlank
	public String getBannerURL() {
		return this.bannerURL;
	}

	public void setBannerURL(final String bannerURL) {
		this.bannerURL = bannerURL;
	}
	@NotBlank
	public String getSistemName() {
		return this.sistemName;
	}

	public void setSistemName(final String sistemName) {
		this.sistemName = sistemName;
	}
	public String getWelcomeEN() {
		return this.welcomeEN;
	}

	public void setWelcomeEN(final String welcomeEN) {
		this.welcomeEN = welcomeEN;
	}
	public String getWelcomeSP() {
		return this.welcomeSP;
	}

	public void setWelcomeSP(final String welcomeSP) {
		this.welcomeSP = welcomeSP;
	}

	@ElementCollection
	public Collection<String> getTopics() {
		return this.topics;
	}

	public void setTopics(final Collection<String> topics) {
		this.topics = topics;
	}

	@ElementCollection
	public Collection<String> getMakes() {
		return this.makes;
	}

	public void setMakes(final Collection<String> makes) {
		this.makes = makes;
	}

}
