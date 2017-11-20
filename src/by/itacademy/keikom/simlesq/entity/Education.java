package by.itacademy.keikom.simlesq.entity;

public class Education {


	private Integer ed_id;
	private String inst_name;
	private String inst_adress;
	private String comment;
	
	public Education() {}
	
	public Education(Integer ed_id, String inst_name, String inst_adress, String comment) {
		super();
		this.ed_id = ed_id;
		this.inst_name = inst_name;
		this.inst_adress = inst_adress;
		this.comment = comment;
	}
	
	public Education(String inst_name, String inst_adress, String comment) {
		super();
		this.inst_name = inst_name;
		this.inst_adress = inst_adress;
		this.comment = comment;
	}
	public Integer getEd_id() {
		return ed_id;
	}

	public void setEd_id(Integer ed_id) {
		this.ed_id = ed_id;
	}

	public String getInst_name() {
		return inst_name;
	}

	public void setInst_name(String inst_name) {
		this.inst_name = inst_name;
	}

	public String getInst_adress() {
		return inst_adress;
	}

	public void setInst_adress(String inst_adress) {
		this.inst_adress = inst_adress;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return String.format("Учереждение образования: %s, адресс: %s, коментарий: %s ", inst_name,inst_adress,comment);
	}
}
