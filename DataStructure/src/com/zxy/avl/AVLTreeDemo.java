package com.zxy.avl;
//平衡二叉树
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr={4,3,6,5,7,8};
        //int[] arr = {10,12,8,9,7,6};
        int[] arr={10,11,7,6,8,9};
        AVLTree tree = new AVLTree();
        for (int i=0;i<arr.length;i++){
            tree.add(new Node(arr[i]));
        }
        tree.infixOrder();
        System.out.println("该树的高度为"+tree.getRoot().height());
        System.out.println("该树左子树的高度为"+tree.getRoot().left.height());
        System.out.println("该树右子树的高度为"+tree.getRoot().right.height());
//        System.out.println("根节点为"+tree.getRoot());
//        System.out.println(tree.getRoot().left);
//        System.out.println(tree.getRoot().left.left);
//        System.out.println(tree.getRoot().right);
//        System.out.println(tree.getRoot().right.left);
//        System.out.println(tree.getRoot().right.right);
    }
}
class AVLTree{
    private Node root;//定义根节点，默认为null
    public Node getRoot(){//得到根节点
        return root;
    }
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

}
class Node {
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
     *
     * @param node 需要添加的节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //用递归添加
        if (node.value < this.value) {
            //向左递归
            if (this.left != null) {
                this.left.add(node);
            } else {//直到该节点为叶子节点
                this.left = node;
            }
        } else {
            //向右递归
            if (this.right != null) {
                this.right.add(node);
            } else {
                this.right = node;
            }
        }

        //当添加完一个节点之后，判断，左子树和右子树是否相差超过1
        //1、右子树比左子树高
        if ((rightHeight()-leftHeight())>1){
            //如果它的右子树的左子树比它的右子树的右子树高
            if (right.leftHeight()>right.rightHeight()){
                //先对当前节点的右子节点进行右旋转
                right.rightRotate();
                //再对当前节点进行左旋转
                leftRotate();
            }else {
                //直接左旋
                leftRotate();
            }
            return;
        }
        //2、左子树比右子树高
        if ((leftHeight()-rightHeight())>1){
            //如果它的左子树的右子树比它的左子树的左子树高
            if (left.rightHeight()>left.leftHeight()){
                //先对当前节点的左子节点进行左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            }else {
                //直接右旋
                rightRotate();
            }
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
    //返回左子树的高度
    public int leftHeight(){
        if (left==null){
            return 0;
        }
        return left.height();
    }
    //返回右子树的高度
    public int rightHeight(){
        if (right==null){
            return 0;
        }
        return right.height();
    }
    //返回以该节点为树节点的树的高度
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;//1为自己的这一层
    }
    //编写进行左旋转的方法
    public void leftRotate(){
        //创建一个新的节点，值为当前根节点的值
        Node newNode=new Node(value);
        //把当前节点的左子树设置为新节点的左子树
            newNode.left = left;
        //把当前节点的右子树的左子树设置为新节点的右子树
        newNode.right=right.left;
        //把当前节点的值换成右子节点的值
        value=right.value;
        //把当前节点的右子树设置为右子树的右子树
        right=right.right;
        //把当前节点的左子树设置为新节点
        left=newNode;
    }
    //编写进行右旋的方法
    public void rightRotate(){
        //创建一个新的节点，值为当前根节点的值
        Node newNode=new Node(value);
        //把当前节点的右子树设置为新节点的右子树
        newNode.right = right;
        //把当前节点的左子树的右子树设置为新节点的左子树
        newNode.left=left.right;
        //把当前节点的值换成左子节点的值
        value=left.value;
        //把当前节点的左子树设置为左子树的左子树
        left=left.left;
        //把当前节点的右子树设置为新节点
        right=newNode;
    }
}
