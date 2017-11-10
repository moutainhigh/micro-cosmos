package com.yichen.cosmos.cloud.platform.util;

import java.util.UUID;

/**
 * @author lwf
 * @ClassName: SUID
 * @Description: 系统唯一识别ID
 * @date 2016年3月23日 下午2:15:27
 */
public class SUID {

    private static long systemTimeMillis = 0l;
    private static long synchronizedNum = 0l;
    private static StringBuilder primaryUniqueKey;

    /**
     * @return String
     * @throws
     * @Title: getPUKey
     * @Description: 获得一个数据记录的主键Key<时间戳生成>
     */
    @Deprecated
    public synchronized static String getPUKey() {
        primaryUniqueKey = new StringBuilder(25);
        long currentTimeMillis = System.currentTimeMillis();
        // 同步判定
        if (currentTimeMillis > systemTimeMillis) {
            synchronizedNum = 0l;
            systemTimeMillis = currentTimeMillis;
        } else {
            synchronizedNum++;
        }
        primaryUniqueKey.append(systemTimeMillis).append(synchronizedNum);
        return primaryUniqueKey.toString();
    }

    /**
     * @return String
     * @throws
     * @Title: getUUID
     * @Description: 生成32位编码
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    /**
     * 生成默认密码
     *
     * @return
     */
    public static String getDefaultPassword() {
        String uuid = getUUID();
        String defaultPassword = uuid.substring(3, 12);
        return defaultPassword;
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }

}
