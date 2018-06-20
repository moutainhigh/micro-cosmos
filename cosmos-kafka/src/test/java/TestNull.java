import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @Author:thomas
 * @Date: 2018/5/3 14:04
 * @Description:
 */
public class TestNull {
    public static void main(String[] args){

//        List list = new ArrayList();
//        list.add("1");
//        list.add("12");
//        list.add("13");
//
//        final CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<String>(list);
//        Iterator<String> iterm =   cowList.iterator();
//        while(iterm.hasNext()){
//            String item = iterm.next();
//            cowList.remove(item);
//        }
//
//        System.out.println(list.size());
//        System.out.println(cowList.size());

//        Map<String,String> hash = new HashMap<>();
//        hash.put("1","1");
//        Map<String,String>  hash1 = new ConcurrentHashMap<>(hash);
//        System.out.println("hash:"+JSON.toJSONString(hash));
//        System.out.println("hash1:"+JSON.toJSONString(hash1));
//
//        Map<String,String> batchOrgMoneyMap = new HashMap<>();
//        batchOrgMoneyMap.put("df3248b79b82422e80653fd9c468e93d","6949.33071048000044543702102828319766558706760406494140625000000");
//        batchOrgMoneyMap.put("2f70c41f17f046dca6a2843076a499d7","69.5087860399999999773575087846921860545990057289600372314453125000000");
//        batchOrgMoneyMap.put("86abb798fd774537af735c2435e85fc1","6949.33071048000044543702102828319766558706760406494140625000000");
//        batchOrgMoneyMap.put("998cda3633fa4dc7b28285d21d5ad667","6949.33071048000044543702102828319766558706760406494140625000000");
//
//
//        String[] entrustOrgIds = batchOrgMoneyMap.keySet().toArray(new String[0]);
//        List<String> entrustOrgIdList = Arrays.asList(entrustOrgIds);
//
//        System.out.println(JSON.toJSON(entrustOrgIdList));

        test33();
    }


    public static void test33(){

        List<Map<String,String>>  orgCountList = new ArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("orgId","2f70c41f17f046dca6a2843076a499d7");
        map1.put("entrustCount","7");

        Map<String,String> map2 = new HashMap<>();
        map2.put("orgId","998cda3633fa4dc7b28285d21d5ad667");
        map2.put("entrustCount","2");

        Map<String,String> map3 = new HashMap<>();
        map3.put("orgId","df3248b79b82422e80653fd9c468e93d");
        map3.put("entrustCount","9");
        Map<String,String> map4 = new HashMap<>();
        map4.put("orgId","86abb798fd774537af735c2435e85fc1");
        map4.put("entrustCount","2");


        orgCountList.add(map1);
        orgCountList.add(map2);
        orgCountList.add(map3);
        orgCountList.add(map4);

        Collections.sort(orgCountList, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {

                Integer entrustCount1 = Integer.parseInt(o1.get("entrustCount"));
                Integer entrustCount2 = Integer.parseInt(o2.get("entrustCount"));
                return entrustCount1 - entrustCount2;
            }
        });

        System.out.println("sort:"+JSON.toJSONString(orgCountList));
        if(CollectionUtils.isEmpty(orgCountList)){
            //一般不会为空
            System.out.println("kong");
        }else if(orgCountList.size() == 1){
            System.out.println("111:  "+orgCountList.get(0).get("orgId"));
        }else {
            //说明存在多条记录，找出次数最少且相等的记录，然后随机获取一个
            //已经按照次数从小到大排序好了，循环中，如果发现不等，则终止循环
            List<String>  tmpOrgIdList = new ArrayList<>();
            Integer firstEntrustCount = null;

            for(Map<String,String> tmpOrgCountMap:orgCountList){
                if(CollectionUtils.isEmpty(tmpOrgIdList)){
                    tmpOrgIdList.add(tmpOrgCountMap.get("orgId"));
                    firstEntrustCount = Integer.parseInt(tmpOrgCountMap.get("entrustCount"));
                }else{
                    Integer nextEntrustCount = Integer.parseInt(tmpOrgCountMap.get("entrustCount"));
                    if(nextEntrustCount == firstEntrustCount){
                        tmpOrgIdList.add(tmpOrgCountMap.get("orgId"));
                    }else{
                        break;
                    }
                }
            }
            System.out.println("tmpOrgIdList:"+JSON.toJSONString(tmpOrgIdList));
            //随机选取一个
            if(CollectionUtils.isNotEmpty(tmpOrgIdList)){
                Random random = new Random();
                String[] randomOrgIdsArr = tmpOrgIdList.toArray(new String[0]);
                String entrustOrgId = randomOrgIdsArr[random.nextInt(randomOrgIdsArr.length)];
                System.out.println("entrustOrgId:"+entrustOrgId);
            }
        }
    }

    static class Transform{
        private Date startDate;
        private Date endDate;

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }
    }
}
