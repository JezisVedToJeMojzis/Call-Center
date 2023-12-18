package callcenter;

import java.util.UUID;

public class Call {
    private UUID id = UUID.randomUUID(); // assign random ID to call
    private Integer requiredExperienceLevel = 1; // Default is the lowest exp level
    private UUID callHandler = null; // employee that is handling the call
    private boolean inQueue = false;
    private boolean callEnded = false; // call is finished

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
        if(requiredExperienceLevel >= 1 && requiredExperienceLevel <= 10){
            this.requiredExperienceLevel = requiredExperienceLevel;
        }else{
            System.out.println("Required experience level is out of scope (must be between 1-10).");
        }
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
                ", requiredExperienceLevel=" + requiredExperienceLevel +
                ", callHandler=" + callHandler +
                ", inQueue=" + inQueue +
                ", callEnded=" + callEnded +
                '}';
    }
}
