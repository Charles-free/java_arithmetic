package com.zxy.divide;
//用分治法解决汉诺塔问题
public class Hanoitower {
    public static void main(String[] args) {
        hanoitower(5,'A','B','C');
    }

    /**
     * 递归方法
     * @param nums 总共有多少个盘
     * @param a a塔 初始塔
     * @param b b塔
     * @param c c塔 目标塔
     */
    public static void hanoitower(int nums,char a,char b,char c){
        if (nums==1){//如果只有一个盘
            System.out.println("第1个盘从"+a+"->"+c);//直接从a塔移动到c塔
        }else{
            hanoitower(nums-1,a,c,b);//将除第一个盘以外的所有盘先从a塔移动到b塔（期间借助c塔）
            System.out.println("第"+nums+"个盘从"+a+"->"+c);//把最下面那个盘移动到c
            hanoitower(nums-1,b,a,c);//将b塔上的盘移动到c塔（期间借助a塔）
        }
    }
}
