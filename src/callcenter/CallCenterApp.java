package callcenter;

import callcenter.factory.*;
import callcenter.position.Director;
import callcenter.position.Manager;
import callcenter.position.Respondent;

import java.util.List;

public class CallCenterApp {
    public static void main(String[] args) {

        // Singleton instance of call center
        CallCenterFactory callCenterFactory = new CallCenterFactory();
        CallCenter callCenter = callCenterFactory.createCallCenter();

        // Singleton instance of director
        EmployeeFactory directorFactory = new DirectorFactory();
        Employee director = directorFactory.createEmployee();

        // Singleton instance of manager
        EmployeeFactory managerFactory = new ManagerFactory();
        Employee manager = managerFactory.createEmployee();

        // Respondents
        EmployeeFactory respondentFactory = new RespondentFactory();
        Employee respondent1 = respondentFactory.createEmployee();
        Employee respondent2 = respondentFactory.createEmployee();
        Employee respondent3 = respondentFactory.createEmployee();

        // Incoming calls
        CallFactory callFactory = new CallFactory();
        Call call1 = callFactory.createCall();
        Call call2 = callFactory.createCall();
        Call call3 = callFactory.createCall();
        Call call4 = callFactory.createCall();
        Call call5 = callFactory.createCall();
        Call call6 = callFactory.createCall();

        call2.setRequiredExperienceLevel(6);

        System.out.println("Instance details: " + callCenter.toString());

        callCenter.dispatchCall(call1);
        callCenter.dispatchCall(call2);
        callCenter.dispatchCall(call3);
        callCenter.dispatchCall(call4);
        callCenter.dispatchCall(call5);
        callCenter.dispatchCall(call6);
    }
}