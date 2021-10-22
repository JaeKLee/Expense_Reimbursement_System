package services;

import java.util.List;

import model.Reimbursement;

public interface ReimbursementService {
		// INSERTS
		public boolean addNewReimbWithDesc(Reimbursement reimb);
		public boolean addNewReimbWithoutDesc(Reimbursement reimb);

		// UPDATES
		public boolean updateReimbStatus(Reimbursement reimb);
		public boolean updateReimbDesc(int reimbId, String reimbDesc);

		// SELECTS
		
		public Reimbursement findReimbById(int reimbId);

		public List<Reimbursement> findAllReimb();
		public List<Reimbursement> findAllReimbByStatus(int reimbStatusId);

}
