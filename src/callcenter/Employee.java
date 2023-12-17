package callcenter;

import java.util.UUID;

public abstract class Employee {
    private UUID id = UUID.randomUUID(); // assign random ID to employee
    private String position;
    private Integer experienceLevel;
    private UUID assignedCall = null;

    public abstract void receiveCall(Call call);
    public abstract void finishCall();

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

    public UUID getAssignedCall() {
        return assignedCall;
    }

    public void setAssignedCall(UUID assignedCall) {
        this.assignedCall = assignedCall;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", experienceLevel=" + experienceLevel +
                ", assignedCall=" + assignedCall +
                '}';
    }
}
