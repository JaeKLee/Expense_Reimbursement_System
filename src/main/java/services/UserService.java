package services;


import model.User;

public interface UserService {


	// SELECTS
	public User login(String userName, String password);
	public User findUserById(int userId);
	public User findUserByUserName(String username);

}

