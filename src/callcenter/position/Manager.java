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

    // Method to process incoming call
    @Override
    public void receiveCall(Call call) {
        // Manager is not available
        if(getAssignedCall() != null){
            System.out.println("Manager " + "(ID: " + getId() + ") is not available. Escalating the call to Director...");
            escalateCall(call); // Escalating call to director
        }
        else{ // Manager is available
            // Manager does not have enough of experience to handle the call
            if(enoughExperienceLevel(getExperienceLevel(), call.getRequiredExperienceLevel()) == false){
                System.out.println("Manager " + "(ID: " + getId() + ") is not able to handle this call (ID: " + call.getId() + "). Escalating the call to Director... Manager " + "(ID: " + getId() + ") is checking the queue for a new call.");
                escalateCall(call); // Escalating call to director
                CallCenter.getInstance().processQueue(this); // Employee can get a new call
            }else{ // Manager has enough of experience to handle the call
                System.out.println("Manager " + " (ID: " + getId() + ", Experience level: " + getExperienceLevel() + ") is now handling the call " + "(ID: " + call.getId() + ", Required experience level: " + call.getRequiredExperienceLevel() + ")");
                assignCallToEmployee(call, this, true); // Connecting call and manager
            }
        }
    }

    // Method to check if employee is able to handle the call (based on experience level)
    public Boolean enoughExperienceLevel(Integer employeeExperienceLevel, Integer requiredExperienceLevel){
        if(employeeExperienceLevel < requiredExperienceLevel){
            return false;
        }
        return true;
    }

    // Method to assign call to specific employee instance
    public void assignCallToEmployee(Call call, Employee employee, Boolean callAssigned){
        setAssignedCall(call.getId()); // Connect call to employee
        call.setCallHandler(employee.getId()); // Connect employee to call
        call.setInQueue(false); // Call is out of the queue
    }

    // Method to escalate call if the Manager is not available or not able to handle call
    public void escalateCall(Call call) {
        call.setCallHandler(null);
        Director.getInstance().receiveCall(call); // Escalating the call to director
    }

    // Method to finish call and check for new one
    @Override
    public void finishCall() {
        if (getAssignedCall() != null) {
            CallCenter.getInstance().addToFinishedCalls(this.getAssignedCall()); // Adding call to list of finished calls
            setAssignedCall(null); // Manager is available again
            CallCenter.getInstance().processQueue(this); // Employee can get a new call
        } else {
            System.out.println("Manager (ID: " + getId() + ") is not in a call.");
        }
    }
}
