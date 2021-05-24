package com.zxy.stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack list = new LinkedListStack(6);
        list.push(5);
        list.push(3);
        list.push(6);
        list.push(9);
        list.push(2);
        list.push(0);
        list.push(8);
        list.showStack();
        list.pop();
        list.pop();
        list.pop();
        list.pop();
        list.pop();
        list.pop();
        list.pop();
        System.out.println("出栈后");
        list.showStack();
    }
}
    class LinkedListStack{
        private Node head=new Node(0);
        private int maxsize;
        public LinkedListStack(int maxsize){
            this.maxsize=maxsize;
        }
        //入栈
        public void push(int value){
            if (isFull()){
                System.out.println("栈满了");
                return;
            }
            Node node=new Node(value);
            node.next=head.next;
            head.next=node;


        }
        //出栈
        public int pop(){
            Node temp=null;
            if (isEmpty()){
                throw new RuntimeException("栈为空");
            }
            temp=head.next;
            int value=temp.data;
            head.next=temp.next;
            return value;
        }
        //显示栈
        public void showStack(){
            Node temp=head.next;
            while (true){
                if (temp==null){
                    break;
                }
                System.out.println(temp.data);
                temp=temp.next;
            }
        }
        //获取栈的长度
        public int getStackLength(){
            int size=0;
            Node temp=head;
            while(true){
                if (temp.next==null){
                    break;
                }
                size++;
                temp=temp.next;
            }
            return size;
        }
        //判断栈是否满
        public boolean isFull(){
            return getStackLength()==maxsize;
        }
        //判断栈是否为空
        public boolean isEmpty(){
            return head.next==null;
        }
    }
    class Node{
        public int data;
        public Node next;
        public Node(int data){
            this.data=data;
        }
    }

