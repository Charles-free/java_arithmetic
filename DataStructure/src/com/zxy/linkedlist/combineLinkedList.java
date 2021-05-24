package com.zxy.linkedlist;

public class combineLinkedList {


    static class Node{
        public int data;
        public Node next;
        public Node(){}

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
    public static Node combine(Node head1,Node head2){
        Node temp1=head1.next;
        Node temp2=head2.next;
        Node head3=new Node();
        Node temp3=head3;


        while (true){
            if (temp1==null){
                temp3.next=temp2;
                break;
            }
            if (temp2==null){
                temp3.next=temp1;
                break;
            }
            if (temp1.data>temp2.data){
                temp3.next=temp2;
                temp2=temp2.next;
                temp3=temp3.next;
            }
            if (temp1.data<=temp2.data){
                temp3.next=temp1;
                temp1=temp1.next;
                temp3=temp3.next;
            }
        }
        return head3;

    }

    public static void print(Node head){
        Node temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }


    public static void main(String[] args) {
        Node head1=new Node();
        Node head2=new Node();
        Node node1=new Node(2);
        Node node2=new Node(5);
        Node node3=new Node(7);
        head1.next=node1;
        node1.next=node2;
        node2.next=node3;
        System.out.println("第一个有序链表");
        print(head1);

        Node node4=new Node(1);
        Node node5=new Node(3);
        Node node6=new Node(6);
        Node node7=new Node(8);
        head2.next=node4;
        node4.next=node5;
        node5.next=node6;
        node6.next=node7;
        System.out.println("第二个有序链表");
        print(head2);
        System.out.println("合并之后");
        print(combine(head1,head2));

    }
}
