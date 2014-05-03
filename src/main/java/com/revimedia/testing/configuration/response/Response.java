package com.revimedia.testing.configuration.response;


/**
 * Created by Funker on 03.05.14.
 */
public class Response {

    private String _success;
    private String TransactionId;
    private Errors[] ERRORS;
    private boolean Success;
    private boolean IsWarning;


    public void set_success(String _success) {
        this._success = _success;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public void setERRORS(Errors[] ERRORS) {
        this.ERRORS = ERRORS;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public void setWarning(boolean isWarning) {
        IsWarning = isWarning;
    }

    public String get_success() {
        return _success;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public Errors[] getERRORS() {
        return ERRORS;
    }

    public boolean isSuccess() {
        return Success;
    }

    public boolean isWarning() {
        return IsWarning;
    }


    @Override
    public String toString() {
        return "Response{" +
                "_success='" + _success + '\'' +
                ", TransactionId='" + TransactionId + '\'' +
                ", ERRORS=" + ERRORS +
                ", Success=" + Success +
                ", IsWarning=" + IsWarning +
                '}';
    }
}
