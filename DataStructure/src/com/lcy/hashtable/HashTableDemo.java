package com.lcy.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            String key = "";
            Scanner scanner = new Scanner(System.in);
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class HashTab {
    private final EmpLinkedList[] empLinkedListArray;
    private final int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public int hashFun(int id) {
        return id % size;
    }

    public void findEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.println("在第" + (empLinkedListNo + 1) + "条链表中找到雇员id=" + id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员");
        }
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinkedList {
    private Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = emp;
    }

    public void list(int i) {
        if (head == null) {
            System.out.println("第" + (i + 1) + "个链表为空");
            return;
        }
        Emp temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp temp = head;
        while (temp != null) {
            if (temp.id == id) {
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

}
