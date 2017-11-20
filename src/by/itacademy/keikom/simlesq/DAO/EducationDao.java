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

public class EducationDao implements IDAO<Education> {

	Connection connect;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	String request;

	@Override
	public void create(Education obj) {
		request = "insert into education (name_inst,adress_inst,comment) values(?,?,?)";

		try {
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setString(1, obj.getInst_name());
			ps.setString(2, obj.getInst_adress());
			ps.setString(3, obj.getComment());
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
		request = "delete from education where id_ed = ?";
		Integer result = 0;

		try {

			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, id);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		if (result > 0)
			return true;
		else
			return false;
	}

	@Override
	public void update(Education obj) {
		request = "update education set name_inst = ?,adress_inst = ?,comment = ? where id_ed = ?";

		try {

			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setString(1, obj.getInst_name());
			ps.setString(2, obj.getInst_adress());
			ps.setString(3, obj.getComment());
			ps.setInt(4, obj.getEd_id());
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (connect != null) connect.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public List<Education> allRecords() {
		List<Education> list = new ArrayList<Education>();
		request = "select * from education";
		
		try {
			connect = ConnectToDB.connectToDB.getConnect();
			st = connect.createStatement();
			rs = st.executeQuery(request);
			
			while (rs.next()) {
				list.add(new Education(rs.getInt("id_ed"), rs.getString("name_inst"),
						rs.getString("adress_inst"), rs.getString("comment")));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {
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
	public Education getById(Integer id) {
		request = "select * from education where id_ed = ?";
		
		try {
			
			connect = ConnectToDB.connectToDB.getConnect();
			ps = connect.prepareStatement(request);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Education(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
		return null;
	}
}
