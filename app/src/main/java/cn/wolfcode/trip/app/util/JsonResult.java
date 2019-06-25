package cn.wolfcode.trip.app.util;

/**
 * @Auther:S
 * @Date:19/6/20
 */
public class JsonResult {
    private boolean success=true;
    private String errorMsg;
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
