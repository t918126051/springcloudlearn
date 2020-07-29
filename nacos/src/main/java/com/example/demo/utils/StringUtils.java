package com.example.demo.utils;

public class StringUtils {
    /**
     * 驼峰转下划线
     * @param stringName
     * @return
     */
    public static String  toUnderString(String stringName){
        StringBuffer stringBuffer = new StringBuffer(stringName);
        int temp = 0;
        if(!stringName.contains("_")){
            for ( int i = 0; i < stringName.length(); i++ ){
                if (Character.isUpperCase(stringName.charAt(i))){
                    stringBuffer.insert(temp+i,"_");
                    temp+=1;
                }
            }
        }
        return stringBuffer.toString().toLowerCase();
    }
}
