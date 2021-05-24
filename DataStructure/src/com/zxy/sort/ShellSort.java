package com.zxy.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//希尔排序
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr={8,9,1,7,2,3,5,4,6,0};
//        shellSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);   //arr[i]的范围为[0,80000)
        }
        //输出排序的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateSt1 = simpleDateFormat.format(date1);
        System.out.println("排序开始的时间为：" + dateSt1);

        shellSort2(arr);//移位法

        Date date2 = new Date();
        String dateSt2 = simpleDateFormat.format(date2);
        System.out.println("排序结束的时间为：" + dateSt2);
    }
    //对有序数组进行交换法
    public static void shellSort(int[] arr){
        int temp=0;
        int count=0;
        for (int gap=arr.length/2;gap>=1;gap/=2){  //将数组进行分组，每次分组的组数为gap
        for (int i=gap;i<arr.length;i++){    //利用循环使得数组元素依次向后遍历
            for (int j=i-gap;j>=0;j-=gap){  // 对每组的所有元素进行遍历，步长为gap，循环交换
                if(arr[j]>arr[j+gap]){
                    temp=arr[j];
                    arr[j]=arr[j+gap];
                    arr[j+gap]=temp;

                }
            }
        }
            System.out.println("第"+(count++)+"次排序后的数组为："+ Arrays.toString(arr));
    }
    }
    //对有序数组进行移位法
    public static void shellSort2(int[] arr){
        int temp=0;//定义临时变量，存储待插入的数
        int count=0;
        int j=0;//用来遍历的下标
        for (int gap=arr.length/2;gap>=1;gap/=2){            //将数组进行分组，每次分组的组数为gap
            for (int i=gap;i<arr.length;i++){
                j=i;
                temp=arr[j];
                if (arr[j]<arr[j-gap]){
                    while(j-gap>=0&&temp<arr[j-gap]){
                        //移动
                        arr[j]=arr[j-gap];
                        j=j-gap;
                    }//退出while循环，给temp找到合适的插入位置
                    arr[j]=temp;
                }
            }
            //System.out.println("第"+(count++)+"次排序后的数组为："+ Arrays.toString(arr));
            }
    }
}
