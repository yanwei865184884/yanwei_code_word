package com.example.yanwei.common.tools;

public class TestDemo {

    public static void main(String[] args) {
        String dm = "ABCD";
        char[] chars = dm.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            int a = aChar;
            System.out.println(a);
        }
        //System.out.println(Integer.valueOf(dm));
    }



}
