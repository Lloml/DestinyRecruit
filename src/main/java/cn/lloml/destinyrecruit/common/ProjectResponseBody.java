package cn.lloml.destinyrecruit.common;


import java.io.Serializable;

public class ProjectResponseBody implements Serializable {

    private boolean success;
    private Object data;
    private String message;
    private String errorCode;
    private String errorMessage;
    private int showType;
    private String traceId;
    private String host;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    boolean judgeSuccess(int statusCode) {
        return statusCode >= 200 & statusCode < 300;
    }

    public ProjectResponseBody(int statusCode, String message, Object data) {
        if (judgeSuccess(statusCode)) {
            this.success = true;
            this.data = data;
            this.message = message;
        } else {
            this.success = false;
            this.data = data;
            this.errorCode = String.valueOf(statusCode);
            this.showType = 1;
            this.errorMessage = message;
        }
    }

    public ProjectResponseBody(int statusCode, String message) {
        if (judgeSuccess(statusCode)) {
            this.success = true;
            this.message = message;
        } else {
            this.success = false;
            this.errorCode = String.valueOf(statusCode);
            this.showType = 1;
            this.errorMessage = message;
        }
    }
}
