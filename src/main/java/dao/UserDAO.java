package dao;

import model.User;

public interface UserDAO {
	
	// SELECTS
	public User selectUserById(int userId);
	public User selectUserByUsername(String userName);
	public User selectUserByEmail(String email);
	public User selectUserByUsernameOrEmail(String userName, String email, String password);
	
}

