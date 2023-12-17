package callcenter.position;

import callcenter.Call;
import callcenter.CallCenter;
import callcenter.Employee;

import java.util.UUID;

public class Manager extends Employee {
    private static Manager instance;

    // Singleton instance of Manager
    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }
    @Override
    public void receiveCall(Call call) {
        // Manager is not available
        if(isInCall()){
            System.out.println("Manager is not available. Escalating the call to Director...");
            escalateCall(call); // Escalating call to director
        }
        else{ // Manager is available
            // Check if Manager has enough of experience to handle the call
            if(enoughExperienceLevel(getExperienceLevel(), call.getRequiredExperienceLevel()) == false){
                System.out.println("Manager is not able to handle this call. Escalating the call...");
                escalateCall(call); // Escalating call to director
                setInCall(false); // Manager is available again
            }
            System.out.println("Manager " + " (ID: " + getId() + ", Experience level: " + getExperienceLevel() + ") is handling the call " + "(Required experience level: " + call.getRequiredExperienceLevel() + ")");
            assignCallToEmployee(call, this, true); // Connecting call and manager
        }
    }
    // Function to check if employee is able to handle the call (based on experience level)
    public Boolean enoughExperienceLevel(Integer employeeExperienceLevel, Integer requiredExperienceLevel){
        if(employeeExperienceLevel <= requiredExperienceLevel){
            return false;
        }
        return true;
    }

    // Function to assign call to specific employee instance
    public void assignCallToEmployee(Call call, Employee employee, Boolean callAssigned){
        setInCall(callAssigned); // set employees status as in call
        call.setCallHandler(employee); // Assign the employee instance as the call handler
    }

    // Function to escalate call if the Manager is not available or not able to handle call
    public void escalateCall(Call call) {
        Director.getInstance().receiveCall(call); // Escalating the call to director
    }
}
