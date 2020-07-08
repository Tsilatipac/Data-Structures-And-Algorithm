package com.lcy.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "Bob");
        HeroNode node2 = new HeroNode(2, "Tom");
        HeroNode node3 = new HeroNode(3, "Jack");
        HeroNode node4 = new HeroNode(4, "Jay");
        HeroNode node5 = new HeroNode(5, "Phil");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setTree(root);

//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//
//        System.out.println("前序遍历查找");
//        HeroNode preOrderSearchNode = binaryTree.preOrderSearch(5);
//        System.out.println(preOrderSearchNode);
//        System.out.println("中序遍历查找");
//        HeroNode infixOrderSearchNode = binaryTree.infixOrderSearch(5);
//        System.out.println(infixOrderSearchNode);
//        System.out.println("后序遍历查找");
//        HeroNode postOrderSearchNode = binaryTree.postOrderSearch(5);
//        System.out.println(postOrderSearchNode);
        binaryTree.delNode(5);
        binaryTree.preOrder();
        binaryTree.delNode(1);
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    public void setTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找");
        if (this.no == no) {
            return this;
        }
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.preOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        if (this.right != null) {
            resultNode = this.right.preOrderSearch(no);
        }
        return resultNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.infixOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        System.out.println("进入中序遍历查找");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resultNode = this.right.infixOrderSearch(no);
        }
        return resultNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.postOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        if (this.right != null) {
            resultNode = this.right.postOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        System.out.println("进入后序遍历查找");
        if (this.no == no) {
            return this;
        }
        return resultNode;
    }
}