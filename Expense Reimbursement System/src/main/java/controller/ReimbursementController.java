package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.ReimbursementDAOImpl;
import io.javalin.http.Context;
import model.Reimbursement;
import model.User;
import services.ReimbursementService;
import services.ReimbursementServiceImpl;

public class ReimbursementController {
	
	static ReimbursementService reimb = new ReimbursementServiceImpl(new ReimbursementDAOImpl());
	
	public static void createNewReimb(Context context) {
		System.out.println("-------Starting createNewReimb-------");
		services.MainDriver.logg.debug("-------Starting createNewReimb inside reimbursement controller-------");
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = Timestamp.valueOf(dateTime.format(formatter));
		

		Reimbursement customReimb = context.bodyAsClass(Reimbursement.class);
		
		
		customReimb.setSubmittedTime(timestamp);
		customReimb.setReimbStatusId(3);
		customReimb.setReimbAuthorId(((User) context.sessionAttribute("currentUser")).getUserId());
		
		if (customReimb.getDescription().isEmpty()) {
			services.MainDriver.logg.debug("-------Description is empty-------");
			reimb.addNewReimbWithoutDesc(customReimb);
			services.MainDriver.logg.debug("Reimb amount from JSON: "+customReimb.getReimbAmount());
			services.MainDriver.logg.debug("Reimb submitted time from JSON: "+customReimb.getSubmittedTime());
			services.MainDriver.logg.debug("Reimb authorid from JSON: "+customReimb.getReimbAuthorId());
			services.MainDriver.logg.debug("Reimb statusid from JSON: "+customReimb.getReimbStatusId());
			services.MainDriver.logg.debug("Reimb typeid from JSON: "+customReimb.getReimbTypeId());
		} else {
			services.MainDriver.logg.debug("-------Description is NOT empty-------");
			reimb.addNewReimbWithDesc(customReimb);
			services.MainDriver.logg.debug("Reimb amount from JSON: "+customReimb.getReimbAmount());
			services.MainDriver.logg.debug("Reimb submitted time from JSON: "+customReimb.getSubmittedTime());
			services.MainDriver.logg.debug("Reimb description from JSON: "+customReimb.getDescription());
			services.MainDriver.logg.debug("Reimb authorid from JSON: "+customReimb.getReimbAuthorId());
			services.MainDriver.logg.debug("Reimb statusid from JSON: "+customReimb.getReimbStatusId());
			services.MainDriver.logg.debug("Reimb typeid from JSON: "+customReimb.getReimbTypeId());
		}

		
		context.redirect("/html/homepage.html");

		services.MainDriver.logg.debug("-------Exiting createNewReimb inside reimbursement controller-------");
	}
	/**
	 * This method is to update the status of the reimbursement by manager.
	 * It takes the current time and formats for style then convert it to Timestamp.
	 * 
	 * @param context
	 */
	public static void updateReimb(Context context) {
		services.MainDriver.logg.debug("-------Starting updateReimb inside reimbursement controller-------");
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Timestamp currentTime = Timestamp.valueOf(dateTime.format(formatter));
		
		Reimbursement customReimb = context.bodyAsClass(Reimbursement.class);
		
		customReimb.setResolvedTime(currentTime);
		customReimb.setReimbResolverId(((User) context.sessionAttribute("currentUser")).getUserId());
		
		reimb.updateReimbStatus(customReimb);

		services.MainDriver.logg.debug("reimbid: "+customReimb.getReimbId());
		services.MainDriver.logg.debug("reimbstatusid: "+customReimb.getReimbStatusId());
		services.MainDriver.logg.debug("reimbresolverid: "+customReimb.getReimbResolverId());
		services.MainDriver.logg.debug("reimbresolvertime: "+customReimb.getResolvedTime());
		services.MainDriver.logg.debug("-------Exiting updateReimb inside reimbursement controller-------");
	}
	
	/**
	 * 
	 * This is to get rid of + for space and %2B for + in description.
	 * It converts UTF-8 codes to standard characters.
	 * 
	 * @param value
	 * @return decoded value
	 */
	public static String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {            
        	services.MainDriver.logg.fatal(e);
        	return "Wrong input";
        }
    }
	
	
	
	

}
