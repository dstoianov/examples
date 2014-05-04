package com.revimedia.testing.configuration.response;

/**
 * Created by Funker on 03.05.14.
 */
public class Errors {
    private String reason;
    private String param;
    private String extraInfo;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getReason() {
        return reason;
    }

    public String getParam() {
        return param;
    }

    public String getExtraInfo() {
        return extraInfo;
    }


    @Override
    public String toString() {
        return "Errors{" +
                "reason='" + reason + '\'' +
                ", param='" + param + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                '}';
    }

}
