package com.zxy.search;

import java.util.ArrayList;
import java.util.List;

//二分查找  使用二分查找的前提是该数组是有序的
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr={1,3,4,5,6,7,7,7,7,9,12,};
//        int index=binarySearch(0,arr.length-1,arr,20);
//        if(index==-1){
//            System.out.println("该数不在数组中");
//        }else
//            System.out.println("该数在数组中，且数组下标为："+index);
        List<Integer> list=binarySearch2(0,arr.length-1,arr,7);
        System.out.println(list);
     }

    /**
     *
     * @param left 左边索引
     * @param right 右边索引
     * @param arr 数组
     * @param value 要查找的值
     * @return 如果找到返回该数的索引值，找不到返回-1
     */
    public static int binarySearch(int left,int right,int[] arr,int value){
        if(left>right){//
            return -1;
        }
        int mid=(left+right)/2;
        int midVal=arr[mid];

        if (value>midVal){//说明要查找的数在mid的右边

           return binarySearch(mid+1,right,arr,value);//向右递归
        } else if (value<midVal){//说明要查找的数在mid的左边

           return binarySearch(left,mid-1,arr,value);//向左递归
        }else {//说明value==arr[mid]，在数组中找到了这个数
            return mid;
        }
    }
//用二分查找找出数组中所有等于value的元素，返回下标集合
    public static List<Integer> binarySearch2(int left, int right, int[] arr, int value){
        if(left>right){//
            return new ArrayList<>();
        }
        int mid=(left+right)/2;
        int midVal=arr[mid];

        if (value>midVal){//说明要查找的数在mid的右边

            return binarySearch2(mid+1,right,arr,value);//向右递归
        } else if (value<midVal){//说明要查找的数在mid的左边

            return binarySearch2(left,mid-1,arr,value);//向左递归
        }else {//说明value==arr[mid]，在数组中找到了这个数
            List<Integer> list=new ArrayList<Integer>();
            int temp=mid-1;
            while(true){
                //向左遍历
                if(temp<0||arr[temp]!=value){
                    break;
                }
                list.add(temp);
                temp--;
            }
            list.add(mid);//添加中间值
            temp=mid+1;
            while(true){
                //向右遍历
                if(temp>arr.length-1||arr[temp]!=value){
                    break;
                }
                list.add(temp);
                temp++;
            }
        return list;
        }
    }
}
