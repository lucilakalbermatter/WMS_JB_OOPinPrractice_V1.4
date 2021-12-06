package main.java.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Data Repository
 * 
 * @author pujanov
 *
 */
public class UserRepository {

	private static final List<Employee> EMPLOYEE_LIST = new ArrayList<Employee>();
	private static final List<Admin> ADMIN_LIST = new ArrayList<Admin>();

	/**
	 * Load employee, records from the personnel.json file
	 */
	static {
		// System.out.println("Loading items");
		BufferedReader reader = null;
		try {
			EMPLOYEE_LIST.clear();

			reader = new BufferedReader(new FileReader("./data/personnel.json"));
			Object data = JSONValue.parse(reader);
			if (data instanceof JSONArray) {
				JSONArray dataArray = (JSONArray) data;
				for (Object obj : dataArray) {
					if (obj instanceof JSONObject) {
						JSONObject jsonData = (JSONObject) obj;
						String userName = jsonData.get("user_name").toString();
						String password = jsonData.get("password").toString();
						Employee employee = new Employee(userName, password, null);
						EMPLOYEE_LIST.add(employee);
						Admin admin = new Admin(userName, password, null);
						ADMIN_LIST.add(admin);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * Get All persons
	 * 
	 * @return
	 */
	public static List<Employee> getAllEmployees() {
		return EMPLOYEE_LIST;
	}

	public static List<Admin> getAllAdmins() {
		return ADMIN_LIST;
	}

	
	public static boolean isUserValid(String userName, String password) {
		List<Employee> employees = getAllEmployees();
		
		for(Employee employee : employees) {
			if(userName.equals(employee.getName())) {
				if(password.equals(employee.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isUserEmployee(String name) {
		List<Employee> employees = getAllEmployees();

		for(Employee employee : employees) {
			if(name.equals(employee.getName())) {
					return true;
				}
			}
		return false;
	}

	public static boolean isAdminValid(String userName, String password) {
		List<Admin> admin = getAllAdmins();

		for(Admin admins : admin) {
			if(userName.equals(admins.getName())) {
				if(password.equals(admins.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isUserAdmin(String name) {
		List<Admin> admin = getAllAdmins();

		for(Admin admins : admin) {
			if(name.equals(admins.getName())) {
				return true;
			}
		}
		return false;
	}
}
