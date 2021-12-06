package main.java;

import main.java.data.AdminServiceImpl;
import main.java.data.User;
import main.java.data.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class TheWarehouseApp {
	/**
	 * Execute the <i>TheWarehouseApp</i>
	 * 
	 * @param args
	 */
	
	public static List<String> SESSION_ACTIONS = new ArrayList<String>();
	public static boolean IS_EMPLOYEE = false;

	
	public static void main(String[] args) {

		TheWarehouseManager theManager = new TheWarehouseManager();

		// Welcome User
		theManager.welcomeUser();

		// Get the user's choice of action and perform action
		do {
			if(!UserRepository.isUserAdmin(TheWarehouseManager.SESSION_USER.getName())) {
			int choice = theManager.getUsersChoice();
			theManager.performAction(choice);

			} else { //If user is admin
				
				// prompt for password and allow further actions if authenticated:
				
				AdminServiceImpl adminService = new AdminServiceImpl();
				
				if(!SESSION_USER.isAuthenticated()) {
				adminService.authenticateAdmin();
				}
				
				int choice = adminService.getAdminsChoice();
				
				adminService.performAction(choice);
				
			}
			// confirm to do more
			if (theManager.confirm("Do you want to perform another action?") == false) {
				theManager.quit();
				}

		} while (true);
	}
}
