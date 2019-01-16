package cc.demo.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by conn on 16/3/1.
 */
@ApiModel(description = "返回值信息")
public class ResultEntity<T> {

    public static final String OK_MSG = "ok";
    public static final int OK_CODE = 0;
    @ApiModelProperty(value = "结果代码")
    private int code;
    @ApiModelProperty(value = "错误信息")
    private String msg;
    @ApiModelProperty(value = "结果数据" )
    private  T data;

    public static String getOkMsg() {
        return OK_MSG;
    }

    public static int getOkCode() {
        return OK_CODE;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

      public ResultEntity (T obj) {
        this(OK_CODE, OK_MSG, obj);
    }

//    public static ResultEntity newResultEntity(String msg, Object obj) {
//        return newResultEntity(OK_CODE, msg, obj);
//    }

    public ResultEntity (int code, String msg, T data) {
            this.code = code;
            this.msg = msg;
            this.data = data;

    }
//    public ResultEntity () {
//    }
//    public static ResultEntity newResultEntity(int code, String msg, Object data) {
//        ResultEntity resultEntity = new ResultEntity();
//        resultEntity.setCode(code);
//        resultEntity.setMsg(msg);
//        resultEntity.setData(data);
//        return resultEntity;
//    }

//    public static ResultEntity newErrEntity() {
//        return newErrEntity(ERR_CODE, ERR_MSG);
//    }

//    public static ResultEntity newErrEntity(String msg) {
//        return newErrEntity(ERR_CODE, msg);
//    }

//    public static ResultEntity newErrEntity(int code, String msg) {
//        ResultEntity errEntity = new ResultEntity();
//        errEntity.setCode(code);
//        errEntity.setMsg(msg);
//        return errEntity;
//    }
}
