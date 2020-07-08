package com.lcy.sparsearray;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 创建一个原始的二维数组11*11
        // 0：表示没有棋子，1：表示黑子，2：表示蓝子
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][6] = 3;
        chessArray[5][6] = 3;
        // 输出原始的二维数组
        System.out.println("原始的二维数组：");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组转稀疏数组的思想
        // 1、先遍历二维数组 得到非0的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println();
        System.out.println("sum=" + sum);

        // 2、创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到sparseArr中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArray[i][j];
                }
            }
        }

        // 输出稀疏数组的值
        System.out.println();
        System.out.println("得到稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("abc.data"));
//        oos.writeObject(sparseArr);
//        oos.flush();
//        oos.close();
//
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("abc.data"));
//        int sparseArr2[][] = (int[][]) ois.readObject();
//        System.out.println("得到序列化的稀疏数组为：");
//        for (int i = 0; i < sparseArr2.length; i++) {
//            System.out.printf("%d\t%d\t%d\t\n",sparseArr2[i][0],sparseArr2[i][1],sparseArr2[i][2]);
//        }
//        System.out.println();

        // 将稀疏数组恢复成原始的二维数组
        // 1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArray2[][]=new int [sparseArr[0][0]][sparseArr[0][1]];

        // 2、读取稀疏数组后几行数据，并赋给原始二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArray2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        // 恢复后的二维数组
        System.out.println("恢复后的二维数组：");
        for (int[] row : chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
