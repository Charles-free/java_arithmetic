package com.zxy.sort;

import java.text.SimpleDateFormat;
import java.util.Date;
//冒泡排序
public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr = {3, 9, -1, 10, 20};

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);   //arr[i]的范围为[0,80000)
        }
        //输出排序的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateSt1 = simpleDateFormat.format(date1);
        System.out.println("排序开始的时间为：" + dateSt1);
        BubbleSort bubbleSort = new BubbleSort();

        bubbleSort.bubbleSort(arr);

        Date date2 = new Date();
        String dateSt2 = simpleDateFormat.format(date2);
        System.out.println("排序结束的时间为：" + dateSt2);


    }

    public void bubbleSort(int[] arr) {
        //第一趟排序就是将最大的数排在最后
        int temp = 0;//临时变量
        boolean flag = false;//标识，表示数组元素是否进行过交换
        //时间复杂度为O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {//每次循环排序的次数都会减少1，所以用个大for循环包裹起来
            for (int j = 0; j < arr.length - 1 - i; j++) {//总共进行arr.length-1次循环
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag) {//如果进行交换则重置flag为false，继续循环
                flag = false;
            } else {
                break;//如果没进行交换则退出循环
            }
            // System.out.printf("第%d次排序后的数组为:%s\n", i + 1, Arrays.toString(arr));
        }
    }
}

