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
import by.itacademy.keikom.simlesq.entity.AdressPeople;

public class AdressPeopleDao implements IDAO<AdressPeople>{
	
	Connection connect;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	String request;

	@Override
	public void create(AdressPeople obj) {}
	
	public void create(AdressPeople obj, int id) {
		request = "insert into adress_people (adr_id,country, city,street) values (?,?,?,?)";
		
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, id);
			ps.setString(2, obj.getCountry());
			ps.setString(3, obj.getCity());
			ps.setString(4, obj.getStreet());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	@Override
	public Boolean delete(Integer id) {
		request = "delete  from adress_people where adr_id = ?";
		Integer result = 0;
		
		try {
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, id);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		if (result > 0) return true;
		else return false;
	}

	@Override
	public void update(AdressPeople obj) {
		request = "update adress_people set adr_id = ?,country = ?,city = ?, street = ? where adr_id = ?";
		
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, obj.getAdr_id());
			ps.setString(2, obj.getCountry());
			ps.setString(3, obj.getCity());
			ps.setString(4, obj.getStreet());
			ps.setInt(5, obj.getAdr_id());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public List<AdressPeople> allRecords() {
		request = "select * from adress_people";
		List<AdressPeople> list = new ArrayList<AdressPeople>();
		
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			st = connect.createStatement();
			rs = st.executeQuery(request);
			
			while (rs.next()) {
				list.add(new AdressPeople(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return list;
	}

	@Override
	public AdressPeople getById(Integer id) {
		request = "select * from adress_people where adr_id = ?";
		
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) return new AdressPeople(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	public void informationOnPlaceOfResidence(int id) {
		request = "select people.p_name, people.p_age, adress_people.country, adress_people.city, adress_people.street"
				+ " from people inner join adress_people on people.p_id = adress_people.adr_id where people.p_id = ?";
		
		try {
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				System.out.println(String.format("Имя: %s, возраст: %s, Меcто жительства: Страна %s, город %s, улица %s.",
						rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
