package com.xunyi.cloud.wisdom.activiti.service.impl;

import com.xunyi.cloud.wisdom.activiti.service.IRegularService;
import com.yichen.cosmos.cloud.platform.util.StringTools;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Author:thomas su
 * @Date: 2018/8/8 11:24
 * @Description: !!!!注意序列化;正则匹配  用于线条件
 */
@Service
public class RegularService implements IRegularService,Serializable {
    @Override
    public boolean match(String A, String B, boolean isFormula) {
        System.out.println("正在表达式：A:"+A+", B:"+B +", isFormula:"+isFormula);
        return false;
    }

    /**
     *数字判断
     * @param number
     * @return
     */
    @Override
    public boolean isNumber(String number) {
       return StringTools.isNumberic(number,true,true,true);
    }


}
