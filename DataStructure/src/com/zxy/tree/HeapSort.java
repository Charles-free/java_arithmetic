package com.zxy.tree;

import com.zxy.sort.BubbleSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//堆排序
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr={4,6,8,5,9,4,7,0,3};
//        heapSort(arr);
//        System.out.println("排序后的数组为"+Arrays.toString(arr));
        int[] arr = new int[80000000];//八千万个数，堆排序只要31秒
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 80000000);   //arr[i]的范围为[0,80000)
        }
        //输出排序的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateSt1 = simpleDateFormat.format(date1);
        System.out.println("排序开始的时间为：" + dateSt1);

        heapSort(arr);

        Date date2 = new Date();
        String dateSt2 = simpleDateFormat.format(date2);
        System.out.println("排序结束的时间为：" + dateSt2);
    }
    //写一个方法进行堆排序
    public static void heapSort(int[] arr){
//        System.out.println("堆排序");
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+ Arrays.toString(arr));
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次"+ Arrays.toString(arr));
        //将无序数列构造成一个堆，根据需求选择大顶堆或小顶堆
        for (int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        for(int j=arr.length-1;j>0;j--){
            int temp=arr[0];//将大顶堆的顶（即最大值）赋给临时变量
            //交换
            arr[0]=arr[j];
            arr[j]=temp;
            adjustHeap(arr,0,j);//最后一个元素离开数组，重新对剩下的元素排序，排成一个大顶堆
        }
    }

    /**
     * 功能 完成对以i对应的非叶子节点的树调整成大顶堆
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整，length是在逐渐的减少
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp=arr[i];//定义临时变量存放arr[i]的值
        for (int k=i*2+1;k<length;k=k*2+1) {
            if (k+1<length&&arr[k]<arr[k+1]){
                k++; //将k指向子节点中最大的那个节点
            }
            if (arr[k]>temp){
                arr[i]=arr[k];//交换，把较大的值赋值给arr[i]
                i=k;//!!!i指向k，继续循环比较
            }else{//如果arr[k]比i对应的那个节点还要小那么就不用调整
                break;
            }
        }
        //循环结束，我们已经将以i为父节点的最大值，放在了（局部）树的顶部
        arr[i]=temp;//将temp调整到最后的位置
    }
}

