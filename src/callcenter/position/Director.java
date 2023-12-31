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

    // Method to process incoming call
    @Override
    public void receiveCall(Call call) {
        // Director is not available
        if (getAssignedCall() != null) {
            System.out.println("Director " + "(ID: " + getId() + ") is not available. The call is placed into the queue. Please wait...");
            CallCenter.getInstance().addToQueue(call); // Adding call to queue
        } else { // Director is available
            System.out.println("Director " + " (ID: " + getId() + ", Experience level: " + getExperienceLevel() + ") is now handling the call " + "(ID: " + call.getId() + ", Required experience level: " + call.getRequiredExperienceLevel() + ")");
            assignCallToEmployee(call, this, true); // Connecting call and director
        }
    }

    // Method to assign call to specific employee instance
    public void assignCallToEmployee(Call call, Employee employee, Boolean callAssigned){
        setAssignedCall(call.getId()); // Connect call to employee
        call.setCallHandler(employee.getId()); // Connect employee to call
        call.setInQueue(false); // Call is out of the queue
    }

    // Method to finish call and check for new one
    @Override
    public void finishCall(){
        if(getAssignedCall() != null){
            CallCenter.getInstance().addToFinishedCalls(this.getAssignedCall()); // Adding call to list of finished calls
            setAssignedCall(null); // Director is available again
            CallCenter.getInstance().processQueue(this); // Employee can get a new call
        }else{
            System.out.println("Director (ID: " + getId() + ") is not in a call.");
        }
    }
}
