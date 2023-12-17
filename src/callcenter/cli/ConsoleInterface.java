package callcenter.cli;
import callcenter.Call;
import callcenter.CallCenter;
import callcenter.Employee;
import callcenter.factory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsoleInterface {

    public static void controller(){
        // Singleton instance of call center
        CallCenterFactory callCenterFactory = new CallCenterFactory();
        CallCenter callCenter = callCenterFactory.createCallCenter();

        // Singleton instance of director
        EmployeeFactory directorFactory = new DirectorFactory();
        Employee director = directorFactory.createEmployee();

        // Singleton instance of manager
        EmployeeFactory managerFactory = new ManagerFactory();
        Employee manager = managerFactory.createEmployee();

        // Respondents factory
        EmployeeFactory respondentFactory = new RespondentFactory();

        // Calls factory
        CallFactory callFactory = new CallFactory();

        List<Call> callsToDispatch = new ArrayList<>();

        System.out.println("********************************************************************************");
        System.out.println("WELCOME TO THE CALL CENTER APP!");
        System.out.println("********************************************************************************");

        System.out.println("- First we choose the number (must be integer) of respondents.");
        Integer inputNumberOfRespondents = KeyboardInput.readInt("\nChoose the number of respondents:  ");
        for (int i = 0; i < inputNumberOfRespondents; i++) {
            respondentFactory.createEmployee();
        }

        System.out.println("You have chosen to have " + inputNumberOfRespondents + " respondents in you call center!");
        System.out.println("Below you can see the details of the respondents + their manager and director within the call center. Note: Every employee has a specific level of experience.");
        System.out.println("Employees of call center: " + callCenter.getEmployees());

        System.out.println("Press 1 and enter to view the manual...");

        while (true) {
            Integer control = KeyboardInput.readInt("\nAdd command (1 = manual)...");
            switch (control) {
                case 1:
                    System.out.println("\nManual (press enter after each option):");
                    System.out.println("- Press 0 to end session.");
                    System.out.println("- Press 1 for this manual.");
                    System.out.println("- Press 2 to view calls waiting to dispatch.");
                    System.out.println("- Press 3 to view calls in queue for respondents.");
                    System.out.println("- Press 4 to view calls in queue for both manager and director.");
                    System.out.println("- Press 5 to view calls in queue for director.");
                    System.out.println("- Press 6 to view finished calls.");
                    System.out.println("- Press 7 to view employees of call center.");
                    System.out.println("- Press 8 to create a new call.");
                    System.out.println("- Press 9 to dispatch all calls.");
                    System.out.println("- Press 10 to finish a call of specific employee.");
                    continue;
                case 2:
                    System.out.println("\nCalls waiting to be dispatched:");
                    System.out.println(callsToDispatch);
                    continue;
                case 3:
                    System.out.println("\nCalls in queue for respondents:");
                    System.out.println(callCenter.getCallQueueForRespondents());
                    continue;
                case 4:
                    System.out.println("\nCalls in queue for both manager and director:");
                    System.out.println(callCenter.getCallQueueForManagerOrDirector());
                    continue;
                case 5:
                    System.out.println("\nCalls in queue for director:");
                    System.out.println(callCenter.getCallQueueForDirector());
                    continue;
                case 6:
                    System.out.println("\nFinished calls:");
                    System.out.println(callCenter.getFinishedCalls());
                    continue;
                case 7:
                    System.out.println("\nEmployees of call center:");
                    System.out.println(callCenter.getEmployees());
                    continue;
                case 8:
                    System.out.println("\nYou have chosen to create a new call!");
                    Call call = callFactory.createCall();
                    call.setRequiredExperienceLevel(KeyboardInput.readInt("Choose level of experience needed for this call (must be integer):"));
                    callsToDispatch.add(call);
                    continue;
                case 9:
                    System.out.println("\nYou have chosen to dispatch all your calls!");
                    for(Call callToDispatch : callsToDispatch){
                        System.out.println("\nCall " + callToDispatch.getId() + " was dispatched. Required experience level: " + callToDispatch.getRequiredExperienceLevel());
                        callCenter.dispatchCall(callToDispatch);
                    }
                    callsToDispatch.clear();
                    continue;
                case 10:
                    System.out.println("\nYou have chosen to finish a call of specific employee!");
                    UUID uuid = KeyboardInput.readUUID("Type ID of employee you want to finish call (must be UUID): ");
                    for(Employee employee : CallCenter.getInstance().getEmployees()){
                        if(employee.getId().equals(uuid) && employee.getAssignedCall() != null){
                            System.out.println("\n" + employee.getPosition() + " (ID: " + employee.getId() + ") has finished call and is checking if there are other calls for him in the queue.");
                            employee.finishCall();
                        }
                    }
                    continue;
                case 0:
                    return;
            }
        }
    }
}
