package com.zxy.search;

//插值查找
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1, 4, 7, 9, 12, 16, 25, 54, 76};
//        int[] arr=new int[100];
//        for (int i=0;i<arr.length;i++){
//            arr[i]=i+1;
//        }
        int index=insertValueSearch(0,arr.length-1,arr,54);
        if(index==-1){
            System.out.println("找不到该数");
        }else
            System.out.println("找到该数，数组下标为："+index);
    }

    public static int insertValueSearch(int left, int right, int[] arr, int findVal) {
        System.out.println("查找次数");
        //findVal<arr[0]||findVal>arr[arr.length-1]可以优化查找，也十分必要，防止数组越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal < midVal) {
            return insertValueSearch(left, mid - 1, arr, findVal);//向左递归
        } else if (findVal > midVal) {
            return insertValueSearch(mid + 1, right, arr, findVal);//向右递归
        } else {//找到该数，即findVal=midVal
            return mid;
        }
    }
}