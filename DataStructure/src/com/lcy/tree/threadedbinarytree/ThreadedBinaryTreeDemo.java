package com.lcy.tree.threadedbinarytree;

import lombok.Getter;
import lombok.Setter;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试: 以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 ="  + leftNode); //3
        System.out.println("10号结点的后继结点是="  + rightNode); //1

//        //当线索化二叉树后，能在使用原来的遍历方法
//        //threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}

class ThreadedBinaryTree {
    private HeroNode root;

    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    private HeroNode pre = null;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    public void threadedNodes(HeroNode heroNode) {
        if (heroNode == null) {
            return;
        }
        threadedNodes(heroNode.getLeft());
        if (heroNode.getLeft() == null) {
            heroNode.setLeft(pre);
            heroNode.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(heroNode);
            pre.setRightType(1);
        }
        pre = heroNode;
        threadedNodes(heroNode.getRight());
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

    public void threadedList() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
}

@Getter
@Setter
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //如果leftType == 0 表示指向的是左子树，如果1表示指向前驱结点
    //如果leftType == 0 表示指向的是右子树，如果1表示指向后继结点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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