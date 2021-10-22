package services;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class MainDriver {
	public final static Logger logg = Logger.getLogger(MainDriver.class);


	public static void main(String[] args) {
		logg.setLevel(Level.DEBUG);
		if(logg.isInfoEnabled())
			logg.info("Log level is set to Info. Logging all the information.... starting now.");
		
		services.MainDriver.logg.debug("-------Starting up the app-------");
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles(staticFiles -> {
				staticFiles.directory = "/frontend";
				staticFiles.hostedPath = "/";
				staticFiles.location = Location.CLASSPATH;
			});
		}).start(9009);

		FrontController frontCont = new FrontController(app);

	}

}
