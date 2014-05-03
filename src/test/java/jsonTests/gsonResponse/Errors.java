package jsonTests.gsonResponse;

/**
 * Created by Funker on 03.05.14.
 */
public class Errors {

    private String Reason;
    private String Param;
    private String ExtraInfo;

    public void setReason(String reason) {
        Reason = reason;
    }

    public void setParam(String param) {
        Param = param;
    }

    public void setExtraInfo(String extraInfo) {
        ExtraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return "Errors{" +
                "Reason='" + Reason + '\'' +
                ", Param='" + Param + '\'' +
                ", ExtraInfo='" + ExtraInfo + '\'' +
                '}';
    }

}
