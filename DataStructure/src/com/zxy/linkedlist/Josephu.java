package com.zxy.linkedlist;
//约瑟夫问题
public class Josephu {

    public static void main(String[] args) {
        CircleLinkedList list = new CircleLinkedList();
        list.addBoy(25);
        list.showBoy();
        list.countBoy(3,4,25);
    }
}
    class CircleLinkedList{
        private Boy first=null;
        public void addBoy(int n) {

            if (n < 1) {
                System.out.println("无法构成环形链表");
                return;
            }
            Boy curBoy = null;//构造辅助指针curBoy并初始化
            for (int i = 1; i <= n; i++) {
                Boy boy = new Boy(i);
                if (i == 1) {
                    first = boy;//first指针指向第一个小孩节点
                    first.setNext(boy);//first指向自己构成环
                    curBoy = first;//将辅助指针指向第一个小孩节点
                }
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;//辅助指针后移
            }
        }

        //遍历
        public void showBoy(){
            
            Boy curBoy=null;
            if(first==null){
                System.out.println("该链表为空链表");
                return;
            }
                curBoy=first;
            while (true){
                System.out.println("小孩编号为："+curBoy.getNo());
                if(curBoy.getNext()==first){
                    System.out.println("遍历完毕");
                    break;
                }
                curBoy=curBoy.getNext();//辅助指针后移
            }

        }

        /**
         *
         * @param startNo 从第几个人开始报数
         * @param countNum 报几个数
         * @param nums 总共多少个人
         */
        public void countBoy(int startNo,int countNum,int nums){
            if(first==null||startNo<1||countNum>nums){
                System.out.println("输入参数有误");
                return;
            }

            Boy helper=first;//辅助指针helper先指向first
            while(true){
                if(helper.getNext()==first){//将helper移到环形列表的最后一个节点
                    break;
                }
                helper=helper.getNext();
            }
            for(int i=0;i<startNo-1;i++){
                first=first.getNext();
                helper=helper.getNext();//将first和helper指针都移动startNo-1次，使得first指向报数的小孩节点，helper指向报数小孩的前一个小孩节点
            }
               // System.out.println("第一个报数的小孩节点为"+first.getNo());
            while(true) {
                if(first==helper){//说明圈中只有一个节点
                    System.out.println("圈中还剩一个小孩，编号为："+first.getNo());
                    break;
                }
                for (int i = 0; i < countNum - 1; i++) {
                    first = first.getNext();
                    helper = helper.getNext();//first和helper都移动countNum-1次，使得first指向要出圈的小孩节点，helper指向要出圈的小孩的前一个小孩节点
                }
                    System.out.println("出圈的小孩编号："+first.getNo());
                //出圈
                first = first.getNext();
                helper.setNext(first);
            }

        }
    }

    class Boy{
        private int no;
        private Boy next;

        public Boy(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
    }


