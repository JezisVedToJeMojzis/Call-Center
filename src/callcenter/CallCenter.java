package callcenter;

import callcenter.position.Director;
import callcenter.position.Manager;
import callcenter.position.Respondent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CallCenter {
    private static CallCenter instance; // static so there is only one shared instance of this variable for the entire class
    private List<Employee> employees;
  //  private Queue<Call> callQueue;

    private CallCenter() { // private constructor to prevent instantiation outside of this class
        this.employees = new ArrayList<>();
       // callQueue = new LinkedList<>();
    }

    public static CallCenter getInstance() { // obtain the single instance of CallCenter. If an instance doesn't exist (instance == null), it creates one using the private constructor. Otherwise, it returns the existing instance.
        if (instance == null) {
            instance = new CallCenter();
        }
        return instance;
    }

    public void hireEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees); // Return a copy to prevent external modifications
    }

    public void dispatchCall(Call call, Class<? extends Employee> lastHandler) {
    }

    public void handleWaitingCalls() {

    }
}
