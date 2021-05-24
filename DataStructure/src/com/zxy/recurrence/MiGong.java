package com.zxy.recurrence;
//迷宫问题
public class MiGong {
    public static void main(String[] args) {
        int[][] map=new int[7][6];//创建地图
        //设置地图的墙，值为1
        for(int i=0;i<6;i++){
            map[0][i]=1;
            map[6][i]=1;
        }
        for (int i=0;i<7;i++){
            map[i][0]=1;
            map[i][5]=1;
        }
        map[3][1]=1;
        map[3][2]=1;
        System.out.println("地图的情况");
        for (int i=0;i<7;i++){
            for (int j=0;j<6;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        setWay(map,1,1);
        System.out.println("标识过的地图");
        for (int i=0;i<7;i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    //定义1表示墙，2表示走过，3表示走不通
    public static boolean setWay(int[][] map,int i,int j){
        if(map[5][4]==2){
            return true;
        }else{
            if(map[i][j]==0){//表明这个点没走过
                //按照策略下-》右-》上-》左
                map[i][j]=2;
                if(setWay(map,i+1,j)){//往下走
                    return true;
                }else if(setWay(map,i,j+1)){//向右走
                    return true;
                }else if (setWay(map,i-1,j)){//向上走
                    return true;
                }else if (setWay(map,i,j-1)){//向左走
                    return true;
                }else {
                    map[i][j]=3;//说明该点走不通，是死路
                    return false;
                }
            }else   //map[i][j]!=0,可能是1，2，3
                return false;

        }
    }
}
