package com.zxy.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;//创建一个集合存储顶点
    private int[][] edges;//用一个二维数组存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;//记录某个节点是否被访问
    public static void main(String[] args) {
        int n=5;//节点（顶点）的个数
        String[] vertexes={"A","B","C","D","E"};
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String vertex:vertexes){
            graph.insertVertex(vertex);
        }
        //添加边
        graph.insertEdge(0,1,1);//A-B
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();
//        System.out.println("深度优先遍历");
//        graph.dfs();
        System.out.println("广度优先遍历");
        graph.bfs();
    }
    //构造器，初始化,n表示顶点的数量
    public Graph(int n){
        vertexList=new ArrayList<>(n);//初始化集合
        edges=new int[n][n];//初始化二维数组
        numOfEdges=0;
        isVisited=new boolean[n];
    }
    //添加顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param ver1 第一个顶点的数组下标
     * @param ver2 第二个顶点的数组下标
     * @param weight 边的权重，这里我们1表示连接，0表示未连接
     */
    public void insertEdge(int ver1,int ver2,int weight){
        //因为是无向图，所以两点之间都设置成一样
        edges[ver1][ver2]=weight;
        edges[ver2][ver1]=weight;
        numOfEdges++;//边的数目+1
    }
    //返回顶点
    //i表示传入的顶点的下标
    public String getVertex(int i){
        return vertexList.get(i);
    }
    //返回边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //显示图
    public void showGraph(){
        for (int[] row:edges){
            System.out.println(Arrays.toString(row));
        }
    }
    //得到第一个邻接节点的下标w
    public int getFirstNeighbor(int index){
        for (int j=0;j<vertexList.size();j++){
            if (edges[index][j]>0){//表示这两个节点是邻接节点
                return j;
            }
        }
        return -1;//没有该节点的邻接节点
    }
    /**
     * 根据前一个邻接节点的下标，来获取下一个邻接节点
     * @param v1 这里v1指当前节点的下标
     * @param v2 v2指当前节点的第一个邻接节点的下标
     * @return 返回的是当前节点的第二个邻接节点
     */
    public int getNextNeighbor(int v1,int v2){
        for (int j=v2+1;j<vertexList.size();j++){
            if (edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }
    //深度优先遍历算法
    //i第一次就是0
    private void dfs(boolean[] isVisited,int i){
        //首先访问该节点
        System.out.print(getVertex(i)+"->");
        //将这个节点设置为已经访问过
        isVisited[i]=true;
        //查找该节点的第一个邻接节点
        int j=getFirstNeighbor(i);
        while(j!=-1){//说明该节点存在邻接节点
            //如果它的邻接节点没有被访问过
            if (isVisited[j]==false){
                //递归
                dfs(isVisited,j);
            }else{//如果该邻接节点被访问过
                j=getNextNeighbor(i,j);//找到i的第二个邻接节点,继续循环
            }
        }

    }
    //对dfs进行一个重载，遍历所有的节点，并进行dfs
    public void dfs(){
        for (int i=0;i<vertexList.size();i++){
            if (!isVisited[i]) {//该节点没有被访问
                dfs(isVisited, i);
            }
        }
    }
    //广度优先遍历算法
    //对一个节点进行广度优先遍历
    private void bfs(boolean[] isVisited,int i){
        int u;//表示队列的头节点对应的下标
        int w;//表示邻接节点
        LinkedList queue=new LinkedList();//定义一个队列用来表示访问过的节点的顺序
        System.out.print(getVertex(i)+"->");
        isVisited[i]=true;//标记为已访问
        queue.addLast(i);//添加到队列
        while (!queue.isEmpty()){//如果队列不为空
            u=(Integer) queue.removeFirst();//移出队列的第一个元素（最先进队列的节点的下标）
            w=getFirstNeighbor(u);//得到第一个邻接节点下标
            while (w!=-1){
                if (isVisited[w]==false){//如果没有被访问过
                    System.out.print(getVertex(w)+"->");//输出该节点
                    isVisited[w]=true;//标记
                    queue.addLast(w);
                }else{//被访问过
                    //访问它的下一个邻接节点
                    w=getNextNeighbor(u,w);//体现广度优先搜索
                }
            }
        }
    }
    //bfs方法重载
    public void bfs(){
        for (int i=0;i<vertexList.size();i++){
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
}
