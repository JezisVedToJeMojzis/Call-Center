package callcenter.position;

import callcenter.Call;
import callcenter.Employee;
import callcenter.CallCenter;

import java.util.UUID;

public class Director extends Employee {
    private static Director instance;

    // Singleton instance of Director
    public static Director getInstance() {
        if (instance == null) {
            instance = new Director();
        }
        return instance;
    }
    @Override
    public void receiveCall(Call call) {
        // Director is not available
        if (isInCall()) {
            System.out.println("Director is not available. Please try calling later.");
        } else { // Director is available
            System.out.println("Director " + " (ID: " + getId() + ", Experience level: " + getExperienceLevel() + ") is handling the call " + "(Required experience level: " + call.getRequiredExperienceLevel() + ")");
            assignCallToEmployee(call, this, true); // Connecting call and director
        }
    }

    // Function to assign call to specific employee instance
    public void assignCallToEmployee(Call call, Employee employee, Boolean callAssigned){
        setInCall(callAssigned); // set employees status as in call
        call.setCallHandler(employee); // Assign the employee instance as the call handler
    }
}
