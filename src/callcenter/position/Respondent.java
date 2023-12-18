package callcenter.position;

import callcenter.Call;
import callcenter.CallCenter;
import callcenter.Employee;

import java.util.UUID;

public class Respondent extends Employee {

    // Method to process incoming call
    @Override
    public void receiveCall(Call call) {
        // Respondent is available
        // Check if respondent has enough of experience to handle the call
        if(enoughExperienceLevel(getExperienceLevel(), call.getRequiredExperienceLevel()) == false){
            System.out.println("Respondent" + " (ID: " + getId() + ", Experience level: " + getExperienceLevel() + ") is now handling the call " + "(ID: " + call.getId() + ", Required experience level: " + call.getRequiredExperienceLevel() + ")");
            System.out.println("Respondent " + "(ID: " + getId() + ") is not able to handle this call. Escalating the call to Manager...");
            escalateCall(call); // Escalating call to manager
        }
        else{
            System.out.println("Respondent" + " (ID: " + getId() + ", Experience level: " + getExperienceLevel() + ") is now handling the call " + "(ID: " + call.getId() + ", Required experience level: " + call.getRequiredExperienceLevel() + ")");
            assignCallToEmployee(call, this, true); // Connecting call and respondent
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

    // Method to escalate call if the Respondent is not available or not able to handle call
    public void escalateCall(Call call) {
        setAssignedCall(null);
        Manager.getInstance().receiveCall(call); // Escalating the call to manager
        CallCenter.getInstance().processQueue(this); // Employee can get a new call
    }

    // Method to finish call and check for new one
    @Override
    public void finishCall(){
        if(getAssignedCall() != null){
            setAssignedCall(null); // Respondent is available again
            CallCenter.getInstance().processQueue(this); // Employee can get a new call
            CallCenter.getInstance().addToFinishedCalls(this.getAssignedCall()); // Adding call to list of finished calls
        }else{
            System.out.println("Respondent (ID: " + getId() + ") is not in a call.");
        }
    }
}

