package com.lcy.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {

    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        String srcFile = System.getProperty("user.dir") + "/files/src.bmp";
        String dstFile = System.getProperty("user.dir") + "/files/src2.bmp";
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩成功！😊");
        String zipFile = System.getProperty("user.dir") + "/files/src2.bmp";
        String unzipDstFile = System.getProperty("user.dir") + "/files/src3.bmp";
        unZipFile(zipFile, unzipDstFile);
        System.out.println("解压成功！😊");

//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length); //40
//        System.out.println("nodes = " + nodes);
//        huffmanTree.preOrder();
//        System.out.println(huffmanCodes);
//        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
//        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
//        byte[] decode = decode(huffmanCodes, huffmanCodeBytes);
//        System.out.println("原来的字符串=" + new String(decode));

    }

    private static void unZipFile(String zipFile, String dstFile) {
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(zipFile)));
            os = new FileOutputStream(new File(dstFile));
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] decode = decode(huffmanCodes, huffmanBytes);
            os.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void zipFile(String srcFile, String dstFile) {
        FileInputStream fis = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(new File(srcFile));
            oos = new ObjectOutputStream(new FileOutputStream(new File(dstFile)));

            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            byte[] huffmanBytes = huffmanZip(bytes);
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
//        System.out.println("赫夫曼字节数组对应的二进制字符串=" + stringBuilder.toString());
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTree = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        return zip(bytes, huffmanCodes);
    }

    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println(stringBuilder);
        int len = (stringBuilder.length() + 7) / 8;
//        int len;
//        if (stringBuilder.length() % 8 == 0) {
//            len = stringBuilder.length() / 8;
//        } else {
//            len = stringBuilder.length() / 8 + 1;
//        }
        byte[] huffmanCodesBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodesBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanCodesBytes;
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            counts.merge(b, 1, Integer::sum);
        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}

class Node implements Comparable<Node> {

    Byte data; //存放数据本身
    int weight; //权值
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}