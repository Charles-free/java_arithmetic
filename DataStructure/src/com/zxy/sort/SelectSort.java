package com.zxy.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
//选择排序
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr={3,5,1,-2,8,-5};
//        selectSort(arr);
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);   //arr[i]的范围为[0,80000)
        }
        //输出排序的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateSt1 = simpleDateFormat.format(date1);
        System.out.println("排序开始的时间为：" + dateSt1);

        selectSort(arr);

        Date date2 = new Date();
        String dateSt2 = simpleDateFormat.format(date2);
        System.out.println("排序结束的时间为：" + dateSt2);

    }
    public static void selectSort(int[] arr) {
        int min=0;
        int minIndex=0;
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];//定义数组最小值，先假定为数组第一个元素,每完成一次排序，就后移一位，总共需要arr.length-1次排序
            minIndex=i;//定义最小值索引
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < min) {//遍历数组，如果数组元素比最小值小
                    min = arr[j];//赋值给临时变量min
                    minIndex = j;//将最小值的索引赋值给minIndex
                }
            }//循环结束,交换，使得数组的第一个元素为最小值
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
        //System.out.println("排序后的数组为："+ Arrays.toString(arr));//循环结束，输出排序好的数组
    }
}
