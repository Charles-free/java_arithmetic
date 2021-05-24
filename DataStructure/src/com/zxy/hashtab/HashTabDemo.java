package com.zxy.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab tab=new HashTab(7);
        //写一个简单的菜单
        String key="";
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("delete:删除雇员");
            System.out.println("exit:退出系统");
            key=scanner.next();
            switch(key){
                case "add":
                    System.out.println("输入id");
                    int id=scanner.nextInt();
                    System.out.println("输入雇员姓名");
                    String name=scanner.next();
                    Emp emp = new Emp(id, name);
                    tab.add(emp);
                    break;
                case "list":
                    tab.list();
                    break;
                case "find":
                    System.out.println("输入id");
                    id=scanner.nextInt();
                    tab.findById(id);
                    break;
                case "delete":
                    System.out.println("输入id");
                    id=scanner.nextInt();
                    tab.deleteById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}
//创建HashTab，管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;//链表型的数组
    private int size;//哈希表中数组的大小，即存放链表的条数
    //构造器
    public HashTab(int size){
        this.size=size;
        //初始化empLinkedListArray
        empLinkedListArray=new EmpLinkedList[size];
        //!!!不要忘了分别初始化每条链表
        for (int i=0;i<size;i++){
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }
    public void add(Emp emp){
       int empLinkedListNo=fun(emp.id);
       empLinkedListArray[empLinkedListNo].add(emp);
    }
    public void list(){
        for (int i=0;i<size;i++){
            empLinkedListArray[i].list(i);
        }
    }
    public void findById(int id){
        int empLinkedListNo=fun(id);
        Emp emp=empLinkedListArray[empLinkedListNo].findById(id);
        if (emp==null){
            System.out.println("没有找到该id");
            return;
        }
        System.out.println("该雇员在第"+(fun(id)+1)+"条链表,"+"姓名为："+emp.name);
    }
    public void deleteById(int id){
        int empLinkedListNo=fun(id);
        empLinkedListArray[empLinkedListNo].deleteById(id);
    }
    //构造散列函数
    public int fun(int id){
        return id%size;
    }
}
//创建链表(不带表头)
class EmpLinkedList{
    Emp head;//指向链表的第一个节点，默认为null
    //添加
    public void add(Emp emp){
        if(head==null){//链表中没有节点
            head=emp;
            return;
        }
        //如果链表不为空
        Emp curEmp;//定义辅助指针
        curEmp=head;
        while(true) {
            if (curEmp.next == null) {
                curEmp.next = emp;
                break;
            }curEmp=curEmp.next;//指针后移
        }
    }
    //显示列表
    public void list(int no){
            if (head==null){
                System.out.println("第"+(no+1)+"条链表为空");
                return;
            }
            Emp curEmp;//定义辅助指针
            curEmp=head;
        System.out.print("第"+(no+1)+"条链表信息为");
            while (curEmp!=null){
                System.out.print("id="+curEmp.id+",name="+curEmp.name+"    ");
                curEmp=curEmp.next;
            }
        System.out.println();
    }
    //根据id查找
    public Emp findById(int id){
        if (head==null){//链表为空
            return null;
        }
        Emp curEmp;//定义辅助指针
        curEmp=head;
        while (curEmp!=null){
            if (curEmp.id==id){//找到该雇员
                return curEmp;
            }
            curEmp=curEmp.next;
        }
        //循环结束，说明没有找到该id
        System.out.println("找不到该id");
        return null;
    }
    //删除雇员
    public void deleteById(int id){
        if (head==null){//链表为空
            System.out.println("第"+(id+1)+"条链表为空");
            return;
        }
        if (head.next==null||head.id==id){//链表的第一个元素正好是要找的那个，两种情况，链表只有一个元素，链表不只一个元素
            head=head.next;
            System.out.println("已删除该id为"+id+"的雇员");
            return;
        }
        Emp curEmp;//定义辅助指针
        curEmp=head;
        //链表有2个以上的元素，从第二个元素开始遍历
        while(curEmp.next!=null){
            if (curEmp.next.id==id){//找到该雇员的前一个节点
               curEmp.next=curEmp.next.next;
               System.out.println("已删除该id为"+id+"的雇员");
               return;
           }
            curEmp=curEmp.next;
        }
        //如果没有找到该id
        System.out.println("该id为"+id+"的雇员不存在");
    }
}
//表示一个雇员
class Emp{
    int id;
    String name;
    Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
