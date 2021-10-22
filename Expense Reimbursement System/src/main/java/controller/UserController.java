package controller;

import dao.ReimbursementDAOImpl;
import dao.UserDAOImpl;
import io.javalin.http.Context;
import model.User;
import services.ReimbursementService;
import services.ReimbursementServiceImpl;
import services.UserService;
import services.UserServiceImpl;

public class UserController {

	static UserService userServ = new UserServiceImpl(new UserDAOImpl());
	static ReimbursementService reimb = new ReimbursementServiceImpl(new ReimbursementDAOImpl());

	public static boolean login(Context context) {
		Boolean loginBool = false;
		String username = context.formParam("username");
		String password = context.formParam("password");

		User loginResult = null;
		loginResult = userServ.login(username, password);

		if (loginResult != null) {
			context.sessionAttribute("currentUser", loginResult);
			context.json(loginResult);
			System.out.println("This is in redirecting");
			context.redirect("/html/homepage.html");
			loginBool = true;
		} else {
			context.redirect("/html/notAllowed.html");
		}
		return loginBool;
	}
	
	public static void logout(Context cxt) {
		System.out.println("User logged out");
		cxt.sessionAttribute("currentUser", null);
		cxt.redirect("/index.html");
	}

	public static void getUserInfo(Context context) {
		context.json(userServ.findUserByUserName(((User) context.sessionAttribute("currentUser")).getUserName()));
	}

	public static void getAssocReimbList(Context context) {
		context.json(((User) context.sessionAttribute("currentUser")).getAssocReimbList());
	}
	
	

}
