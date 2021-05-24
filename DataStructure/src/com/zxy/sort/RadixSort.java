package com.zxy.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
//基数排序
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr={53,3,542,748,14,214};
//        radixSort(arr);
//        System.out.println("排序后数组为："+ Arrays.toString(arr));
        //用空间换时间
//当数过大时，需要的空间十分大，可能导致java空间不足，报错
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);   //arr[i]的范围为[0,80000)
        }
        //输出排序的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateSt1 = simpleDateFormat.format(date1);
        System.out.println("排序开始的时间为：" + dateSt1);


        radixSort(arr);

        Date date2 = new Date();
        String dateSt2 = simpleDateFormat.format(date2);
        System.out.println("排序结束的时间为：" + dateSt2);
        //System.out.println("排序后数组为："+ Arrays.toString(arr));
    }
    public static void radixSort(int[] arr){
        //找出arr数组中的最大值
        int max=arr[0]; //先假定数组的第一个元素为最大值
        for(int i=1;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        //计算最大值的位数，即循环的次数
        int maxLength=(max+"").length();//用一个巧妙的方法，加一个空串，使之变成一个字符串

        int[][] bucket=new int[10][arr.length];//定义一个二维数组表示桶，二维数组中的每一行表示一个桶，需要10个桶
        //相当于每个桶用一维数组表示，数组大小为arr.length
        int[] bucketElementCounts=new int[10];//定义一个一维数组，它的每个元素表示每个桶中元素的数量
        for(double i=0;i<maxLength;i++) {
            //将arr中元素循环放入桶中
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] /(int)(Math.pow(10,i))% 10;//取出每个元素对应的位所对应的值，第一次是个位，第二次是十位...
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];//将数组元素放入对应的桶中对应的位置
                bucketElementCounts[digitOfElement]++;
            }
            //将桶中的元素依次取出放回arr
            int index = 0;//表示数组的起始索引
            for (int k = 0; k < 10; k++) {//循环遍历，用k表示第k个桶
                if (bucketElementCounts[k] != 0) {//如果第k个桶放入了元素
                    for (int m= 0; m < bucketElementCounts[k]; m++) {//将每个桶中的每个元素依次放入arr
                        arr[index] = bucket[k][m];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;//清空bucketElementCounts数组（表示每个桶中元素的数量）
            }
            //不用清空所有桶中的元素,因为下次放入时会将原数据覆盖，且根据bucketElementCounts数组来判断取每个桶中的元素的次数
        }

    }
}
