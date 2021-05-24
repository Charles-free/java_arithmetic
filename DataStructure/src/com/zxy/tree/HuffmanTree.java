package com.zxy.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        Node root=creatHuffmanTree(arr);
        preOrder(root);
    }
    public static Node creatHuffmanTree(int[] arr){
        List<Node> nodes= new ArrayList<>();//为了方便操作，创建集合
        for(int value:arr){    //将数组里的元素都放入到集合中
            nodes.add(new Node(value));
        }
        //循环，只要集合里的元素大于1，就重复以下操作
        while(nodes.size()>1) {
            Collections.sort(nodes);//对集合里的元素（Node)进行排序，前提是Node实现了Comparable接口
            //取出集合的前两个元素，即最小的两个元素
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            //构成一颗新的二叉树
            Node parent = new Node(left.value + right.value);//这颗新二叉树的权值等于前面两颗二叉树权值之和
            //将这两颗二叉树添加到新的二叉树中
            parent.left=left;
            parent.right=right;
            //移除两颗旧二叉树，并添加新的这颗二叉树
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        //循环结束，输出剩下的那颗二叉树，即哈夫曼树
        return nodes.get(0);
    }
    //前序遍历哈夫曼树
    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else{
            System.out.println("二叉树为空无法遍历");
        }
    }
}
class Node implements Comparable<Node> {
    int value;//权值
    Node left;//左子树
    Node right;//右子树

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value-o.value;
    }
    //编写前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
}
