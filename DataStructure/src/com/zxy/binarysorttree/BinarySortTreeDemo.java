package com.zxy.binarysorttree;
//二叉排序树
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr={7,3,10,12,5,1,9};
        BinarySortTree tree = new BinarySortTree();
        for(int i=0;i<arr.length;i++){
            tree.add(new Node(arr[i]));
        }
        tree.infixOrder();
        tree.deleteNode(7);
        System.out.println("删除节点后");
        tree.infixOrder();
    }
}
//创建一颗二叉排序树
class BinarySortTree{
    Node root;//定义根节点，默认为null
    //添加节点
    public void add(Node node){
        if (root==null){
            root=node;
        }else{
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if (root==null){
            System.out.println("该树为空，无法遍历");
        }else {
            root.infixOrder();
        }
    }
    //查找要删除的节点
    public Node searchNode(int value){
        if (root==null){
            System.out.println("二叉树为空，无法查找");
            return null;
        }else {
            return root.searchNode(value);
        }
    }
    //查找要删除的节点的父节点
    public Node searchParent(int value){
        if (root==null) {
            System.out.println("二叉树为空，无法查找");
            return null;
        }else {
            return root.searchParent(value);
        }
    }
    //删除节点
    public void deleteNode(int value){
        Node node=searchNode(value);
        if (node==null){
            return;
        }
        Node parent=searchParent(value);
        if (root.left==null&&node.right==null){//如果这颗树只有一个节点，即root
            root=null;
            return;
        }
        //如果要删除的这个节点为叶子节点
        if (node.left==null&&node.right==null){
            if (parent.left==node){//该节点为左叶子节点
                parent.left=null;
            }else{//该节点为右叶子节点
                parent.right=null;
            }
            return;
        }
        //如果要删除的是只有一颗子树的节点
        if (node.left==null||node.right==null) {
            if (parent==null){//如果parent为空，即要删除的是根节点
                if (node.left!=null){
                    root=node.left;
                }else if (node.right!=null){
                    root=node.right;
                }
                return;
            }
            //parent不为空
            //1、node为parent的左子节点
            if (parent.left == node) {
                //node有左子树
                if (node.left != null && node.right == null) {
                    parent.left = node.left;
                } else {//node有右子树
                    parent.left = node.right;
                }
                return;
            }
            //2、node为parent的右子节点
            else if (parent.right == node) {
                //node有左子树
                if (node.left != null && node.right == null) {
                    parent.right = node.left;
                } else {//node有右子树
                    parent.right = node.right;
                }
                return;
            }
        }
        //如果要删除的是有两颗子树的节点
        if (node.left!=null&&node.right!=null){
            //找到右子树的最小节点的值
            int temp=deleteRightTreeMin(node.right);
            node.value=temp;
            return;
        }
    }
    //定义一个方法，删除右子树最小值节点，并返回这个最小值
    public int deleteRightTreeMin(Node node){
        while(node.left!=null){
            node= node.left;
        }
        int temp=node.value;//定义一个变量存储最小值
        deleteNode(node.value);
        return temp;
    }
}
class Node{
    int value;//权值
    Node left;//左节点
    Node right;//右节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
    /**
     * 添加节点
     * @param node 需要添加的节点
     */
    public void add(Node node){
        if (node==null){
            return;
        }
        //用递归添加
        if (node.value<this.value) {
            //向左递归
            if (this.left != null) {
                this.left.add(node);
            }else{//直到该节点为叶子节点
                this.left=node;
            }
        }else{
            //向右递归
            if (this.right!=null){
                this.right.add(node);
            }else{
                this.right=node;
            }
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    /**
     * 找到要删除的这个节点
     * @param value 要删除的这个节点的值
     * @return 要删除的节点
     */
    public Node searchNode(int value){
        if (this.value==value){
            return this;
        }else{
            if (value<this.value&&this.left!=null){
                //向左递归查找
                return this.left.searchNode(value);
            }else if (value>this.value&&this.right!=null){
                //向右递归查找
                return this.right.searchNode(value);
            }else{
                return null;
            }
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value 要删除的这个节点的权值
     * @return 父节点
     */
    public Node searchParent(int value){
        //如果该节点的左子节点或者右子节点是要删除的这个节点，返回该节点（即父节点）
        if ((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }else{
            if (this.left!=null&&(value<this.value)){
                //向左递归查找
                return this.left.searchParent(value);
            }else if (this.right!=null&&(value>this.value)){
                //向右递归查找
                return this.right.searchParent(value);
            }else {
                //分两种情况
                //1、要查找的节点为root（没有父节点）
                //2、要查找的节点不存在（自然没有父节点）
                return null;
            }
        }
    }
}
