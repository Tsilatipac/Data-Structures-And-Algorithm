package com.lcy.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "曾经沧海难为水，除却巫山不是云";
        String str2 = "水";
        System.out.println("index = " + violenceMatch(str1, str2));
    }

    //暴力匹配算法实现
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int i = 0;
        int j = 0;
        while (i < s1.length && j < s2.length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == s2.length) {
            return i - j;
        }
        return -1;
    }

    //统计出现的次数
    public static int violenceCountMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int sum = 0;
        int j = 0;
        for (int i = 0; i < s1.length; ) {
            while (j < s2.length && s1[i] == s2[j]) {
                i++;
                j++;
            }
            if (j == s2.length) {
                sum++;
            }
            i = i - j + 1;
            j = 0;
        }
        return sum;
    }
}
