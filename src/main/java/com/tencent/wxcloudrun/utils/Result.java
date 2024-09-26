package com.tencent.wxcloudrun.utils;


import com.tencent.wxcloudrun.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码,200表示成功", position = 0,example="200")
    private Integer code;

    @ApiModelProperty(value = "成功或错误消息", position = 1, example="succeed")
    private String message;

    @ApiModelProperty(value = "返回数据", position = 2)
    private T data;


    public Result(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        if (data == null || data.equals("")){
            this.data = null;
        }else {
            this.data = data;
        }
    }

    /**
     * 不带有返回值data类型
     *
     */
    public Result(String message, Integer code) {
        this.message = message;
        this.code = code;
        this.data = null;
    }

    public Result(T data) {
        this.message = "succeed";
        this.code = 200;
        if (data == null || data.equals("")){
            this.data = null;
        }else {
            this.data = data;
        }
    }

    public Result() {
        this.message = "succeed";
        this.code = 200;
        this.data = null;
    }

    public static <T> Result<T> error() {
        return new Result<T>(ResultEnum.FAILED.getMessage(), ResultEnum.FAILED.getCode());
    }

    public static <T> Result<T> error(String message) {
        return new Result<T>(message, ResultEnum.FAILED.getCode());
    }

    public static <T> Result<T> error(String message, T data) {
        return new Result<T>(message, ResultEnum.FAILED.getCode(), data);
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<T>(message, code, null);
    }

    public static <T> Result<T> error(Integer code, String message, T data) {
        return new Result<T>(message, code, data);
    }

    public static <T> Result<T> error(ResultEnum result) {
        return new Result<T>(result.getMessage(), result.getCode());
    }

    public static <T> Result<T> OK() {
        return new Result<T>();
    }

    /**
     * 成功，无返回值data
     *
     * @param message
     * @return
     */
    public static <T> Result<T> OK(String message) {
        return new Result<T>(message, ResultEnum.SUCCESS.getCode());
    }

    /**
     * 成功，有返回值data
     *
     * @param message
     * @return
     */
    public static <T> Result<T> OK(String message, T data) {
        return new Result<T>(message, ResultEnum.SUCCESS.getCode(), data);
    }

    /**
     * 业务出现问题时
     *
     * @return
     */
    public static <T> Result<T> build(String message, Integer code) {
        return new Result<T>(message, code);
    }

    /**
     * 业务出现问题时,有data
     *
     * @return
     */
    public static <T> Result<T> build(String message, Integer code, T data) {
        return new Result<T>(message, code, data);
    }


    /**
     * 返回@validated参数校验异常信息
     * @param exceptionMsg 异常信息
     */
    public static <T> Result<T> validatedException(String exceptionMsg){
        Result<T> result = new Result();
        result.setCode(ResultEnum.PARAMETER_ERROR.getCode());
        if(exceptionMsg == null || exceptionMsg.isEmpty()){
            exceptionMsg = ResultEnum.PARAMETER_ERROR.getMessage();
        }
        result.setMessage(exceptionMsg);
        result.setData(null);
        return result;
    }

}
