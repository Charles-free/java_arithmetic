package com.zxy.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize=20;
    public static void main(String[] args) {
        int[] arr={1,8,10,8,9,1000,1234};
        int index=fibSearch(arr,1 );
        System.out.println("index="+index);
    }
    //创建一个斐波拉契数列
    public static int[] fib(){
        int[] f=new int[maxSize];
        f[0]=1;
        f[1]=1;
        for (int i=2;i<f.length;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }
    //斐波拉契查找(非递归)
    //斐波拉契数列公式f[k]=f[k-1]+f[k-2]
    public static int fibSearch(int[] arr,int findVal){
        int left=0;
        int right=arr.length-1;
        int mid=0;
        int k=0;//表示斐波拉契数列中的下标
        int[] f=fib();//获取斐波拉契数列
        while(arr.length>f[k]){//如果数组的长度大于斐波拉契数列中的值，那么斐波拉契数列向后遍历，直到刚好找到一个大于或等于arr长度的数
            k++;
        }

        int[] temp= Arrays.copyOf(arr,f[k]);//新建一个数组，数组长度为发f[k]
        for (int i=right+1;i<f[k];i++){
            temp[i]=arr[right];
        }

        //接下来类似于二分查找和插值查找
        while(left<=right){
            mid=left+f[k-1]-1;//斐波拉契查找算法的求mid的公式
            if(findVal<temp[mid]){//向左查找
                right=mid-1;
                k=k-1;//因为左边的元素个数为f[k-1]
            } else if (findVal>temp[mid]){//向右查找
                left=mid+1;
                k=k-2;//因为右边的元素个数为f[k-2]
            }else{//找到了，分两种情况
                if (mid<=right){//在原arr数组内找到
                    return mid;
                }else {//找到的是填充值
                    return right;
                }
            }
        }
        //没有找到
        return -1;
    }
}
