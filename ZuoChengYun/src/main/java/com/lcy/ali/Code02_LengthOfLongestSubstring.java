package com.lcy.ali;

import java.util.HashSet;

/**
 * 给定长度为m的字符串aim，以及一个长度为n的字符串str能否在str中找到一个长度为m的连续子串，
 * 使得这个子串刚好由aim的m个字符组成，顺序无所谓，返回任意满足条件的一个子串的起始位置，未找到返回-1
 *
 * @author lichenyu
 */
public class Code02_LengthOfLongestSubstring {
    public static void main(String[] args) {
        int i = 2 + 3;
    }

    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> charCountSet = new HashSet<>();
        int length = s.length();
        int result = 0;
        for(int i = 0; i < length; i++){
            if(i != 0 ){
                charCountSet.remove(s.charAt(i));
            }
            int tempCount = 0;
            int j = -1;
            while(j + 1 < length && !charCountSet.contains(s.charAt(j + 1))){
                charCountSet.add(s.charAt(j + 1));
                tempCount ++;
                j++;
            }
            result = Math.max(result, tempCount);
        }
        return result;
    }

    public static boolean isTY(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        char[] str1 = A.toCharArray();
        char[] str2 = B.toCharArray();
        int[] count = new int[256];

        for (int i = 0; i < str1.length; i++) {
            count[str1[i]]++;
        }

        for (int i = 0; i < str2.length; i++) {
            if (count[str2[i]]-- == 0) {
                return false;
            }
        }
        return true;
    }

    public static int containExactly2(String s, String a) {
        if (s == null || a == null || s.length() < a.length()) {
            return -1;
        }

        char[] str = s.toCharArray();
        char[] aim = a.toCharArray();

        for (int L = 0; L <= str.length - aim.length; L++) {
            if (isCountEqual(str, L, aim)) {
                return L;
            }
        }
        return -1;
    }

    public static boolean isCountEqual(char[] str, int L, char[] aim) {
        int[] count = new int[256];
        for (char c : aim) {
            count[c]++;
        }
        for (int i = 0; i < aim.length; i++) {
            if (count[str[L + i]]-- == 0) {
                return false;
            }
        }
        return true;
    }

    public static int containExactly(String s, String a) {
        if (s == null || a == null || s.length() < a.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] aim = a.toCharArray();
        int[] count = new int[256];
        for (char c : aim) {
            count[c]++;
        }
        int M = aim.length;
        int invalidCount = 0;
        int R = 0;

        for (; R < M; R++) {
            if (count[str[R]]-- <= 0) {
                invalidCount++;
            }
        }
        for (; R < str.length; R++) {
            if (invalidCount == 0) {
                return R - M;
            }
            if (count[str[R]]-- <= 0) {
                invalidCount++;
            }
            if (count[str[R - M]]++ < 0) {
                invalidCount--;
            }
        }
        return invalidCount == 0 ? R - M : -1;
    }
}
