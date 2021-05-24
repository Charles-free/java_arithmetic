package com.zxy.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr={-9,7,8,0,2,3,8,7};
//        quickSort(arr,0,arr.length-1);
//        System.out.println("循环之后的数组为："+Arrays.toString(arr));

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);   //arr[i]的范围为[0,8000000)
        }
        //输出排序的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateSt1 = simpleDateFormat.format(date1);
        System.out.println("排序开始的时间为：" + dateSt1);

        quickSort(arr,0,arr.length-1);

        Date date2 = new Date();
        String dateSt2 = simpleDateFormat.format(date2);
        System.out.println("排序结束的时间为：" + dateSt2);
    }
    public static void quickSort(int[] arr,int left,int right){
        int l=left;//左指针
        int r=right;//右指针
        int pivot=arr[(left+right)/2];//pivot中心点，这里我们定义为基准值
        int temp=0;//定义临时变量，用于数据交换
        while (r>l){//循环，左指针小于右指针
            while(arr[l]<pivot){//循环遍历，直到找到比基准值大的元素
                l++;//左指针向右移动
            }
            while (arr[r]>pivot){//循环遍历，直到找到比基准值小的元素
                r--;//右指针向左移动
            }
            if (r<=l){
                break;
            }
            //交换
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            //为避免死循环，如两个一样的数一直交换或者一边是有序，一边是无序
            //如果交换后发现arr[l]==arr[pivot]，则r--，前移
            if (arr[l]==pivot){
                r--;
            }
            //如果交换后发现arr[r]==arr[pivot]，则l++，后移
            if (arr[r]==pivot){
                l++;
            }
        }
        //防止栈溢出
        if(r==l){
            r--;
            l++;
        }
        //开始递归
        if (left<r){
            quickSort(arr,left,r);
        }
        if (right>l){
            quickSort(arr,l,right);
        }
    }
}
