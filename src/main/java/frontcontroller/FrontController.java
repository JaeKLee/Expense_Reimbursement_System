package frontcontroller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.User;

public class FrontController {

	Javalin app;
	Dispatcher dispatcher;

	public FrontController(Javalin app) {
		System.out.println("-------Starting Frontcontroller-------");
		this.app = app;
		app.before("/api/*", FrontController::checkAllRequest);
		app.before("/html/*", FrontController::checkHTMLRequest);
		
		this.dispatcher = new Dispatcher(app);
		System.out.println("-------Exiting FrontController-------");
	}

	public static void checkAllRequest(Context context) {
		///MIDDLEWARE
		services.MainDriver.logg.debug("-------Inside checkAllRequest-------");
		if (context.path().equals("/api/user/login")) {
			return;
		}
		
		
		if (context.sessionAttribute("currentUser") == null) {
			System.out.println("this is checkallrequest if currentuser is not null");;
			context.redirect("/index.html"); //if the user is not logged in, load the index.html
		}
		services.MainDriver.logg.debug("-------Exiting checkAllRequest-------");


	}
	
	public static void checkHTMLRequest(Context context) {
		services.MainDriver.logg.debug("-------Inside checkHTMLRequest-------");
		if (context.sessionAttribute("currentUser") == null) {
			System.out.println("this is checkallrequest if currentuser is not null");;
			context.redirect("/index.html"); //if the user is not logged in, load the index.html
		}
		services.MainDriver.logg.debug("-------Exiting checkAllRequest-------");

	}

}
