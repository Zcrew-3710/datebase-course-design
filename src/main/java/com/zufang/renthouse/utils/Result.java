package com.zufang.renthouse.utils;

import lombok.Data;

/**
 * 统一返回结果工具类（补全所有重载方法，解决success()解析失败）
 */
@Data
public class Result<T> {
    private Integer code; // 状态码（200=成功，400=参数错，500=业务错）
    private String msg;   // 提示信息
    private T data;       // 数据体（泛型兼容任意类型）

    // ========== 1. 无参success()：默认“操作成功”+空数据+200码 ==========
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功"); // 默认提示语
        result.setData(null);
        return result;
    }

    // ========== 2. 仅提示信息的success() ==========
    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    // ========== 3. 仅数据的success() ==========
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    // ========== 4. 提示信息+数据的success() ==========
    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    // ========== 失败相关方法 ==========
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> paramError(String msg) {
        Result<T> result = new Result<>();
        result.setCode(400);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}