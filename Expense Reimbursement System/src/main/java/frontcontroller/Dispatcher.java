package frontcontroller;

import static io.javalin.apibuilder.ApiBuilder.*;

import controller.ReimbursementController;
import controller.UserController;
import io.javalin.Javalin;

public class Dispatcher {
	public Dispatcher(Javalin app) {
		setupAllPaths(app);
	}

	private void setupAllPaths(Javalin app) {
		setupUserControllerPath(app);
		setupReimbControllerPath(app);
	}

	private void setupReimbControllerPath(Javalin app) {
		app.routes( () -> {
			path("/api", ()->{
				path("/createreimb", ()->{
					services.MainDriver.logg.debug("-------Inside createReimb-------");
					post(ReimbursementController::createNewReimb);
				});
				path("/updatereimb", ()->{
					services.MainDriver.logg.debug("-------Inside updateReimb-------");
					post(ReimbursementController::updateReimb);
				});
			});
		});
		
	}
	private void setupUserControllerPath(Javalin app) {
		app.routes(() -> {

			path("/api", () -> {
				
				path("/user", ()->{

					path("/login", () -> {
						services.MainDriver.logg.debug("-------Inside login-------");
						post(UserController::login);
						get(UserController::getUserInfo);
					});
					
					path("/logout", ()->{
						services.MainDriver.logg.debug("-------Inside logout-------");
						post(UserController::logout);
					});
					
				});

			});

		});

	}
}
