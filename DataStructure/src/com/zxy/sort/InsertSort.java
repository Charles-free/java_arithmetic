package com.zxy.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//插入排序
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr={3,1,6,0,7,-5};
//        insertSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);   //arr[i]的范围为[0,80000)
        }
        //输出排序的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateSt1 = simpleDateFormat.format(date1);
        System.out.println("排序开始的时间为：" + dateSt1);


        insertSort(arr);

        Date date2 = new Date();
        String dateSt2 = simpleDateFormat.format(date2);
        System.out.println("排序结束的时间为：" + dateSt2);

    }
    public static void insertSort(int[] arr) {
        int insertVal=0;
        int insertIndex=0;
        for (int i = 1; i < arr.length; i++) {//外层循环，因为要插入arr.length-1个数，所以循环arr.length-1次
            insertVal = arr[i];//待插入的数，从数组的第二个元素开始
            insertIndex = i - 1;//最先表示插入数的前面那个数的下标，依次向前遍历
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//将下标为insertIndex的这个数往后移
                insertIndex--;//循环，从待插数的前面的那个数开始一直到数组的第一个元素
            }
            arr[insertIndex + 1] = insertVal;//将待插数赋值给下标insertIndex后面的那个数，因为它后面的数都已经后移一位，所以不用担心数据覆盖
        }
        //插入完毕，输出排序好的数组
        //System.out.println("排序之后的数组为："+ Arrays.toString(arr));
    }
}
