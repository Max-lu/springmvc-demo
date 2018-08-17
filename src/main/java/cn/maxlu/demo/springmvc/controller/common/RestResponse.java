package cn.maxlu.demo.springmvc.controller.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class RestResponse {

    private RestResponse(ResponseCode responseCode, String msg, Object data, Object extra) {
        this.responseCode = responseCode;
        this.code = responseCode.getCode();
        this.msg = msg;
        this.data = data;
        this.extra = extra;
    }

    public static RestResponse success(String msg, Object data, Object extra) {
        return new RestResponse(ResponseCode.Success, msg, data, extra);
    }

    public static RestResponse success(String msg, Object data) {
        return new RestResponse(ResponseCode.Success, msg, data, null);
    }

    public static RestResponse success(String msg) {
        return new RestResponse(ResponseCode.Success, msg, null, null);
    }

    public static RestResponse fail(String msg, Object data) {
        return new RestResponse(ResponseCode.Fail, msg, data, null);
    }

    public static RestResponse fail(String msg) {
        return new RestResponse(ResponseCode.Fail, msg, null, null);
    }

    @JsonIgnore
    private ResponseCode responseCode;

    private int code;

    private String msg;

    private Object data;

    private Object extra;

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private static enum ResponseCode {
        Success(0), Fail(1);

        private int code;

        ResponseCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
