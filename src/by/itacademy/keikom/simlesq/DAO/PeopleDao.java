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
import by.itacademy.keikom.simlesq.entity.People;

public class PeopleDao implements IDAO<People> {

	String request;
	Connection connect;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public void create(People obj) {
		request = "insert into people(p_name,p_age,org_id) values(?,?,?)";

		try {

			connect = ConnectToDB.connectToDB.getConnect();

			ps = connect.prepareStatement(request);
			ps.setString(1, obj.getP_name());
			ps.setInt(2, obj.getP_age());
			ps.setInt(3, obj.getP_org());
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (connect != null)
					connect.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public Boolean delete(Integer id) {
		request = "delete from people where p_id = ?";
		Integer result = null;

		try {

			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, id);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (rs != null) rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		if (result < 1) return false;
		else return true;
	}

	@Override
	public void update(People obj) {
		request = "update people set p_name = ?,p_age = ?,org_id = ? where p_id = ?";
		
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setString(1, obj.getP_name());
			ps.setInt(2, obj.getP_age());
			ps.setInt(3, obj.getP_org());
			ps.setInt(4, obj.getP_id());
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
			if (connect != null) connect.close();
			if (rs != null) rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public List<People> allRecords() {
		List<People> list = new ArrayList<People>();
		request = "select * from people";
		
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			st = connect.createStatement();
			rs = st.executeQuery(request);
			
			while(rs.next()) {
				list.add(new People(rs.getInt("p_id"), rs.getString("p_name"), rs.getInt("p_age"), rs.getInt("org_id")));
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (st != null) st.close();
				if (rs != null) rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return list;
	}

	@Override
	public People getById(Integer id) {
		request = " select * from people where p_id = ?";
		
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				return   new People(rs.getInt("p_id"), rs.getString("p_name"), rs.getInt("p_age"), rs.getInt("org_id"));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return null;
	}

	public void dataOnPlaceOfWork(int id) {
		request = "select people.p_name, people.p_age, work_people.org_name, work_people.org_adress "
				+ "from people inner join work_people on people.org_id = work_people.org_id where p_id = ?";
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				System.out.println(String.format("Имя: %s, возраст: %s, Место работы: Организация: %s, адрес: %s",
						rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
