package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import controller.UserController;
import dao.UserDAO;
import model.User;
import services.UserService;
import services.UserServiceImpl;

class UserServiceTest {
	
	UserService userServ;
	
	UserDAO userDao = Mockito.mock(UserDAO.class);

	@BeforeEach
	void setUp() throws Exception {
		userServ = new UserServiceImpl(userDao);
	}

	@Test
	void testLogin() {
		
		Mockito.when(userDao.selectUserByUsername("noneexistent")).thenReturn(null);
		Mockito.when(userDao.selectUserByEmail("noneexistent")).thenReturn(null);
		
		//assertEquals(User.class, userServ.login("super", "duper"));
		
		assertEquals(null, userServ.login("super", "incorrectpass"));
		
	}
	
	@Test
	void testFindUserByID() {
		Mockito.when(userDao.selectUserById(-1)).thenReturn(null);
		
		assertEquals(null, userServ.findUserById(-1));
	}
	
	@Test
	void testFindUserByUserName() {
		Mockito.when(userDao.selectUserByUsername("noone")).thenReturn(null);
		
		assertEquals(null, userServ.findUserByUserName("noone"));
	}

}
