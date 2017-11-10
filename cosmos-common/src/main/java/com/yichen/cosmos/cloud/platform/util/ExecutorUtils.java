package com.yichen.cosmos.cloud.platform.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by thomas on 2017/3/10 16:27.
 */
public class ExecutorUtils {

    public static final Logger logger = LoggerFactory.getLogger(ExecutorUtils.class);
    //工程版本的锁定，解锁线程池
    public static final Map<String, ExecutorService> versionExecutorMap = new HashMap<String, ExecutorService>();

    //固定线程
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    //分配每个工程版本的处理线程
    public static ExecutorService getSystemExecutorService(String versionId) {
        try {
            if (versionExecutorMap != null && !versionExecutorMap.isEmpty()) {
                if (StringTools.isNotEmpty(versionId)) {
                    String key = versionId.substring(versionId.length() - 1);
                    key = key.toUpperCase();
                    ExecutorService executorService = versionExecutorMap.get(key);
                    if (executorService == null) {
                        return executor;
                    }
                    return executorService;
                }
            }
            return executor;
        } catch (Exception e) {
            logger.error("线程池异常.e={}", e);
            e.printStackTrace();
        }
        return executor;
    }

}
