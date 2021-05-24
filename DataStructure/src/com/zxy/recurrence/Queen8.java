package com.zxy.recurrence;
//八皇后问题
public class Queen8 {
    int count=0;//计算输出的方法次数
    int max=8;
    int[] arr=new int[max];//公共变量
    public static void main(String[] args) {
        Queen8 queen = new Queen8();
        queen.check(0);
        System.out.println("总共的摆法为："+ queen.count);
    }
    //编写一个方法放置第n个皇后
    //check是每一次递归时，进入到check都有for循环
    public void check(int n){
        if(n==max){//放置第8个皇后，说明皇后已经放完
            print();
            return;
        }
        //依次放入皇后并判断是否冲突
        for (int i=0;i<max;i++){
                arr[n]=i;       //先将皇后放在第i个位置
            if (judge(n)){   //判断是否冲突
                check(n+1);  //如果不冲突则回溯，放第n+1个皇后
                //直到将8个皇后放完，然后打印返回，从第八层开始回溯，输出所有情况
            }
            //如果冲突则继续循环
        }
    }
    //当放置第n个皇后后是否和前面的皇后产生冲突
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {//如果第n个皇后和前面的第i个皇后在同一列或同一条斜线，则冲突
                return false;
            }
        }
        return true;
    }
    public void print(){
        count++;
        for (int i=0;i<8;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
