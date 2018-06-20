import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author:thomas
 * @Date: 2018/4/23 14:31
 * @Description:
 */
public class MainTest {
    public static void main(String[] args){
        testData();
    }

    public static String test1111(){
        JSONObject report  = new JSONObject();







        if(!report.keySet().contains("zcgx_flowId")
                || StringUtils.isEmpty(report.getString("zcgx_flowId"))){
            return "无报告";
        }

        Set<String> keys = new TreeSet<>();
        Set<String> reportKeys = report.keySet();




return "";

    }





    public static void testData(){
        Map<String,String> batchOrgMoneyMap = new HashMap<>();
        batchOrgMoneyMap.put("1","1");
        batchOrgMoneyMap.put("2","2");


        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        String[] entrustOrgIds = batchOrgMoneyMap.keySet().toArray(new String[0]);
        if(entrustOrgIds != null){
            List<String> entrustOrgIdList = Arrays.asList(entrustOrgIds);
            final CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>(entrustOrgIdList);
            if(CollectionUtils.isNotEmpty(list)){
                for(String s: list){
                    if(CollectionUtils.isNotEmpty(cowList)
                            && cowList.contains(s)){
                        cowList.remove(s);
                    }
                }
            }

            System.out.println("cowList="+ JSON.toJSONString(cowList));
            System.out.println("entrustOrgIds="+ JSON.toJSONString(entrustOrgIds));
            System.out.println("batchOrgMoneyMap="+ JSON.toJSONString(batchOrgMoneyMap));
        }

    }


    public static void test2(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        final CopyOnWriteArrayList<Integer> cowList = new CopyOnWriteArrayList<Integer>(list);
        for(Integer item: cowList){
            if(item % 2 == 0){
                cowList.remove(item);
            }
        }
        System.out.println(cowList);

    }

    public void test(){
        List<String> list = Arrays.asList(new String[]{"a","b","c"});
        System.out.println("1. list="+list);
        List arrList = new ArrayList(list);
        Iterator<String> iter =arrList.iterator();
        while(iter.hasNext()){
            String ch = iter.next();
            if("b".equals(ch)){
                iter.remove();
            }
        }

        System.out.println("2. arrList="+arrList);
    }
}
