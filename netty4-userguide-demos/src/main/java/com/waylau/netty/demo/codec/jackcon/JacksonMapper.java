package com.waylau.netty.demo.codec.jackcon;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by thomas.su on 2017/11/28 19:14.
 */
public class JacksonMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return mapper;
    }
}
