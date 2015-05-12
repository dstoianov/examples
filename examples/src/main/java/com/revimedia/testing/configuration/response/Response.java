package com.revimedia.testing.configuration.response;


/**
 * Created by Funker on 03.05.14.
 */
public class Response {

    private String _success;
    private String transactionId;
    private Errors[] ERRORS;
    private boolean success;
    private boolean isWarning;


    public void set_success(String _success) {
        this._success = _success;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setERRORS(Errors[] ERRORS) {
        this.ERRORS = ERRORS;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setWarning(boolean isWarning) {
        this.isWarning = isWarning;
    }

    public String get_success() {
        return _success;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Errors[] getErrors() {
        return ERRORS;
    }

    public boolean getSuccess() {
        return success;
    }

    public boolean getIsWarning() {
        return isWarning;
    }


    @Override
    public String toString() {
        return "Response{" +
                "_success='" + _success + '\'' +
                ", TransactionId='" + transactionId + '\'' +
                ", ERRORS=" + ERRORS +
                ", Success=" + success +
                ", IsWarning=" + isWarning +
                '}';
    }
}
