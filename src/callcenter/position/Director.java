package callcenter.position;

import callcenter.Call;
import callcenter.Employee;
import callcenter.CallCenter;

import java.util.UUID;

public class Director extends Employee {
    private static Director instance;
    private Director() {
    }

    public static Director getInstance() {
        if (instance == null) {
            instance = new Director();
        }
        return instance;
    }
    @Override
    public void processCall(Call call) {
        if (isInCall() || isOnBreak()) {
            System.out.println("Director is not available. Please wait in the queue.");
           // escalate(call);
        } else {
            System.out.println("Director " + " (ID: " + getId() + ") is handling the call");
            setInCall(true);
            call.setCallHandler(this); // Assign the Director instance as the call handler
        }
    }

//    private void escalate(Call call) {
//        CallCenter.getInstance().dispatchCall(call, this.getClass());
//    }
}
