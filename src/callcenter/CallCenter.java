package callcenter;

import callcenter.exception.DuplicateIdException;
import callcenter.position.Director;
import callcenter.position.Manager;
import callcenter.position.Respondent;

import java.util.*;

public class CallCenter {
    private static CallCenter instance; // static so there is only one shared instance of this variable for the entire class
    private List<Employee> employees;
    private List<Call> incomingCalls;
  //  private Queue<Call> callQueue;

    private CallCenter() { // Private constructor to prevent instantiation outside of this class
        this.employees = new ArrayList<>();
        this.incomingCalls = new ArrayList<>();
       // callQueue = new LinkedList<>();
    }

    // Singleton instance of CallCenter
    public static CallCenter getInstance() {
        if (instance == null) {
            instance = new CallCenter();
        }
        return instance;
    }

    // Add employee to employees list
    public void hireEmployee(Employee employee) {
        // Check unique id
        if (isEmployeeIdUnique(employee.getId())) {
            employees.add(employee);
        } else {
            throw new DuplicateIdException("Employee with ID " + employee.getId() + " is already hired in the Call Center.");
        }
    }

    // Check if an employee with the same id already exists in the list
    private boolean isEmployeeIdUnique(UUID employeeId) {
        for (Employee existingEmployee : employees) {
            if (existingEmployee.getId().equals(employeeId)) {
                return false; // Duplicate id found
            }
        }
        return true; // id is unique
    }

    // Add call into incomingCalls list
    public void receiveCall(Call incomingCall){
        // Check unique id
        if (isCallIdUnique(incomingCall.getId())) {
            incomingCalls.add(incomingCall);
        } else {
            throw new DuplicateIdException("Call with ID " + incomingCall.getId() + " was already received in Call Center.");
        }
    }

    // Check if call with the same id already exists in the list
    private boolean isCallIdUnique(UUID callId) {
        for (Call existingCall : incomingCalls) {
            if (existingCall.getId().equals(callId)) {
                return false; // Duplicate id found
            }
        }
        return true; // id is unique
    }

    // Connect call to employee
    public void dispatchCall(Call call) {
        Employee freeRespondent = getFreeRespondent();
        if (freeRespondent != null) {
            freeRespondent.receiveCall(call);
        } else {
            System.out.println("All respondents are busy. Please wait in the queue.");
        }
    }

    // Check for free Respondents
    public Employee getFreeRespondent(){
        for (Employee employee : employees) {
            if (employee instanceof Respondent && !employee.isInCall()) {
                return employee;
            }
        }
        return null;
    }

    // Getters/Setters
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees); // Return a copy to prevent external modifications
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Call> getIncomingCalls() {
        return incomingCalls;
    }

    public void setIncomingCalls(List<Call> incomingCalls) {
        this.incomingCalls = incomingCalls;
    }

    @Override
    public String toString() {
        return "CallCenter{" +
                "employees=" + employees +
                ", incomingCalls=" + incomingCalls +
                '}';
    }
}
