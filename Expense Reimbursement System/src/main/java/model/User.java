package model;

import java.util.List;

public class User {

	// Variables
	private int userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String userRoleName;
	private int userRoleId;
	private List<Reimbursement> assocReimbList;

	// Constructors
	public User(int userId, String userName, String password, String firstName, String lastName, String email,
			String userRoleName, int userRoleId, List<Reimbursement> assocReimbList) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleName = userRoleName;
		this.userRoleId = userRoleId;
		this.assocReimbList = assocReimbList;
	}

	public User(String userName, String password, String firstName, String lastName, String email, String userRoleName,
			int userRoleId, List<Reimbursement> assocReimbList) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleName = userRoleName;
		this.userRoleId = userRoleId;
		this.assocReimbList = assocReimbList;
	}

	public User(String userName, String password, String firstName, String lastName, String email, String userRoleName,
			int userRoleId) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleName = userRoleName;
		this.userRoleId = userRoleId;

	}

	public User(int userId, String userName, String password, String firstName, String lastName, String email,
			String userRoleName, int userRoleId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleName = userRoleName;
		this.userRoleId = userRoleId;

	}

	// Getters and setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public List<Reimbursement> getAssocReimbList() {
		return assocReimbList;
	}

	public void setAssocReimbList(List<Reimbursement> assocReimbList) {
		this.assocReimbList = assocReimbList;
	}

	// toString
	@Override
	public String toString() {
		return "\nUser [ " + "userId = " + userId + ", userName = " + userName + ", password = " + password
				+ ", firstName = " + firstName + ", lastName = " + lastName + ", userRoleId = " + userRoleId
				+ ", userRoleName = " + userRoleName + ", email = " + email + ", assocReimbList = " + assocReimbList
				+ " ]";
	}

}
