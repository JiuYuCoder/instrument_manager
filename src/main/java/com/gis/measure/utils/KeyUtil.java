package com.gis.measure.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一的随机数列，记得上同步锁，防止高并发时产生相同的ID
     * 格式：时间 + 随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
