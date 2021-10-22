package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dao.ReimbursementDAO;
import services.ReimbursementService;
import services.ReimbursementServiceImpl;

class ReimbursementServiceTest {
	
	ReimbursementService reimbServ;
	
	ReimbursementDAO reimbDao = Mockito.mock(ReimbursementDAO.class);
	
	@BeforeEach
	void setUp() throws Exception {
		reimbServ = new ReimbursementServiceImpl(reimbDao);
	}

	@Test
	void test() {
//		Mockito.when(reimbDao.selectAllReimb()).thenReturn(null);
		
		
	}

}
