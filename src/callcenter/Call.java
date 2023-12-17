package callcenter;

import java.util.UUID;

public class Call {

    private UUID id = UUID.randomUUID(); // assign random ID to call
    private Integer requiredExperienceLevel = 1; // Default is the lowest exp level
    private Employee callHandler = null; // employee that is handling the call
    private boolean inQueue = false;
    private boolean callEnded = false; // call is finished

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

    // Constructor
    public Call() {
    }

    // Getters/Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getRequiredExperienceLevel() {
        return requiredExperienceLevel;
    }

    public void setRequiredExperienceLevel(Integer requiredExperienceLevel) {
        this.requiredExperienceLevel = requiredExperienceLevel;
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

    @Override
    public String toString() {
        return "Call{" +
                "id=" + id +
                ", callHandler=" + callHandler +
                ", inQueue=" + inQueue +
                ", callEnded=" + callEnded +
                '}';
    }
}
