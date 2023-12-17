package callcenter;

import java.util.UUID;

public class Call {

    private UUID id = UUID.randomUUID(); // assign random ID to call
    private Integer requiredExperienceLevel = 1; // Default is the lowest exp level
    private UUID callHandler = null; // employee that is handling the call
    private boolean inQueue = false;
    private boolean callEnded = false; // call is finished

    public void closeCall() {
        setInQueue(false);
        setCallEnded(true);
    }

    // Constructor
    public Call() {
    }

    // Getters/Setters
    public UUID getId() {
        return id;
    }

    public Integer getRequiredExperienceLevel() {
        return requiredExperienceLevel;
    }

    public void setRequiredExperienceLevel(Integer requiredExperienceLevel) {
        this.requiredExperienceLevel = requiredExperienceLevel;
    }

    public void setCallHandler(UUID callHandler) {
        this.callHandler = callHandler;
    }

    public void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
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
