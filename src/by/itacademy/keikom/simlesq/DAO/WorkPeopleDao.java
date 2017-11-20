package by.itacademy.keikom.simlesq.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.itacademy.keikom.simlesq.ConnectToDB;
import by.itacademy.keikom.simlesq.IDAO;
import by.itacademy.keikom.simlesq.entity.WorkPeople;

public class WorkPeopleDao implements IDAO<WorkPeople> {

	Connection connect;
	String request;
	java.sql.PreparedStatement pr;
	Statement st;
	ResultSet rs;

	@Override
	public void create(WorkPeople obj) {

		connect = ConnectToDB.connectToDB.getConnect();
		request = "insert into work_people(org_name,org_adress) values(?,?)";

		try {

			pr = connect.prepareStatement(request);
			pr.setString(1, obj.getOrg_name());
			pr.setString(2, obj.getOrg_adress());
			pr.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		finally {

			if (connect != null)
				ConnectToDB.connectToDB.closeConnect();

			if (pr != null)
				try {
					if (pr != null)	pr.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
	}

	@Override
	public Boolean delete(Integer id) {

		Integer result = 0;
		connect = ConnectToDB.connectToDB.getConnect();
		request = "delete from work_people where org_id = ?";

		try {

			pr = connect.prepareStatement(request);
			pr.setInt(1, id);
			result = pr.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		finally {

			if (connect != null)
				ConnectToDB.connectToDB.closeConnect();

			if (pr != null)
				try {
					pr.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		if (result > 0) return true; else return false;
	}

	@Override
	public void update(WorkPeople obj) {

		connect = ConnectToDB.connectToDB.getConnect();
		request = "update  work_people  set org_name = ?,org_adress = ? where org_id = ?";

		try {

			pr = connect.prepareStatement(request);
			pr.setString(1, obj.getOrg_name());
			pr.setString(2, obj.getOrg_adress());
			pr.setInt(3, obj.getOrg_id());
			pr.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			if (connect != null)
				ConnectToDB.connectToDB.closeConnect();

			if (pr != null)
				try {
					pr.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}

	}

	@Override
	public List<WorkPeople> allRecords() {

		connect = ConnectToDB.connectToDB.getConnect();
		List<WorkPeople> list = new ArrayList<WorkPeople>();
		WorkPeople wp;
		request = "select * from work_people";

		try {

			st = connect.createStatement();
			rs = st.executeQuery(request);

			while (rs.next()) {
				wp = new WorkPeople();
				wp.setOrg_id(rs.getInt(1));
				wp.setOrg_name(rs.getString(2));
				wp.setOrg_adress(rs.getString(3));
				list.add(wp);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			if (connect != null)
				ConnectToDB.connectToDB.closeConnect();

			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return list;
	}

	@Override
	public WorkPeople getById(Integer id) {

		connect = ConnectToDB.connectToDB.getConnect();
		WorkPeople wp = new WorkPeople();
		request = "select * from work_people where org_id = ?";

		try {

			pr = connect.prepareStatement(request);
			pr.setInt(1, id);
			rs = pr.executeQuery();

			if (rs.next()) {
				wp.setOrg_id(rs.getInt("org_id"));
				wp.setOrg_name(rs.getString("org_name"));
				wp.setOrg_adress(rs.getString("org_adress"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			if (connect != null)
				ConnectToDB.connectToDB.closeConnect();

			if (pr != null)
				try {
					pr.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return wp;
	}
}
