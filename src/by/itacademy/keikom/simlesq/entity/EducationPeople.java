package by.itacademy.keikom.simlesq.entity;

public class EducationPeople {
	
	private Integer p_id;
	private Integer ed_id;
	
	public EducationPeople(Integer p_id, Integer ed_id) {
		
		this.p_id = p_id;
		this.ed_id = ed_id;
	}

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public Integer getEd_id() {
		return ed_id;
	}

	public void setEd_id(Integer ed_id) {
		this.ed_id = ed_id;
	}

	@Override
	public String toString() {
		return String.format("id people: %s, id esucation %s ",p_id, ed_id);
	}
	
}
