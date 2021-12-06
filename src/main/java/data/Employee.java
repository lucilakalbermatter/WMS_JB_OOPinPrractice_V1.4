package main.java.data;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User{
    //Properties
    private final String password;
    private List<Employee> headOf;

    //Constructor
    public Employee(String userName, String password, List<Employee>headOf) {
        super(userName);
        this.password = password;
        this.headOf = new ArrayList<Employee>();
    }

    //GetPassword
    public String getPassword() {
        return password;
    }

    public boolean authenticate(String password) {
        return password.equals(this.password);
    }

    public void order(String item, int amount) {
        System.out.println("You order of the item " +item + " an amount of " + amount);
    }

    @Override
    public void authenticate() {

    }

    public void greet(){
        System.out.println("Hello, " + super.getName() + " ! \n If you experience a problem with the system, \n please contact technical support." );
    }

    @Override
    public void bye() {
        System.out.printf("\nThank you for your visit, %s!\n", this.name);

    }


}
