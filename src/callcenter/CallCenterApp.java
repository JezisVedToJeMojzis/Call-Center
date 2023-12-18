package callcenter;

import callcenter.cli.ConsoleInterface;
import callcenter.factory.*;

public class CallCenterApp {
    public static void main(String[] args) {

        // Singleton instance of call center
//        CallCenterFactory callCenterFactory = new CallCenterFactory();
//        CallCenter callCenter = callCenterFactory.createCallCenter();

        // Singleton instance of director
//        EmployeeFactory directorFactory = new DirectorFactory();
//        Employee director = directorFactory.createEmployee();
//
//        // Singleton instance of manager
//        EmployeeFactory managerFactory = new ManagerFactory();
//        Employee manager = managerFactory.createEmployee();

        // Respondents
//        EmployeeFactory respondentFactory = new RespondentFactory();
//        Employee respondent1 = respondentFactory.createEmployee();
//        Employee respondent2 = respondentFactory.createEmployee();
//
//        // Incoming calls
//        CallFactory callFactory = new CallFactory();
//        Call call1 = callFactory.createCall();
//        Call call2 = callFactory.createCall();
//        Call call3 = callFactory.createCall();
//        Call call4 = callFactory.createCall();
//        Call call5 = callFactory.createCall();
//
//        // Set required experience level for calls
//        call1.setRequiredExperienceLevel(2);
//        call2.setRequiredExperienceLevel(3);
//        call3.setRequiredExperienceLevel(5);
//        call4.setRequiredExperienceLevel(5);
//        call5.setRequiredExperienceLevel(7);
//
//        // Dispatch calls
//        callCenter.dispatchCall(call1);
//        callCenter.dispatchCall(call2);
//        callCenter.dispatchCall(call3);
//        callCenter.dispatchCall(call4);
//        callCenter.dispatchCall(call5);
//
//        System.out.println("Instance details: " + respondent1.toString());
//        System.out.println("Instance details: " + call1.toString());
//
//        respondent1.finishCall();
//        manager.finishCall();

          ConsoleInterface.controller();
    }
}