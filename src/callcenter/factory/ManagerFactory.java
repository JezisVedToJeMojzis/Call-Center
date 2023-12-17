package callcenter.factory;

import callcenter.CallCenter;
import callcenter.Employee;
import callcenter.position.Director;
import callcenter.position.Manager;

public class ManagerFactory implements EmployeeFactory{
    @Override
    public Employee createEmployee() {
        // Singleton instance
        Manager manager = Manager.getInstance();
        manager.setPosition("Manager");
        manager.setExperienceLevel(5);
        CallCenter.getInstance().hireEmployee(manager); // Adding the employee into Call Center list of employees
        return manager;
    }
}
