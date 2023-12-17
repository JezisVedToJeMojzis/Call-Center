package callcenter.factory;

import callcenter.CallCenter;
import callcenter.Employee;
import callcenter.position.Director;

public class DirectorFactory implements EmployeeFactory{
    @Override
    public Employee createEmployee() {
        // Singleton instance
        Director director = Director.getInstance();
        director.setPosition("Director");
        director.setExperienceLevel(10);
        CallCenter.getInstance().hireEmployee(director); // Adding the employee into Call Center list of employees
        return director;
    }
}
