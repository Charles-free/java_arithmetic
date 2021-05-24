package com.zxy.linkedlist;

public class DoubleLinkedList {
    private static Node2 head=new Node2(0,"","");//初始化头节点
    static class Node2{
        public int no;
        public String name;
        public String nickname;
        public Node2 next;
        public Node2 pre;
        public Node2(){}
        public Node2(int no, String name, String nickname) {
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
    public static void addByOrder(Node2 node){
        Node2 temp=head;
        while(true){
            if(temp.next==null){
                temp.next=node;
                node.pre=temp;
                break;
            }
            if(temp.next.no>node.no){

                node.pre=temp;
                node.next=temp.next;
                temp.next.pre=node;
                temp.next=node;
                break;
            }
            if(temp.next.no==node.no){
                System.out.println("编号已经存在，不能添加");
                break;
            }temp=temp.next;
        }


    }

    public static void list(){
        if(head==null){
            System.out.println("链表为空");
            return;
        }
        Node2 temp=head.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }

    public static void main(String[] args) {
        Node2 node1=new Node2(1,"张三丰","无极剑圣");
        Node2 node2=new Node2(5,"段誉","六脉神剑");
        Node2 node3=new Node2(3,"小龙龙","神雕侠侣");
        Node2 node4=new Node2(2,"洪七公","丐帮");

        addByOrder(node1);
        addByOrder(node2);
        addByOrder(node3);
        addByOrder(node4);
        list();
    }
}
