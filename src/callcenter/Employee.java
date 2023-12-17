package callcenter;

import java.util.UUID;

public abstract class Employee {
    private UUID id = UUID.randomUUID(); // assign random ID to employee
    private String position;
    private Integer experienceLevel;
    private boolean inCall = false;

    public abstract void receiveCall(Call call);

    //public abstract void escalateCall(Call call);

    // Constructor
    public Employee() {
    }

    // Getters/Setters
    public UUID getId() {
        return id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(Integer experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public boolean isInCall() {
        return inCall;
    }

    public void setInCall(boolean inCall) {
        this.inCall = inCall;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", inCall=" + inCall +
                '}';
    }
}
