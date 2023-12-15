package callcenter;

import java.util.UUID;

public class Call {

    private UUID id = UUID.randomUUID();
    private Employee callHandler; // employee that is handling the call
    private boolean inQueue = false;
    private boolean callEnded = false;

    public void endCall() {
        if (callHandler != null) {
            System.out.println("Call " + id + " ended. Employee " + callHandler.getId() + " is now available.");
            callHandler.setInCall(false); // employee is now available
            setCallHandler(null); // no one is assigned to call
            setInQueue(false);
            setCallEnded(true);
        } else {
            System.out.println("Call " + id + " is not assigned to any employee.");
        }
    }

    public Call(UUID id, Employee callHandler, boolean inQueue, boolean callEnded) {
        this.id = UUID.randomUUID();
        this.callHandler = callHandler;
        this.inQueue = inQueue;
        this.callEnded = callEnded;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Employee getCallHandler() {
        return callHandler;
    }

    public void setCallHandler(Employee callHandler) {
        this.callHandler = callHandler;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }

    public boolean isCallEnded() {
        return callEnded;
    }

    public void setCallEnded(boolean callEnded) {
        this.callEnded = callEnded;
    }
}
