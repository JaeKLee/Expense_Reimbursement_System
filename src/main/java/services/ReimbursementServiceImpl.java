package services;

import java.util.List;

import dao.ReimbursementDAO;
import dao.ReimbursementDAOImpl;
import model.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {
	
	ReimbursementDAO ersReimb;
	
	public ReimbursementServiceImpl(ReimbursementDAO reimbDao) {
		super();
		this.ersReimb = reimbDao;
	}
	
	public void setReimbDao(ReimbursementDAO reimbDao) {
		this.ersReimb = reimbDao;
	}

	public static void main(String[] args) {
		ReimbursementServiceImpl obj = new ReimbursementServiceImpl(new ReimbursementDAOImpl());
		System.out.println(obj.findAllReimb());
		
	}

	@Override
	public boolean addNewReimbWithDesc(Reimbursement reimb) {
		return ersReimb.insertNewReimbWithDesc(reimb);
	}

	@Override
	public boolean addNewReimbWithoutDesc(Reimbursement reimb) {
		
		return ersReimb.insertNewReimbWithoutDesc(reimb);
	}

	@Override
	public boolean updateReimbStatus(Reimbursement reimb) {
		return ersReimb.updateReimbStatus(reimb);
	}

	@Override
	public boolean updateReimbDesc(int reimbId, String reimbDesc) {
		return ersReimb.updateReimbDesc(reimbId, reimbDesc);
	}
	
	@Override
	public Reimbursement findReimbById(int reimbId) {
		return ersReimb.selectReimbById(reimbId);
	}

	@Override
	public List<Reimbursement> findAllReimb() {
		return ersReimb.selectAllReimb();
	}

	@Override
	public List<Reimbursement> findAllReimbByStatus(int reimbStatusId) {
		return ersReimb.selectAllReimbByStatus(reimbStatusId);
	}

	

}
