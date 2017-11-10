package com.yichen.cosmos.cloud.platform.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Response {


    private static final SerializerFeature[] features = { //SerializerFeature.QuoteFieldNames, // 是否使用双引号
            SerializerFeature.WriteMapNullValue // 输出值为null的字段
//			SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null,输出为0
//			SerializerFeature.WriteNullListAsEmpty, // List字段如果为null,输出为[],而非null
//			SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null,输出为”“,而非null
//			SerializerFeature.WriteNullBooleanAsFalse // Boolean字段如果为null,输出为false,而非null
    };

    //返回码
    private String code;
    //返回消息
    private String msg;
    //返回数据
    private Object data;
    //错误参数名称
    private String errparam;


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public String getErrparam() {
        return errparam;
    }

    public static class Builder {
        private String code;
        private String msg;
        private Object data;
        private String errparam;

        public Builder() {
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder errparam(String errparam) {
            this.errparam = errparam;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }

    private Response(Builder b) {
        this.code = b.code;
        this.msg = b.msg;
        this.data = b.data;
        this.errparam = b.errparam;
    }

    public String toString() {
        return JSON.toJSONString(this, features);
    }

    public String toStringIgnoreNull() {
        return JSON.toJSONString(this);
    }
}
