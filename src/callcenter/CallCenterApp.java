package callcenter;

import callcenter.position.Director;
import callcenter.position.Manager;
import callcenter.position.Respondent;

import java.util.List;

public class CallCenterApp {
    public static void main(String[] args) {

        // Get the one and only instance of call center
        CallCenter callCenter = CallCenter.getInstance();

        // Only one director and manager
        Employee director = Director.getInstance();
        Employee manager = Manager.getInstance();

        // Multiple respondents
        Employee respondent1 = new Respondent();
        Employee respondent2 = new Respondent();

        // Add employees to the CallCenter
        callCenter.hireEmployee(manager);
        callCenter.hireEmployee(director);
        callCenter.hireEmployee(respondent1);
        callCenter.hireEmployee(respondent2);

//        Call call1 = new Call();

    }
}