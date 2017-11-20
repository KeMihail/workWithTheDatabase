package by.itacademy.keikom.simlesq.entity;

public class People {

	private Integer p_id;
	private String p_name;
	private Integer p_age;
	private Integer p_org;
	
	public People() {}
	
	public People(String p_name, Integer p_age, Integer p_org) {
		super();
		this.p_name = p_name;
		this.p_age = p_age;
		this.p_org = p_org;
	}

	public People(Integer p_id, String p_name, Integer p_age, Integer p_org) {
		
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_age = p_age;
		this.p_org = p_org;
	}
	
	@Override
	public String toString() {
		return String.format("Имя: %s, возраст: %s, место работы: %s", p_name,p_age,p_org);
	}

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public Integer getP_age() {
		return p_age;
	}

	public void setP_age(Integer p_age) {
		this.p_age = p_age;
	}

	public Integer getP_org() {
		return p_org;
	}

	public void setP_org(Integer p_org) {
		this.p_org = p_org;
	}
	
	
}
