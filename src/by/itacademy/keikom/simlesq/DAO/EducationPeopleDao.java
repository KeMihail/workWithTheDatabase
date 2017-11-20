package by.itacademy.keikom.simlesq.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.itacademy.keikom.simlesq.ConnectToDB;
import by.itacademy.keikom.simlesq.IDAO;
import by.itacademy.keikom.simlesq.entity.Education;
import by.itacademy.keikom.simlesq.entity.EducationPeople;
import by.itacademy.keikom.simlesq.entity.People;

public class EducationPeopleDao {

	Connection connect;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	String request;
	
	
	public void create(EducationPeople obj) {
		request = "insert into education_people (p_id,id_ed) values (?,?)";
		
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, obj.getP_id());
			ps.setInt(2, obj.getEd_id());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	
	public void delete(People people, Education inst) {
		request = "delete from education_people where p_id = ? and id_ed = ?";
		
		try {
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, people.getP_id());
			ps.setInt(2, inst.getEd_id());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	

	public void update(EducationPeople obj,EducationPeople newObj) {
		request = "update education_people set p_id = ?, id_ed = ? where p_id = ? and id_ed = ?";
		
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, newObj.getP_id());
			ps.setInt(2, newObj.getEd_id());
			ps.setInt(3, obj.getP_id());
			ps.setInt(4, obj.getEd_id());
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	public List<EducationPeople> allRecords() {
		List<EducationPeople> list = new ArrayList<EducationPeople>();
		request = "select * from education_people";
		
		try {
			connect = ConnectToDB.connectToDB.getConnect();
			st = connect.createStatement();
			rs = st.executeQuery(request);
			
			while (rs.next()) {
				list.add(new EducationPeople(rs.getInt(1), rs.getInt(2)));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (st != null) st.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return list;
	}

	public EducationPeople getById(Integer p_id,Integer ed_id) {
		request = "select * from education_people where p_id = ? and id_ed = ?";
		
		try {
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, p_id);
			ps.setInt(2, ed_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return new EducationPeople(rs.getInt(1), rs.getInt(2));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	public void showApersonsEducation(Integer id) {
		request = "select people.p_name,people.p_age,education.name_inst,education.adress_inst,education.comment "
				+ "from education_people join people on education_people.p_id = people.p_id "
				+ "join  education on education_people.id_ed = education.id_ed where people.p_id = ?";
		
		try {
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println(String.format("Человек: имя %s, возраст %s, Учебное заведение: название %s,адресс %s, коментарий %s",
						rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
		public void listOfStudentsOfInstitution (Integer id) {
			request = "select education.name_inst,education.comment,people.p_name\r\n" + 
					"from education_people\r\n" + 
					"inner join education on education_people.id_ed = education.id_ed\r\n" + 
					"inner join people on education_people.p_id = people.p_id where education.id_ed = ?";
		try {
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println(String.format("Учебное заведение: %s, коментарий %s, человек, который проходил обучение: %s",
						rs.getString(1),rs.getString(2),rs.getString(3)));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
