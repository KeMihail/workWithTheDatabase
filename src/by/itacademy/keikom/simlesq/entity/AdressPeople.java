package by.itacademy.keikom.simlesq.entity;

public class AdressPeople {

	private Integer adr_id;
	private String country;
	private String city;
	private String street;
	
	public AdressPeople() {}
	
	public AdressPeople(Integer adr_id, String country, String city, String street) {
		
		this.adr_id = adr_id;
		this.country = country;
		this.city = city;
		this.street = street;
	}

	public AdressPeople(String country, String city, String street) {
		
		this.country = country;
		this.city = city;
		this.street = street;
	}

	public Integer getAdr_id() {
		return adr_id;
	}

	public void setAdr_id(Integer adr_id) {
		this.adr_id = adr_id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return String.format("Страна: %s, город %s, улица: %s", country,city,street);
	}
	
	
}
