package com.zxy.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr={8,4,5,7,1,3,6,2};
//        int[] temp=new int[arr.length];
//        mergeSort(0,arr.length-1,arr,temp);
//        System.out.println("归并之后的数组为"+Arrays.toString(arr));

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);   //arr[i]的范围为[0,8000000)
        }
        int[] temp=new int[arr.length];
        //输出排序的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateSt1 = simpleDateFormat.format(date1);
        System.out.println("排序开始的时间为：" + dateSt1);


        mergeSort(0,arr.length-1,arr,temp);

        Date date2 = new Date();
        String dateSt2 = simpleDateFormat.format(date2);
        System.out.println("排序结束的时间为：" + dateSt2);
    }
    public static void mergeSort(int left,int right,int[] arr,int[] temp){
        if (left<right){
            int mid=(left+right)/2;   //定义分组时左边数组的最右边的索引
            mergeSort(left,mid,arr,temp);  //向左递归进行分解
            mergeSort(mid+1,right,arr,temp);   //向右递归进行分解
            //合并
            merge(left,mid,right,arr,temp);
        }

    }
   //合并的方法
    /**
     * @param left 左边初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param arr 排序的原始数组
     * @param temp 临时数组
     */
    public static void merge(int left, int mid, int right, int[] arr, int[] temp) {
        //System.out.println("xxxx");
        int i = left;//初始化i，左边有序数列的初始索引
        int j = mid + 1;//初始化j，右边有序序列的初始索引
        int t = 0;//临时数组的索引
        while (i <= mid && j <= right) {  //循环，直到左右两边的有序数列有一边循环完为止
            if (arr[i] < arr[j]) {//左边有序数列的当前元素小于右边有序数列的当前元素
                temp[t] = arr[i];//将小的元素拷贝到temp数组
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        while (i <= mid) {//如果左边还有剩余元素
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {//如果右边还有剩余元素
            temp[t] = arr[j];
            t++;
            j++;
        }
     //将temp中的元素拷贝到arr
        //不是每次都拷贝所有元素
        t=0;
        int tempLeft=left;
        //System.out.println("tempLeft="+tempLeft+"   right="+right);
        while(tempLeft<=right){
            arr[tempLeft]=temp[t];
            tempLeft++;
            t++;
        }
    }

}
