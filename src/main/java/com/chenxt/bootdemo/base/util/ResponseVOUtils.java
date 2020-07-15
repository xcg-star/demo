package com.chenxt.bootdemo.base.util;


import com.chenxt.bootdemo.base.vo.ResponseVO;

/**
 * The type Response vo utils.
 *
 * @author chenxt
 * @date 2020/07/15
 */
public class ResponseVOUtils {
    /**
     * Success response vo.
     *
     * @return the response vo
     * @description 请求成功返回对象
     */
    public static ResponseVO success() {
        return success(null);
    }

    /**
     * Success response vo.
     *
     * @param data 返回的数据
     * @return 返回成功 ，包含code、message、data
     */
    public static ResponseVO success(Object data) {
        int code = 0;
        String message = "调用成功";
        return success(code, message, data);
    }

    /**
     * Success response vo.
     *
     * @param code    响应码
     * @param message 相应信息
     * @param data    返回的数据
     * @return 返回成功 ，包含code、message、data
     */
    public static ResponseVO success(int code, String message, Object data) {
        return new ResponseVO(code, message, data);
    }


    /**
     * Error response vo.
     *
     * @param code    响应码
     * @param message 相应信息
     * @return 返回失败 ，包含code、message、data
     */
    public static ResponseVO error(int code, String message) {
        return new ResponseVO(code, message, null);
    }

    /**
     * Error response vo.
     *
     * @param code    响应码
     * @param message 相应信息
     * @param filed   the filed
     * @return 返回失败 ，包含code、message、data
     */
    public static ResponseVO error(int code, String message, String filed) {
        return new ResponseVO(code, message, filed);
    }

}
