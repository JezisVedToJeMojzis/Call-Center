package callcenter.factory;

import callcenter.Call;
import callcenter.CallCenter;

public class CallFactory {
    public static Call createCall() {
        Call call = new Call();
        CallCenter.getInstance().receiveCall(call); // Adding the incoming call into Call Center list of incoming calls
        return call;
    }
}
