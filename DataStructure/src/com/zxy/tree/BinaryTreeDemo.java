package com.zxy.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(2, "吴用");
        HeroNode node2 = new HeroNode(3, "卢俊义");
        HeroNode node3 = new HeroNode(4, "武松");
        HeroNode node4=new HeroNode(5,"李逵");
        BinaryTree tree = new BinaryTree();

        //手动创建二叉树
        tree.setRootNode(root);
        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node3);
        node2.setLeft(node4);
        //前序遍历
        //System.out.println("前序遍历为");
        //tree.preOrder();
        //中序遍历
        System.out.println("中序遍历为");
        tree.postfixOrder();
        //后续遍历
        //System.out.println("后序遍历为");
        //tree.postfixOrder();

//        tree.preQuery(5);
//        tree.infixQuery(5);
//        tree.postfixQuery(5);
        //tree.postfixQuery(6);
        //删除节点1
//        tree.delNode(3);
//        System.out.println("删除节点后");
//        tree.infixOrder();
        //删除节点2
        tree.delNode2(3);
        System.out.println("删除节点后");
        tree.postfixOrder();

    }
}

//创建树
class BinaryTree {
    private HeroNode root;//定义根节点,默认为null

    public void setRootNode(HeroNode root) {//定义设置根节点的方法
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (root != null) {
            root.preOrder();
            return;
        }
        System.out.println("该二叉树为空，无法遍历");
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
            return;
        }
        System.out.println("该二叉树为空，无法遍历");
    }

    //后续遍历
    public void postfixOrder() {
        if (root != null) {
            root.postfixOrder();
            return;
        }
        System.out.println("该二叉树为空，无法遍历");
    }
    //前序查找
    public void preQuery(int no){
        if(root!=null){
            HeroNode node=root.preQuery(no);
            if(node==null){
                System.out.println("没有找到该节点");
                return;
            }
            //找到该节点
            System.out.println("no为"+no+"，姓名为"+ node.getName());
            return;
        }
        System.out.println("该二叉树为空，无法查找");
    }
    //中序查找
    public void infixQuery(int no){
        if(root!=null){
            HeroNode node=root.infixQuery(no);
            if(node==null){
                System.out.println("没有找到该节点");
                return;
            }
            //找到该节点
            System.out.println("no为"+no+"，姓名为"+ node.getName());
            return;
        }
        System.out.println("该二叉树为空，无法查找");
    }
    //后序查找
    public void postfixQuery(int no){
        if(root!=null){
            HeroNode node=root.postfixQuery(no);
            if(node==null){
                System.out.println("没有找到该节点");
                return;
            }
            //找到该节点
            System.out.println("no为"+no+"，姓名为"+ node.getName());
            return;
        }
        System.out.println("该二叉树为空，无法查找");
    }
    //删除节点
    public void delNode(int no){
        if (root!=null){
            if (root.getNo()==no){//如果根节点为要删除的节点
                root=null;//将整个二叉树置为空
                return;
            }else {
                root.delNode(no);
            }
        }else{
            System.out.println("链表为空，无法进行删除");
        }
    }
    //删除节点2
    public void delNode2(int no){
        if (root!=null){
            if (root.getNo()==no){//如果根节点为要删除的节点
                if (root.getLeft()==null&&root.getRight()==null) {//如果根节点没有子节点
                    root = null;//将整个二叉树置为空
                    return;
                }
                //如果根节点只有一个子节点
                if (root.getLeft()==null) {
                    root=root.getRight();
                    return;
                }
                if (root.getRight()==null) {
                    root=root.getLeft();
                    return;
                }
                //否则，根节点有两个子节点
                HeroNode tempNode=new HeroNode();//临时节点
                tempNode=root.getRight();
                root=root.getLeft();//用左子节点来代替它
                root.setRight(tempNode);
                return;
            }else {
                root.delNode2(no);
            }
        }else{
            System.out.println("链表为空，无法进行删除");
        }
    }

}

//节点类
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//左节点
    private HeroNode right;//右节点

    public HeroNode() {
    }

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

    //后续遍历
    public void postfixOrder() {
        if (this.left != null) {
            this.left.postfixOrder();
        }
        if (this.right != null) {
            this.right.postfixOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public HeroNode preQuery(int no) {
        System.out.println("前序查找进行中");
        if (this.no == no) {//如果当前节点就是要找的节点
            return this;//返回当前节点
        }
        HeroNode resNode=null;//定义一个节点，用于保存递归返回的节点
        if (this.left != null) {//向左递归查找
            resNode=this.left.preQuery(no);
        }
        if (resNode!=null){//说明左子树找到
            return resNode;
        }
        if (this.right != null) { //向右递归查找
            resNode=this.right.preQuery(no);
        }
        return resNode;
    }

    //中序查找
    public HeroNode infixQuery(int no) {
        HeroNode resNode=null;//定义一个节点，用于保存递归返回的节点
        if (this.left != null) {//向左递归查找
            resNode=this.left.infixQuery(no);
        }
        if (resNode!=null){//说明左子树找到
            return resNode;
        }
        System.out.println("中序查找进行中");
        if (this.no == no) {//如果当前节点就是要找的节点
            return this;//返回当前节点
        }
        if (this.right != null) { //向右递归查找
            resNode=this.right.infixQuery(no);
        }
        return resNode;
    }

    //后序查找
    public HeroNode postfixQuery(int no) {
        HeroNode resNode=null;//定义一个节点，用于保存递归返回的节点
        if (this.left != null) {//向左递归查找
            resNode=this.left.postfixQuery(no);
        }
        if (resNode!=null){//说明左子树找到
            return resNode;
        }
        if (this.right != null) { //向右递归查找
            resNode=this.right.postfixQuery(no);
        }
        if (resNode!=null){//说明右子树找到
            return resNode;
        }
        System.out.println("后序查找进行中");
        if (this.no == no) {//如果当前节点就是要找的节点
            return this;//返回当前节点
        }
        return null;
    }
    //删除节点1(删除子节点时连同子树一起删掉)
    public void delNode(int no){
        //如果左子树的下一个节点不为空，且恰好是要删除的那个节点
        if (this.left!=null&&this.left.no==no){
            this.left=null;//置为空
            return;//返回
        }
        //如果右子树的下一个节点不为空，且恰好是要删除的那个节点
        if (this.right!=null&&this.right.no==no){
            this.right=null;//置为空
            return;//返回
        }
        if (this.left!=null){
            //向左递归
            this.left.delNode(no);//不用返回，因为可能没有找到要删除的节点
        }
        if (this.right!=null){
            //向右递归
            this.right.delNode(no);
        }
    }
    //删除节点（删除子节点时，如果它有一个子节点，则用该子节点来替代它，如果它有两个子节点，则用左子节点来代替它）
    public void delNode2(int no){
        if (this.left!=null&&this.left.no==no){
            if(this.left.left==null&&this.left.right==null) {//如果要删除的这个节点没有子节点
                this.left = null;//置为空
                return;//返回
            }
            //如果要删除的这个节点只有一个子节点
            if (this.left.left==null) {
                this.left=this.left.right;
                return;
            }
            if (this.left.right==null) {
                this.left=this.left.left;
                return;
            }
            //否则，要删除的这个节点有两个子节点
            HeroNode tempNode=new HeroNode();//创建一个临时节点来存放右子节点
            tempNode=this.left.right;
            this.left=this.left.left;//用左子节点来代替它
            this.left.right=tempNode;//将原来的右子节点重新赋给新节点

            return;
        }
        if (this.right!=null&&this.right.no==no){
            if(this.right.left==null&&this.right.right==null) {//如果要删除的这个节点没有子节点
                this.right = null;//置为空
                return;//返回
            }
            //如果要删除的这个节点只有一个子节点
            if (this.right.left==null) {
                this.right=this.right.right;
                return;
            }
            if (this.right.right==null) {
                this.right=this.right.left;
                return;
            }
            //否则，要删除的这个节点有两个子节点
            HeroNode tempNode=new HeroNode();//创建一个临时节点来存放右子节点
            tempNode=this.right.right;
            this.right=this.right.left;//用左子节点来代替它
            this.right.right=tempNode;//将原来的右子节点重新赋给新节点
            return;
        }
        if (this.left!=null){
            //向左递归
            this.left.delNode(no);//不用返回，因为可能没有找到要删除的节点
        }
        if (this.right!=null){
            //向右递归
            this.right.delNode(no);
        }

    }
}