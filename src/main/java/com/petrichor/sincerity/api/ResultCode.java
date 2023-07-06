package com.petrichor.sincerity.api;

/**
 * API返回码封装类
 * Created by macro on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功", true),
    FAILED(500, "操作失败", false),
    VALIDATE_FAILED(404, "参数检验失败", false),
    UNAUTHORIZED(401, "暂未登录或token已经过期", false),
    FORBIDDEN(403, "没有相关权限", false);
    private final long code;
    private final String message;
    private final boolean ok;

    private ResultCode(long code, String message, boolean ok) {
        this.code = code;
        this.message = message;
        this.ok = ok;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getOk() {
        return ok;
    }
}
