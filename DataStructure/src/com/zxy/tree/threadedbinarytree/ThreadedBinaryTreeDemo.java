package com.zxy.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        //二叉树后面用递归创建，现在简单手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试线索化
        //System.out.println("中序线索二叉树");
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRootNode(root);
        //threadedBinaryTree.infixThreadedNodes();
        System.out.println("前序线索二叉树");
        threadedBinaryTree.preThreadedNodes();
        threadedBinaryTree.preThreadedList();
        //System.out.println("后序线索二叉树");
        //threadedBinaryTree.postfixThreadedNodes();
        //测试，以10号节点做测试
//        HeroNode leftNode = node5.getLeft();
//        System.out.println("10号节点的前驱节点为"+leftNode);
//        HeroNode rightNode = node5.getRight();
//        System.out.println("10号节点的后继节点为"+rightNode);

    }
}
//创建ThreadedBinaryTree实现线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;//定义根节点,默认为null
    private HeroNode pre=null;//创建一个指向当前节点的前驱节点的指针pre，在递归进行线索化时，pre总是保留前一个节点

    public void setRootNode(HeroNode root) {//定义设置根节点的方法
        this.root = root;
    }

    public void preThreadedNodes(){//重载preThreadedNodes()方法
        preThreadedNodes(root);
    }
    public void infixThreadedNodes(){//重载infixThreadedNodes()方法
        infixThreadedNodes(root);
    }

    public void postfixThreadedNodes(){//重载postfixThreadedNodes()方法
        postfixThreadedNodes(root);
    }

    //对二叉树进行前序线索化的方法

    public void preThreadedNodes(HeroNode node){
        if (node==null){//不能线索化
            return;
        }

        //1.线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft()==null){
            node.setLeft(pre);//让当前节点的左指针指向前驱节点
            node.setLeftType(1);//修改当前节点的左指针类型
        }
        //处理后继节点，在递归的下次处理
        if (pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后让当前节点是下一个节点的前驱节点
        pre=node;
        //2.线索化左子树
        if(node.getLeftType()==0) {//如果该节点没有线索化就继续递归，否则进行下一步   ！没有这个判断会导致死循环
            preThreadedNodes(node.getLeft());
        }
        //3.线索化右子树
        if (node.getRightType()==0) {
            preThreadedNodes(node.getRight());
        }
    }
    //对二叉树进行中序线索化的方法

    /**
     *
     * @param node 就是当前需要线索化的节点
     */
    public void infixThreadedNodes(HeroNode node){
        if (node==null){//不能线索化
            return;
        }
        //1.先线索化左子树
        infixThreadedNodes(node.getLeft());
        //2.线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft()==null){
            node.setLeft(pre);//让当前节点的左指针指向前驱节点
            node.setLeftType(1);//修改当前节点的左指针类型
        }
        //处理后继节点，在递归的下次处理
        if (pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后让当前节点是下一个节点的前驱节点
        pre=node;

        //3.线索化右子树
        infixThreadedNodes(node.getRight());
    }


    //对二叉树进行后序线索化的方法

    public void postfixThreadedNodes(HeroNode node){
        if (node==null){//不能线索化
            return;
        }

        //1.线索化左子树
        postfixThreadedNodes(node.getLeft());
        //2.线索化右子树
        postfixThreadedNodes(node.getRight());
        //3.线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft()==null){
            node.setLeft(pre);//让当前节点的左指针指向前驱节点
            node.setLeftType(1);//修改当前节点的左指针类型
        }
        //处理后继节点，在递归的下次处理
        if (pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后让当前节点是下一个节点的前驱节点
        pre=node;
    }
    //线索化遍历前序二叉树方法
    public void preThreadedList(){
        HeroNode node=root;
        if (node==null){
            System.out.println("二叉树为空无法遍历");
            return;
        }
        while(node!=null) {//如果node为null，说明已经遍历完毕
            //先输出第一个节点为1
            while(node.getLeftType() == 0) {
            System.out.println(node);
                node=node.getLeft();
            }
                System.out.println(node);//输出
                node=node.getRight();
        }
    }

    //线索化遍历中序二叉树方法
    public void infixThreadedList(){
        HeroNode node=root;
        if (node==null){
            System.out.println("二叉树为空无法遍历");
            return;
        }
        while(node!=null) {//如果node为null，说明已经遍历完毕
            //循环找到node.getLeftType() == 1的节点，第一个节点为8
            while(node.getLeftType() == 0) {
                node=node.getLeft();
            }
            System.out.println(node);//输出当前节点
            while(node.getRightType()==1){//循环遍历，如果当前节点的right指向的是后继节点
                node=node.getRight();
                System.out.println(node);//输出
            }
            node=node.getRight();//如果right不是指向后继节点，即指向右子树
        }
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
    private int leftType;//左节点的类型  0表示指向左子树，1表示前驱节点
    private int rightType;//右节点的类型  0表示指向右子树，1表示后继节点

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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