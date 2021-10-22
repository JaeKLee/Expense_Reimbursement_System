package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reimbursement;
import model.User;

public class UserDAOImpl implements UserDAO {
	public static void main(String[] args) {
		UserDAOImpl obj = new UserDAOImpl();
		System.out.println(obj.selectUserByUsername("yoru") );
	}

	@Override
	public User selectUserById(int userId) {
		User selectUser = null;
		services.MainDriver.logg.debug("-------Inside selectUserbyId-------");
		try (Connection conn = ProjectOneConnection.getConnection()) {
			services.MainDriver.logg.debug("-------Inside createReimb try-------");
			String sql = "SELECT * FROM ers_user_view where ERS_USERS_ID=? ORDER BY ERS_USERS_ID;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				selectUser = new User(rs.getInt("ERS_USERS_ID"), rs.getString("ERS_USERNAME"),
						rs.getString("ERS_PASSWORD"), rs.getString("USER_FRIST_NAME"), rs.getString("USER_LAST_NAME"),
						rs.getString("USER_EMAIL"), rs.getString("USER_ROLE"), rs.getInt("USER_ROLE_ID"), new ArrayList<Reimbursement>());
			}

			ReimbursementDAO reimb = new ReimbursementDAOImpl();
			
			if (selectUser != null) {
				List<Reimbursement> userReimbList = reimb.selectAllReimbByAuthor(selectUser.getFirstName(), selectUser.getLastName());
				selectUser.setAssocReimbList(userReimbList);
			}
			services.MainDriver.logg.debug("-------Exiting createReimb-------");
		} catch (SQLException e) {
//			e.getStackTrace();
			
			services.MainDriver.logg.fatal(e);
			System.out.println("SQL Connection error");
		}
		services.MainDriver.logg.debug("-------Exiting createReimb-------");
		return selectUser;
	}

	@Override
	public User selectUserByUsername(String userName) {
		User userByUserName = null;

		try (Connection conn = ProjectOneConnection.getConnection()) {
			String sql = "SELECT * FROM ers_user_view WHERE ERS_USERNAME = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				userByUserName = new User(rs.getInt("ERS_USERS_ID"), rs.getString("ERS_USERNAME"),
						rs.getString("ERS_PASSWORD"), rs.getString("USER_FRIST_NAME"), rs.getString("USER_LAST_NAME"),
						rs.getString("USER_EMAIL"), rs.getString("USER_ROLE"), rs.getInt("USER_ROLE_ID"), new ArrayList<Reimbursement>());
			}

			ReimbursementDAO reimb = new ReimbursementDAOImpl();
			
			if (userByUserName != null) {
				if(userByUserName.getUserRoleName().equalsIgnoreCase("Employee")) {
					List<Reimbursement> userReimbList = reimb.selectAllReimbByAuthor(userByUserName.getFirstName(), userByUserName.getLastName());
					userByUserName.setAssocReimbList(userReimbList);	
				} else if (userByUserName.getUserRoleName().equalsIgnoreCase("Manager")) {
					List<Reimbursement> getAllReimbs = reimb.selectAllReimb();
					userByUserName.setAssocReimbList(getAllReimbs);
				}
				
			}
			//System.out.println(userByUserName.getAssocReimbList());
		} catch (SQLException e) {
//			e.getStackTrace();
			services.MainDriver.logg.fatal(e);
			System.out.println("SQL Connection error");
		}

		return userByUserName;
	}

	@Override
	public User selectUserByEmail(String email) {
		User userByEmail = null;

		try (Connection conn = ProjectOneConnection.getConnection()) {
			String sql = "SELECT * FROM ers_user_view WHERE USER_EMAIL = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				userByEmail = new User(rs.getInt("ERS_USERS_ID"), rs.getString("ERS_USERNAME"),
						rs.getString("ERS_PASSWORD"), rs.getString("USER_FRIST_NAME"), rs.getString("USER_LAST_NAME"),
						rs.getString("USER_EMAIL"), rs.getString("USER_ROLE"), rs.getInt("USER_ROLE_ID"), new ArrayList<Reimbursement>());
			}

			ReimbursementDAO reimb = new ReimbursementDAOImpl();

			if (userByEmail != null) {
				List<Reimbursement> userReimbList = reimb.selectAllReimbByAuthor(userByEmail.getFirstName(), userByEmail.getLastName() );
				userByEmail.setAssocReimbList(userReimbList);
			}

		} catch (SQLException e) {
//			e.getStackTrace();
			System.out.println("SQL Connection error");
			services.MainDriver.logg.fatal(e);
		}

		return userByEmail;
	}

	@Override
	public User selectUserByUsernameOrEmail(String userName, String email, String password) {
		User userByUsernameOrEmail = null;

		try (Connection conn = ProjectOneConnection.getConnection()) {
			String sql = "SELECT * FROM ers_user_view eu WHERE (ers_username = ? OR user_email = ?) AND ers_password = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				userByUsernameOrEmail = new User(rs.getInt("ERS_USERS_ID"), rs.getString("ERS_USERNAME"),
						rs.getString("ERS_PASSWORD"), rs.getString("USER_FRIST_NAME"), rs.getString("USER_LAST_NAME"),
						rs.getString("USER_EMAIL"), rs.getString("USER_ROLE"), rs.getInt("USER_ROLE_ID"), new ArrayList<Reimbursement>());
			}

			ReimbursementDAO reimb = new ReimbursementDAOImpl();

			if (userByUsernameOrEmail != null) {
				List<Reimbursement> userReimbList = reimb.selectAllReimbByAuthor(userByUsernameOrEmail.getFirstName(), userByUsernameOrEmail.getLastName());
				userByUsernameOrEmail.setAssocReimbList(userReimbList);
			}

		} catch (SQLException e) {
//			e.getStackTrace();
			System.out.println("SQL Connection error");
			services.MainDriver.logg.fatal(e);
		}

		return userByUsernameOrEmail;
	}


}
