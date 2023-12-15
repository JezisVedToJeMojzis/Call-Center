package callcenter.position;

import callcenter.Call;
import callcenter.CallCenter;
import callcenter.Employee;

import java.util.UUID;

public class Manager extends Employee {

    private static Manager instance;

    private Manager() {
    }
    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }
    @Override
    public void processCall(Call call) {
        if (isInCall() || isOnBreak()) {
            System.out.println("Manager is not available. The call will be escalated to director.");
          //  escalate(call);
        } else {
            System.out.println("Manager " + " (ID: " + getId() + ") is handling the call");
            setInCall(true);
            call.setCallHandler(this); // Assign the Manager instance as the call handler
        }
    }
//    private void escalate(Call call) {
//        CallCenter.getInstance().dispatchCall(call, this.getClass());
//    }
}
