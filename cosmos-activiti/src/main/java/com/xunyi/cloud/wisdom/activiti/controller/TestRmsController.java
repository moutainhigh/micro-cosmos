package com.xunyi.cloud.wisdom.activiti.controller;

import com.xunyi.cloud.wisdom.activiti.model.RmsFactorInfo;
import com.xunyi.cloud.wisdom.activiti.model.RmsFieldInfo;
import com.xunyi.cloud.wisdom.activiti.service.rms.RmsFactorInfoService;
import com.xunyi.cloud.wisdom.activiti.service.rms.RmsFieldInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:thomas su
 * @Date: 2018/8/20 16:57
 * @Description:
 */
@RestController
@RequestMapping(value = "/testRms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestRmsController {

    @Autowired
    private RmsFactorInfoService rmsFactorInfoService;
    @Autowired
    private RmsFieldInfoService rmsFieldInfoService;

    @RequestMapping(value = "/taskComplete", method = RequestMethod.GET)
    public String transferRmsFactor(){
        List<RmsFactorInfo> factorInfos =  rmsFactorInfoService.selectAllFactors();

        AtomicInteger index = new AtomicInteger(1);
        factorInfos.stream().forEach(factorItem->{
            RmsFieldInfo rmsFieldInfo = new RmsFieldInfo();
            String factorCode = factorItem.getFactorCode();
            if(factorCode.contains("#")){//示例：pacra_risk_items#l1wwdcn_TNumsCon_bank
                factorCode = factorCode.replace("#","_");
            }else if(factorCode.contains(":")){//带有list，示例：jxl_id_card_court_blacklist : ["jxl_id_card_court_blacklist_arised","jxl_id_card_court_blacklist_black_type"]
                factorCode = factorCode.split(":")[0].trim();
            }

            String factorName = factorItem.getFactorName();
            String sourceName = factorItem.getSourceName();
            if(!factorName.startsWith(sourceName)){
                factorName = sourceName +"_"+ factorName;
            }

            rmsFieldInfo.setFieldCode(transferCode(factorCode));
            rmsFieldInfo.setFieldName(factorName);

            rmsFieldInfo.setSourceName(factorItem.getSourceName());
            rmsFieldInfo.setFactorCode(factorItem.getFactorCode());
            rmsFieldInfo.setFactorName(factorItem.getFactorName());

            int i = index.addAndGet(1);
            System.out.println("第【"+i+"】个插入操作");
            rmsFieldInfoService.insertSelective(rmsFieldInfo);
        });

        return "{\"code\":200}";
    }


    public static  String transferCode(String params){
        StringBuilder result = new StringBuilder();
        String[] paramArr = params.split("_");
        for(int i = 0; i< paramArr.length;i++){
            if( i== 0){
                result.append(paramArr[i]);
            }else{
                result.append(String.valueOf(paramArr[i].charAt(0)).toUpperCase() + paramArr[i].substring(1));
            }
        }
        return result.toString();
    }

    public static void main(String[] args){
        String params = "thomas_su_address";
        System.out.println(transferCode(params));
    }
}
