package com.zxy.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree=new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        arrBinaryTree.preOrder();
        System.out.println("中序遍历");
        arrBinaryTree.infixOrder();
        System.out.println("后续遍历");
        arrBinaryTree.postfixOrder();
    }

}
class ArrBinaryTree{
    private int[] arr;
    public ArrBinaryTree(int[] arr){
        this.arr=arr;
    }
    //重载preOrder方法
    public void preOrder(){
        preOrder(0);
    }
    //重载infixOrder方法
    public void infixOrder(){
        infixOrder(0);
    }
    //重载postfixOrder方法
    public void postfixOrder(){
        postfixOrder(0);
    }
    //顺序存储二叉树的前序遍历

    /**
     *
     * @param index 表示数组的下标
     */
    public void preOrder(int index){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，无法遍历");
            return;
        }
        System.out.println(arr[index]);
        //向左子节点递归
        if (2*index+1<arr.length){//防止数组越界
            preOrder(2*index+1);
        }
        //向右子节点递归
        if (2*index+2<arr.length){
            preOrder(2*index+2);
        }
    }
    //中序遍历
    public void infixOrder(int index){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，无法遍历");
            return;
        }
        //向左子节点递归
        if (2*index+1<arr.length){//防止数组越界
            infixOrder(2*index+1);
        }
        System.out.println(arr[index]);
        //向右子节点递归
        if (2*index+2<arr.length){
            infixOrder(2*index+2);
        }
    }
    //后续遍历
    public void postfixOrder(int index){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，无法遍历");
            return;
        }
        //向左子节点递归
        if (2*index+1<arr.length){//防止数组越界
            postfixOrder(2*index+1);
        }

        //向右子节点递归
        if (2*index+2<arr.length){
            postfixOrder(2*index+2);
        }
        System.out.println(arr[index]);
    }
}
