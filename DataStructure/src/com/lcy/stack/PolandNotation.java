package com.lcy.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List"+strings);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(strings);
        System.out.println("后缀表达式对应的List"+parseSuffixExpressionList);

    }

    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;
        String str;
        char ch;
        do {
            if ((ch = s.charAt(i)) < 48 || (ch = s.charAt(i)) > 57) {
                ls.add(ch + "");
                i++;
            } else {
                str = "";
                while (i < s.length() && (ch = s.charAt(i)) >= 48 && (ch = s.charAt(i)) <= 57) {
                    str += ch;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    public static List<String> parseSuffixExpressionList(List<String> ls) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }
}

class Operation {
    public static int ADD = 1;
    public static int SUB = 2;
    public static int MUL = 3;
    public static int DIV = 4;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}