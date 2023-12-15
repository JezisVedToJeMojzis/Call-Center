package callcenter;

import java.util.UUID;

public abstract class Employee {
    private UUID id;
    private String position;
    private boolean inCall = false;
    private boolean onBreak = false;

    public Employee() {
        this.id = UUID.randomUUID(); // assign random ID to employee
    }
    public abstract void processCall(Call call);

    void goOnBreak(){
        this.setOnBreak(true);
    }

    void finishBreak(){
        this.setOnBreak(false);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isInCall() {
        return inCall;
    }

    public void setInCall(boolean inCall) {
        this.inCall = inCall;
    }

    public boolean isOnBreak() {
        return onBreak;
    }

    public void setOnBreak(boolean onBreak) {
        this.onBreak = onBreak;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", inCall=" + inCall +
                ", onBreak=" + onBreak +
                '}';
    }
}
