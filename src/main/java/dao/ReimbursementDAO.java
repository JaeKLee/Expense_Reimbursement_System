package dao;

import java.util.List;

import model.Reimbursement;

public interface ReimbursementDAO {
	// INSERTS
	public boolean insertNewReimbWithDesc(Reimbursement reimb);
	public boolean insertNewReimbWithoutDesc(Reimbursement reimb);

	// UPDATES
	public boolean updateReimbStatus(Reimbursement reimb);
	public boolean updateReimbDesc(int reimbId, String reimbDesc);

	// SELECTS
	public Reimbursement selectReimbById(int reimbId);
	public List<Reimbursement> selectAllReimb();
	public List<Reimbursement> selectAllReimbByAuthor(String authorFirstName, String authorLastName);
	public List<Reimbursement> selectAllReimbByStatus(int reimbStatusId);

}
