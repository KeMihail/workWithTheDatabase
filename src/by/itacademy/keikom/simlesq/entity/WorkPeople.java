package by.itacademy.keikom.simlesq.entity;

public class WorkPeople {

	private Integer org_id;
	private String org_name;
	private String org_adress;

	public WorkPeople() {
	}

	
	public WorkPeople(Integer org_id, String org_name, String org_adress) {
		super();
		this.org_id = org_id;
		this.org_name = org_name;
		this.org_adress = org_adress;
	}


	public WorkPeople(String org_name, String org_adress) {

		this.org_name = org_name;
		this.org_adress = org_adress;
	}


	public Integer getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Integer org_id) {
		this.org_id = org_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getOrg_adress() {
		return org_adress;
	}

	public void setOrg_adress(String org_adress) {
		this.org_adress = org_adress;
	}
	
	@Override
	public String toString() {
		return String.format("Место работы: Организация: %s, адрес: %s", org_name,org_adress);
	}
	

}
