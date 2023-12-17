package callcenter.factory;

import callcenter.CallCenter;
import callcenter.Employee;
import callcenter.position.Director;
import callcenter.position.Respondent;

public class RespondentFactory implements EmployeeFactory{
    @Override
    public Employee createEmployee() {
        Respondent respondent = new Respondent();
        respondent.setPosition("Respondent");
        respondent.setExperienceLevel(3);
        CallCenter.getInstance().hireEmployee(respondent); // Adding the employee into Call Center list of employees
        return respondent;
    }
}
