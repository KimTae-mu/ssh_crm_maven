package com.crm.utils;

/**
 * Created by Taeyeon-Serenity on 2017/8/20.
 */

import java.util.UUID;

/**
 * 文件上传的工具类
 * */
public class UploadUtils {
    /**
     * 传入文件的名称，返回的唯一名称
     * */
    public static String getUUIDName(String filename){
        //先查找
        int index = filename.lastIndexOf(".");
        //截取
        String lastname = filename.substring(index, filename.length());

        //唯一字符串
        String uuid= UUID.randomUUID().toString().replace("-","");

        return uuid+lastname;
    }

    public static void main(String[] args) {
        String filename="girl.jpg";
        String uuid = getUUIDName(filename);
        System.out.println(uuid);
    }
}
