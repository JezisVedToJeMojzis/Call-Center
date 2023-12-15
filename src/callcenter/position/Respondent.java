package callcenter.position;

import callcenter.Call;
import callcenter.CallCenter;
import callcenter.Employee;

import java.util.UUID;

public class Respondent extends Employee {
    @Override
    public void processCall(Call call) {
        if (isInCall() || isOnBreak()) {
            System.out.println("None of the respondents is available. The call will be escalated to manager.");
            //escalate(call);
        } else {
            System.out.println("Respondent " + " (ID: " + getId() + ") is handling the call");
            setInCall(true);
            call.setCallHandler(this); // Assign the Respondent instance as the call handler
        }
    }

//    private void escalate(Call call) {
//        CallCenter.getInstance().dispatchCall(call, this.getClass());
//    }
}

