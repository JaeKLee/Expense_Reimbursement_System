package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reimbursement;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	@Override
	public boolean insertNewReimbWithDesc(Reimbursement reimb) {
		boolean insertBool = false;
		services.MainDriver.logg.debug("-------Inside insertNewReimbWithDesc-------");
		try(Connection conn = ProjectOneConnection.getConnection()) {
			services.MainDriver.logg.debug("-------Inside insertNewReimbWithDesc try statement-------");
			String sql = "INSERT INTO ers_reimbursement "
					+ "(REIMB_AMOUNT, REIMB_SUBMITTED, reimb_description, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID) "
					+ "values(?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, reimb.getReimbAmount() );
			ps.setTimestamp(2, reimb.getSubmittedTime() );
			ps.setString(3, reimb.getDescription());
			ps.setInt(4, reimb.getReimbAuthorId());
			ps.setInt(5, reimb.getReimbStatusId());
			ps.setInt(6, reimb.getReimbTypeId());
			ps.executeUpdate();
			insertBool = true;
			services.MainDriver.logg.debug("-------Exiting insertNewReimbWithDesc try statement-------");
		}catch(SQLException e) {
			System.out.println("SQL Connection error");
			services.MainDriver.logg.fatal(e);
		}
		
		services.MainDriver.logg.debug("-------Exiting insertNewReimbWithDesc-------");
		return insertBool;

	}

	@Override
	public boolean insertNewReimbWithoutDesc(Reimbursement reimb) {
		services.MainDriver.logg.debug("-------Inside insertNewReimbWithoutDesc-------");
		try(Connection conn = ProjectOneConnection.getConnection()) {
			services.MainDriver.logg.debug("-------Inside insertNewReimbWithoutDesc try statement-------");
			String sql = "INSERT INTO ers_reimbursement "
					+ "(REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID) "
					+ "values(?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, reimb.getReimbAmount() );
			ps.setTimestamp(2, reimb.getSubmittedTime() );
			ps.setInt(3, reimb.getReimbAuthorId());
			ps.setInt(4, reimb.getReimbStatusId());
			ps.setInt(5, reimb.getReimbTypeId());
			ps.executeUpdate();
			services.MainDriver.logg.debug("-------Exiting insertNewReimbWithoutDesc try statement-------");
		}catch(SQLException e) {
			System.out.println("SQL Connection error");
			services.MainDriver.logg.fatal(e);
			return false;
		}
		services.MainDriver.logg.debug("-------Exiting insertNewReimbWithoutDesc-------");
		return true;
	}
	
	@Override
	public boolean updateReimbStatus(Reimbursement reimb) {
		services.MainDriver.logg.debug("-------Inside updateReimbStatus-------");
		try(Connection conn = ProjectOneConnection.getConnection()) {
			services.MainDriver.logg.debug("-------Inside updateReimbStatus try statement-------");
			String sql = "UPDATE ers_reimbursement SET reimb_resolved = ?, reimb_resolver = ?, reimb_status_id = ? WHERE reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, reimb.getResolvedTime() );
			ps.setInt(2, reimb.getReimbResolverId() );
			ps.setInt(3, reimb.getReimbStatusId());
			ps.setInt(4, reimb.getReimbId());
			ps.executeUpdate();
			services.MainDriver.logg.debug("-------Exiting updateReimbStatus try statement-------");
		}catch(SQLException e) {
			System.out.println("SQL Connection error");
			services.MainDriver.logg.fatal(e);
			return false;
		}
		services.MainDriver.logg.debug("-------Exiting updateReimbStatus-------");
		return true;
	}

	@Override
	public boolean updateReimbDesc(int reimbId, String reimbDesc) {
		services.MainDriver.logg.debug("-------Inside updateReimbDesc-------");
		try(Connection conn = ProjectOneConnection.getConnection()) {
			services.MainDriver.logg.debug("-------Inside updateReimbDesc try statement-------");
			String sql = "UPDATE ers_reimbursement SET reimb_description = ? WHERE reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, reimbDesc );
			ps.setInt(2, reimbId );
			ps.executeUpdate();
			services.MainDriver.logg.debug("-------Exiting updateReimbDesc try statement-------");
		}catch(SQLException e) {
			e.getStackTrace();
			System.out.println("SQL Connection error");
			services.MainDriver.logg.fatal(e);
			return false;
		}
		services.MainDriver.logg.debug("-------Exiting updateReimbDesc-------");
		return true;
	}
	
	@Override
	public Reimbursement selectReimbById(int reimbId) {
		services.MainDriver.logg.debug("-------Insite selectReimbById-------");
		Reimbursement selectReimb = null;
		
		try(Connection conn = ProjectOneConnection.getConnection()) {
			services.MainDriver.logg.debug("-------Insite selectReimbById try statement-------");
			String sql = "SELECT * FROM ers_reimb_view where REIMB_ID=? ORDER BY ers_status DESC, reimb_id ASC;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				selectReimb = new Reimbursement(
						  rs.getInt("REIMB_ID")
						, rs.getDouble("REIMB_AMOUNT")
						, rs.getTimestamp("REIMB_SUBMITTED")
						, rs.getTimestamp("REIMB_RESOLVED")
						, rs.getString("REIMB_DESCRIPTION")
						, rs.getInt("REIMB_AUTHOR")
						, rs.getInt("REIMB_RESOLVER")
						, rs.getInt("REIMB_STATUS_ID")
						, rs.getInt("REIMB_TYPE_ID")
						, rs.getString("ers_resolver_first_name")
						, rs.getString("ers_resolver_last_name")
						, rs.getString("ers_author_first_name")
						, rs.getString("ers_author_last_name")
						, rs.getString("ers_type")
						, rs.getString("ers_status") );
			}
			services.MainDriver.logg.debug("-------Exiting selectReimbById try statement-------");
		} catch (SQLException e) {
			System.out.println("SQL Connection error");
			services.MainDriver.logg.fatal(e);
		}
		services.MainDriver.logg.debug("-------Exiting selectReimbById-------");
		return selectReimb;
	}

	@Override
	public List<Reimbursement> selectAllReimb() {
		List<Reimbursement> selectAllReimb = new ArrayList<>();
		
		try(Connection conn = ProjectOneConnection.getConnection()) {
			String sql = "SELECT * FROM ers_reimb_view ORDER BY ers_status DESC, reimb_id ASC;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				selectAllReimb.add(new Reimbursement(
						  rs.getInt("REIMB_ID")
						, rs.getDouble("REIMB_AMOUNT")
						, rs.getTimestamp("REIMB_SUBMITTED")
						, rs.getTimestamp("REIMB_RESOLVED")
						, rs.getString("REIMB_DESCRIPTION")
						, rs.getInt("REIMB_AUTHOR")
						, rs.getInt("REIMB_RESOLVER")
						, rs.getInt("REIMB_STATUS_ID")
						, rs.getInt("REIMB_TYPE_ID")
						, rs.getString("ers_resolver_first_name")
						, rs.getString("ers_resolver_last_name")
						, rs.getString("ers_author_first_name")
						, rs.getString("ers_author_last_name")
						, rs.getString("ers_type")
						, rs.getString("ers_status") ) );
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println("SQL Connection error");
		}
		return selectAllReimb;
	}
	
	@Override
	public List<Reimbursement> selectAllReimbByAuthor(String authorFirstName, String authorLastName) {
		List<Reimbursement> selectAllReimbById = new ArrayList<>();
		
		try(Connection conn = ProjectOneConnection.getConnection()) {
			String sql = "SELECT * FROM ers_reimb_view WHERE ers_author_first_name =? and ers_author_last_name=? ORDER BY ers_status DESC, reimb_id ASC;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, authorFirstName);
			ps.setString(2, authorLastName);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				selectAllReimbById.add(new Reimbursement(
						  rs.getInt("REIMB_ID")
						, rs.getDouble("REIMB_AMOUNT")
						, rs.getTimestamp("REIMB_SUBMITTED")
						, rs.getTimestamp("REIMB_RESOLVED")
						, rs.getString("REIMB_DESCRIPTION")
						, rs.getInt("REIMB_AUTHOR")
						, rs.getInt("REIMB_RESOLVER")
						, rs.getInt("REIMB_STATUS_ID")
						, rs.getInt("REIMB_TYPE_ID")
						, rs.getString("ers_resolver_first_name")
						, rs.getString("ers_resolver_last_name")
						, rs.getString("ers_author_first_name")
						, rs.getString("ers_author_last_name")
						, rs.getString("ers_type")
						, rs.getString("ers_status") ) );
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println("SQL Connection error");
		}
		return selectAllReimbById;
	}
	
	@Override
	public List<Reimbursement> selectAllReimbByStatus(int reimbStatusId) {
		List<Reimbursement> selectAllReimbByStatus = new ArrayList<>();
		
		try(Connection conn = ProjectOneConnection.getConnection()) {
			String sql = "SELECT * FROM ers_reimb_view WHERE REIMB_STATUS_ID=? ORDER BY ers_status DESC, reimb_id ASC;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbStatusId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				selectAllReimbByStatus.add(new Reimbursement(
						  rs.getInt("REIMB_ID")
						, rs.getDouble("REIMB_AMOUNT")
						, rs.getTimestamp("REIMB_SUBMITTED")
						, rs.getTimestamp("REIMB_RESOLVED")
						, rs.getString("REIMB_DESCRIPTION")
						, rs.getInt("REIMB_AUTHOR")
						, rs.getInt("REIMB_RESOLVER")
						, rs.getInt("REIMB_STATUS_ID")
						, rs.getInt("REIMB_TYPE_ID")
						, rs.getString("ers_resolver_first_name")
						, rs.getString("ers_resolver_last_name")
						, rs.getString("ers_author_first_name")
						, rs.getString("ers_author_last_name")
						, rs.getString("ers_type")
						, rs.getString("ers_status") ) );
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println("SQL Connection error");
		}
		return selectAllReimbByStatus;
	}

}
