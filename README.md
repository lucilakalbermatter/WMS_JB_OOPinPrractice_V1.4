# OOP In practice

## Topics covered

Abstract classes and Interfaces, Singletons & factories, overloading, polymorphism & virtual methods, and casting.

## Goal achieved

By the end of the exercise you will have upgraded your query command line tool to have functionalities added for admin users. OOP practices are done following the underlying principles.
Specifically, Following goals are achieved :

- Enhanced query tool, with added functionalities for admin users.
- Refactorings as per instructions.
- Functionalities as per admin, Employees or guest users.
- Different sets of actions added for admin users.

## Data

This time we are adding data related to orders in the provided `order.json` file.

You can write a class OrderRepository to fetch and store data in memory from the `order.json` file. Or optionally you can copy the provided OrderRepository.java file to the 	`com.dci.java.data` package (as this is not the main focus of the exercise).

The data for `personnel.json` will also be structured slightly different from this point. Each `user` will now have an additonal attribute `role` which is either EMPLOYEE or ADMIN.


## Description

As usual, you will start with the traditional refactoring, this time into abstract classes and interfaces. This process can be a little more complex than the one you did with previous exercise. In this exercise you will add new functionalities for admin with completely different set of actions. The previous features for normal users and Employees should be retained and you should try to make as less change as possible to the existing code. It involves various steps:

1. Analyze and decide which additional classes are required.
2. Decide which class to inherit the additional classes from if necessary.
3. Use abstract classes and Implement the interfaces in Java.
4. Refactor the original files to accommodate the admin functionalities.

An experienced developer will require some time to do all of this, and you may chose to do so as well, but the exercise provides you with step-by-step guidance to move along. This will help you concentrate on the most important parts of the topics covered.


When the query tool seeks for the name and gets the value it compares the name field with the user_name (user_name is unique) in the personnel.json file and if the user_name turns out to that of an admin, the user should be redirected to admin panel(i.e showing different welcome message and handling different set of actions for the admin users. The admin member should be asked to authenticate with his/her password to perform any administrative action.
The actions that can be done by Admin users are :

1. List orders by warehouse
2. List orders by status
3. List Orders whose total cost greater than provided value

These actions should be implemented as per the guidelines provided in the steps to follow section.
The admin users should also be shown the session summary when they choose to quit or don't want to perform another action.

###  Steps to follow :

You are supposed to continue from the solution of the last exercise.

- Make User an abstract class
- Make authenticate method in User class abstract method.
- Create a class Guest which inherits the User class
- Make greet and buy methods in User class abstract and migrate these methods as is to Guest class

In Employee class :  
	- Method bye will no longer have the super.bye(args...) line as we cannot directly invoke the abstract method bye in the abstract class User.  
	So, we're removing this line and adding the System.out.printf("\nThank you for your visit, %s!\n", this.name); line itself for now.

>Abstract class is a restricted class that cannot be used to create objects. Now as the User is declared as >abstract class, it cannot be instantiated direclty. It can only be inherited by child classes. So, we'll refactor >our project not to instantiate object from the User class directly.


In The WarehouseManageApp:  
	- remove assignment new User("") to static variable SESSION_USER

In The WarehouseManager.java:  
	- seekUserName() method :  
	   &nbsp;  - Initialize SESSION_USER with Employee or Guest appropriately as per the role of the user_name provided.  
	- greetUser() method :  
		&nbsp;  - change User to Guest. as we cannot instantiate User class  
	- quit() method :  
		&nbsp;  - change Guest guest = new Guest() instead of User.

*****Now let's run the app and verify that nothing has broke. The program should run as before.*****


Now, we'll work with a new dataset for `personnel.json`. Replace the old personnel.json file with the provided sample/personnel.json file. This file now contains an additional field role whose value(EMPLOYEE or ADMIN) determines if the employee has ADMIN privilege. So, if the employee has ADMIN privileges he can perform tasks that requires higher authorities.  

>(Note: we consider user_name as an unique attribute)


##### Admin

Create a class Admin that inherits from the Employee class. Add constructors as per requirements
override the greet method to have different greeting message for admins :

```
Hello, {name of the user}!
Welcome to the Admin Panel.
With higher authority comes higher responsibility.
```

###### UserRepository class :
- We'll update the UserRepository class to add a static List of Admin(ADMIN_LIST) and add admin users to this list. So. that we'll have two different lists for normal employees and admins.
- Then we'll add the user to either EMPLOYEE_LIST or ADMIN_LIST as per the role attribute in the json file.
- Also add a getAllAdmins method which returns the ADMIN_LIST
- Add isUserAdmin and isAdminValid methods and implement them in similar fashion as isUserEmployee and isEmployeeValid methods (but for Admin) to return the correct boolean value.


In The WarehouseManager.java :  
	- Update seekUserName() and greetUser() methods to accomodate for Admin users.  
	- Update quit() method to accomodate for Admin users.


###### Functionalities for Admin :

Now we'll add various set of actions for admins only.


For this we'll be using a new data set for orders. Copy sample/order.json file provided to data/ directory of your project.  

Let us add a new class named `Order` and model it with the data stucture in our order.json file.  
Add any other classes as per requirements to encapsulate data inside the Order class.( We'll add a class OrderItem with fields itemName and quantity to match the DS). Generate all argument constructors and getters setters for the fields.

###### Order

| Property      | Type         | Default |
|---------------|--------------|---------|
| status         | String       | None    |
| isPaymentDone      | boolean       | None    |
| warehouse      | int       | None    |
| dateOfOrder   | Date         | None    |
| orderItems     | List (OrderItem)          | None    |
| totalCost      | double       | None    |

Each one of these properties will be supplied to the constructor method at the moment of instantiating the objects.


###### OrderItem

| Property      | Type         | Default |
|---------------|--------------|---------|
| itemName         | String       | None    |
| quantity      | int       | None    |



Add OrderRepository class with getAllOrders method to read the order.json file and give the list of orders in similar fashion to UserRepository class. Optionally, you can choose to use the provided `sample/UserRepository.java` file.

In TheWarehouseApp.java :  
	- Inside the do while loop:   
		&nbsp; - keep the previous flow for non-admin users.  
		&nbsp; - if the user is admin we'll have different sets of actions related to adminstrations.  


The different sets of actions for Admins are :

- List orders by warehouse
- List orders by status
- List Orders whose total cost greater than provided value

For this we will work with the provided AdminServiceImpl class which implements the AdminService interface :  
	- Copy both the interface AdminService and it's implementation class AdminServiceImpl to com.dci.java.data package.  
	- Provided TheWareHouseApp.java file can be used to compare the changes before moving on to further steps. It is not recommended to copy/paste the code without even trying.<br> 	
	- Implement the methods in AdminServiceImpl as per the instructions provided in the comments.
