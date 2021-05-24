package com.zxy.search;
//线性查找
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr={2,4,1,6,3,8,5};
        int index=seqSearch(arr,3);
        if(index==-1){
            System.out.println("该数不在数组中");
        }
        System.out.println("这个数在数组中，且数组下标为："+index);
    }
    public static int seqSearch(int[] arr,int value){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==value){
                return i;
            }
        }
        return -1;
    }
}
