package callcenter;

import callcenter.exception.DuplicateIdException;
import callcenter.exception.NullException;
import callcenter.position.Director;
import callcenter.position.Manager;
import callcenter.position.Respondent;

import java.util.*;

public class CallCenter {
    private static CallCenter instance; // static so there is only one shared instance of this variable for the entire class
    private List<Employee> employees;
    private List<Call> allCalls;
    private List<Call> finishedCalls;
    private Queue<Call> callQueueForRespondents;
    private Queue<Call> callQueueForManagerOrDirector;
    private Queue<Call> callQueueForDirector;

    private CallCenter() { // Private constructor to prevent instantiation outside of this class
        this.employees = new ArrayList<>();
        this.allCalls = new ArrayList<>();
        this.finishedCalls = new ArrayList<>();
        this.callQueueForRespondents = new LinkedList<>();
        this.callQueueForManagerOrDirector = new LinkedList<>();
        this.callQueueForDirector = new LinkedList<>();
    }

    // Singleton instance of CallCenter
    public static CallCenter getInstance() {
        if (instance == null) {
            instance = new CallCenter();
        }
        return instance;
    }

    // Method to add employee to employees list
    public void hireEmployee(Employee employee) {
        // Check unique id
        if (isEmployeeIdUnique(employee.getId())) {
            employees.add(employee);
        } else {
            throw new DuplicateIdException("Employee with ID " + employee.getId() + " is already hired in the Call Center.");
        }
    }

    // Method to check if an employee with the same id already exists in the list
    private boolean isEmployeeIdUnique(UUID employeeId) {
        for (Employee existingEmployee : employees) {
            if (existingEmployee.getId().equals(employeeId)) {
                return false; // Duplicate id found
            }
        }
        return true; // id is unique
    }

    // Method to add call into incomingCalls list and queue for respondents
    public void receiveCall(Call call){
        // Check unique id within queue
        if (isIncomingCallIdUnique(call.getId())) {
            initialProcessOfCall(call);
        } else {
            throw new DuplicateIdException("Call with ID " + call.getId() + " is already in the queue waiting for respondent.");
        }
    }

    // Method to add call to initial queue and change status
    public void initialProcessOfCall(Call call){
        allCalls.add(call);
        call.setInQueue(true);
    }

    // Method to check if call with the same id already exists in the queue for respondents (incoming calls)
    private boolean isIncomingCallIdUnique(UUID callId) {
        for (Call existingCall : callQueueForRespondents) {
            if (existingCall.getId().equals(callId)) {
                return false; // Duplicate id found
            }
        }
        return true; // id is unique
    }

    // Method to check if call with the same id already exists in the list of finished calls
    private boolean isFinishedCallIdUnique(UUID callId) {
        for (Call existingCall : finishedCalls) {
            if (existingCall.getId().equals(callId)) {
                return false; // Duplicate id found
            }
        }
        return true; // id is unique
    }

    // Method to connect a call to employee
    public void dispatchCall(Call call) {
        callQueueForRespondents.add(call); // add call to queue
        call.setInQueue(true);
        Employee freeRespondent = getFreeRespondent(); // get free respondent
        if (freeRespondent != null) {
            processQueue(freeRespondent); // Processing first call in the queue (sending to respondent)
        } else{
            System.out.println("All respondents are busy. Please wait in the queue.");
            //throw new NullException("All respondents are busy.");
        }
    }

    // Method to check for free Respondents
    public Employee getFreeRespondent(){
        for (Employee employee : employees) {
            if (employee instanceof Respondent && employee.getAssignedCall() == null) {
                return employee;
            }
        }
        return null;
    }

    // Method to process first call in the queue
    public void processQueue(Employee employee) {
        // Calls only for respondents
        if (employee instanceof Respondent) {
            Call queuedCallForRespondent = callQueueForRespondents.poll();  // Selecting the first call in queue
            if(queuedCallForRespondent != null){
                employee.receiveCall(queuedCallForRespondent); // Respondent receiving the call from queue
            }
            else{
                System.out.println("There are no calls in the queue intended for respondents.");
               // throw new NullException("There is no call in the queue for respondent.");
            }
        }
        // Calls only for director
        if (employee instanceof Director) { // Higher priority calls
            Call queuedCallForDirector = callQueueForDirector.poll();  // Selecting the first call in queue
            if(queuedCallForDirector != null){
                employee.receiveCall(queuedCallForDirector); // Director receiving the call from queue
            }
            else{
                System.out.println("There are no calls in the queue intended only for director.");
                //throw new NullException("There is no call in the queue for director.");
            }
        }
        // Calls for both manager and director
        if ((employee instanceof Manager) || (employee instanceof Director)) {
            Call queuedCallForManagerOrDirector = callQueueForManagerOrDirector.poll();  // Selecting the first call in queue
            if(queuedCallForManagerOrDirector != null){
                employee.receiveCall(queuedCallForManagerOrDirector); // Receiving the call from queue
            }
            else{
                System.out.println("There are no calls in the queue intended for both manager and director.");
               // throw new NullException("There is no call in the queue for both director and manager.");
            }
        }
    }

    // Method to add calls to queue for manager and director based on experience level
    public void addToQueue(Call call){
        // Adding calls to queue intended for director
        if(call.getRequiredExperienceLevel() > 5){
            callQueueForDirector.add(call);
        }
        // Adding calls to queue intended for both manager and director
        else{
            callQueueForManagerOrDirector.add(call);
        }
    }

    // Method to adding finished calls to list
    public void addToFinishedCalls(UUID callId){
        Call finishedCall = new Call();
        // Look for the full call instance based on id in list of all calls
        for(Call call : allCalls){
            if(call.getId() == callId){
                finishedCall = call;
            }
        }
        // Check for duplicities in list of finished calls
        if (isFinishedCallIdUnique(finishedCall.getId())) {
            finishedCall.setCallEnded(true); // Change status of call to ended
            finishedCalls.add(finishedCall); // Add call to list of finished calls
        } else {
            throw new DuplicateIdException("Call with ID " + finishedCall.getId() + " is already in the queue.");
        }
    }

    // Getters/Setters
    public List<Employee> getEmployees() {
        return employees;
    }
    public List<Call> getFinishedCalls() {
        return finishedCalls;
    }
    public Queue<Call> getCallQueueForRespondents() {
        return callQueueForRespondents;
    }
    public Queue<Call> getCallQueueForManagerOrDirector() {
        return callQueueForManagerOrDirector;
    }
    public Queue<Call> getCallQueueForDirector() {
        return callQueueForDirector;
    }
}
