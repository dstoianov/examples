package jsonTests.gsonResponse;

/**
 * Created by Funker on 19.11.2014, 1:36.
 */
public class DisclaimerText {

    private String tcpa;
    private String bestq;

    public DisclaimerText(String tcpa, String bestq) {
        this.tcpa = tcpa;
        this.bestq = bestq;
    }

    public DisclaimerText() {
    }

    public String getTcpa() {
        return tcpa;
    }

    public void setTcpa(String tcpa) {
        this.tcpa = tcpa;
    }

    public String getBestq() {
        return bestq;
    }

    public void setBestq(String bestq) {
        this.bestq = bestq;
    }
}
