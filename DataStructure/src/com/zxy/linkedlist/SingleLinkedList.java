package com.zxy.linkedlist;

import java.util.Stack;

public class SingleLinkedList {
    private Node head=new Node(0,"","");//初始化头节点

//    public Node getHead() {
//        return head;
//    }
//
//    public void setHead(Node head) {
//        this.head = head;
//    }

    static class Node{
        public int no;
        public String name;
        public String nickname;
        public Node next;
        public Node(){}
        public Node(int no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }
    }
    public void delete(int no){
        Node temp=head;
        while(true){
            if(temp.next.no==no){
                temp.next=temp.next.next;
                break;
            }
            if(temp.next==null){
                System.out.println("没有找到该节点");
                break;
            }
            temp=temp.next;
        }

    }
    public void addByOrder(Node node){
        Node temp=head;
        boolean flag=false;//标识添加的符号是否存在
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no>node.no){
                break;
            }
            if(temp.next.no==node.no){
               flag=true;
               break;
            }temp=temp.next;
        }
        if(flag){
            System.out.println("编号已经存在，不能添加");
        }else {
            node.next=temp.next;
            temp.next=node;
        }
    }
    public void list(){
        if(head==null){
            System.out.println("链表为空");
            return;
        }
        Node temp=head.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }
//链表反转
    public void reversal(){
        if(head.next==null||head.next.next==null){//如果没有节点或只有一个节点，那么不需要反转
            System.out.println("链表为空");
        }
        Node head2=new Node();
        Node temp1=head.next;
        Node temp=null;//定义一个零时节点保存当时节点的下一个节点
        while (temp1!=null){

                temp=temp1.next;//暂时保存temp1的下一个节点
                temp1.next=head2.next;//将temp1的next指向新链表的最前端
                head2.next=temp1;//将新链表的头节点指向temp1
                temp1=temp;//后移

        }
        head.next=head2.next;
        System.out.println("链表反转成功");
    }
//链表从尾到头打印
    public void reversePrint(){
        if(head.next==null){
            System.out.println("这是空链表");
        }
        Node temp=head.next;
        Stack<Node> stack = new Stack<Node>();
        while (true){
            if(temp==null) {
                break;
            }
            stack.add(temp);
            temp=temp.next;
            }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
    public static void main(String[] args) {
        Node node1=new Node(1,"张三丰","无极剑圣");
        Node node2=new Node(2,"小龙龙","神雕侠侣");
        Node node3=new Node(3,"段誉","六脉神剑");

        SingleLinkedList list=new SingleLinkedList();
        list.addByOrder(node1);
        list.addByOrder(node3);
        list.addByOrder(node2);
        list.list();
//        list.reversal();
//        list.list();
        System.out.println("利用stack反转打印链表");
        list.reversePrint();
    }
}
