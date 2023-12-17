package callcenter.factory;

import callcenter.CallCenter;

public class CallCenterFactory {
    public static CallCenter createCallCenter() {
        // Singleton instance
        return CallCenter.getInstance();
    }
}
