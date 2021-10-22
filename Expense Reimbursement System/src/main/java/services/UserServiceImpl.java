package services;

import dao.UserDAO;
import model.User;

public class UserServiceImpl implements UserService {
//	For Mockito sake
	UserDAO ersUser;
	
	public UserServiceImpl(UserDAO userDao) {
		super();
		this.ersUser = userDao;
	}
	
	public void setUserDao(UserDAO userDao) {
		this.ersUser = userDao;
	}

	@Override
	public User login(String userInput, String password) {
		User resultUser = null;
		
		try {
			if( ersUser.selectUserByUsername(userInput) !=null ) {
				if(ersUser.selectUserByUsername(userInput).getPassword().equals(password)) 
					resultUser = ersUser.selectUserByUsername(userInput);
			} else if (ersUser.selectUserByEmail(userInput) != null) {
				if(ersUser.selectUserByEmail(userInput).getPassword().equals(password))
					resultUser = ersUser.selectUserByEmail(userInput);
			} 

		} catch (Exception e) {
			services.MainDriver.logg.fatal(e);
			System.out.println("No such account in the system.");
		}
		
		return resultUser;
	}

	@Override
	public User findUserById(int userId) {
		return ersUser.selectUserById(userId);
	}

	@Override
	public User findUserByUserName(String username) {
		return ersUser.selectUserByUsername(username);
	}
}
