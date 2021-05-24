package com.zxy.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    static Map<Byte,String> huffmanCodes=new HashMap<>();//将哈夫曼编码存放在map中
    static StringBuilder stringBuilder=new StringBuilder();//用于拼接路径，表示相对应的哈夫曼编码
    public static void main(String[] args) {
        String content="i like like like java do you like a java";
        byte[] byteContent=content.getBytes();//将字符串转化成byte型数组存放
        //System.out.println(Arrays.toString(byteContent));
//        List<Node> list=getNodes(byteContent);
//        Node root=createHuffmanTree(list);
//        //preOrder(root);
//        System.out.println("哈夫曼编码为");
//        System.out.println(getCodes(root));
//
//        byte[] huffmanCodeBytes=zip(byteContent,huffmanCodes);
//        System.out.println("压缩之后的数组为");
//        System.out.println(Arrays.toString(huffmanCodeBytes));
        System.out.println("压缩之后的哈夫曼字节数组为"+Arrays.toString(huffmanZip(byteContent)));
        String str=byteToBinary(huffmanZip(byteContent));
        System.out.println(str);
        byte[] sourceByte=toSourceString(str,huffmanCodes);
        System.out.println("解码之后的字符串为");
        System.out.println(new String(sourceByte));

        //测试压缩文件
//        String srcFile="f://a.png";
//        String destFile="f://a.zip";
//        ZipFile(srcFile,destFile);
        //测试解压文件
        String zipFile="f://a.zip";
        String dest="f://a2.png";
        unZipFile(zipFile,dest);
    }
    //使用一个方法将前面的方法封装起来，便于调用

    /**
     *
     * @param bytes 原始的字符串对应的数组
     * @return 经过哈夫曼编码处理后的字节数组（压缩后的数组）
     */
    public static byte[] huffmanZip(byte[] bytes){
        List<Node> list=getNodes(bytes);
        //创建哈夫曼树
        Node root=createHuffmanTree(list);
        //获取对应的哈夫曼编码
        Map<Byte,String> huffmanCodes=getCodes(root);
        //根据生成的哈夫曼编码，压缩得到哈夫曼字节数组
        byte[] huffmanCodeBytes=zip(bytes,huffmanCodes);
        return huffmanCodeBytes;
    }
    //为了方便，重载getCode方法
    public static Map<Byte,String> getCodes(Node root){
        if (root==null){
            return null;
        }
        getCodes(root,"",stringBuilder);
        return huffmanCodes;
    }
    //定义一个方法，将byte类型的数组的元素放到list集合里
    public static List<Node> getNodes(byte[] bytes){
        List<Node> nodes=new ArrayList<>();//创建一个ArrayList
        //遍历bytes，统计每个byte出现的次数-->map[key,value]
        Map<Byte,Integer> counts=new HashMap<>();
        //将数组中的元素先放入map中
        for (byte b:bytes){
            Integer count=counts.get(b);//返回map集合中b所映射的键值，即byte出现的次数
            if (count==null){
                counts.put(b,1);
            }else{
                counts.put(b,count+1);
            }
        }
        //遍历map
        for (Map.Entry<Byte,Integer> entry:counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));//将map中的元素都放入nodes中
        }
        return nodes;
    }
    //创建哈夫曼树
    public static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size()>1) {
            Collections.sort(nodes);//排序
            Node left=nodes.get(0);
            Node right=nodes.get(1);
            //新节点没有data，只有weight
            Node parent=new Node(null,left.weight+right.weight);
            parent.left=left;
            parent.right=right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    //前序遍历
    public static void preOrder(Node root){
        if (root==null){
            System.out.println("该哈夫曼树为空，无法遍历");
            return;
        }else
            root.preOrder();
    }
    //得到哈夫曼编码

    /**
     * 将传入的node的所有叶子节点的哈夫曼编码得到，并放入map集合中
     * @param node 传入节点
     * @param code 路径，向左是0，向右是1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1=new StringBuilder(stringBuilder);
        //将code加入到stringBuilder1中
        stringBuilder1.append(code);
        if (node!=null) {//如果node为空则不做处理
            if (node.data == null) {//非叶子节点
                //递归
                //向左递归
                getCodes(node.left, "0", stringBuilder1);
                //向右递归
                getCodes(node.right,"1",stringBuilder1);
            }else{//找到了叶子节点
                huffmanCodes.put(node.data,stringBuilder1.toString());//将编码存入map
            }
        }
    }
    //将字符串压缩成字节数组
    //这里对每8位字符处理时，是通过补码转换为原码
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();//用于字符串拼接
        for (byte b:bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println(stringBuilder.toString());
        int len;//用来表示字符串转换成byte有多少个（每8位一个byte）
        int length=stringBuilder.length();
        if (length%8==0){//字符串长度正好是8的整数倍
            len=length/8;
        }else{
            len=length/8+1;
        }
        byte[] huffmanCodeBytes=new byte[len];//创建数组用于存放哈夫曼编码转换成的字节
        int index=0;//用于huffmanCodeBytes数组的索引
        for (int i=0;i<length;i=i+8){//8表示步长
            String str;
            if (i+8>length){
                str=stringBuilder.substring(i);//如果不是8的整数倍，那么从i开始一直到字符串的最后
            }else {
                str = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index]=(byte) Integer.parseInt(str,2);
            index++;
        }
        return huffmanCodeBytes;
    }


    /**
     * 解压（解码）
     *     将字节数组转换成二进制拼接起来的字符串
     * @param huffmanCodeBytes 传入压缩完成之后的字节数组
     */
    public static String byteToBinary(byte[] huffmanCodeBytes){
        StringBuilder stringBuilder=new StringBuilder();//用于拼接字符串
        for (int i=0;i<huffmanCodeBytes.length;i++){
            int temp=huffmanCodeBytes[i];//将字节型转换成int型
            if (i==huffmanCodeBytes.length-1){//如果遍历到了最后一个字节数组元素
                if (temp>=0){//如果这个数大于等于0，则直接拼接
                    String str=Integer.toBinaryString(temp);
                    stringBuilder.append(str);
                    break;
                }
            }
            if (temp>=0){
                temp |=256;//按位与256   1 0000 0000 | 0000 0001 => 1 0000 0001
            }
            String str=Integer.toBinaryString(temp);//将每个元素转化成二进制字符串,返回的是temp对应的二进制的补码

            stringBuilder.append(str.substring(str.length()-8));//截取字符串的后8位进行拼接
        }
        return stringBuilder.toString();
    }
    //将二进制字符串转换成原字符串
    public static byte[] toSourceString(String str,Map<Byte,String> huffmanCodes){
        List<Byte> list=new ArrayList<>();//创建集合，存放byte
        //将map反转
        Map<String,Byte> map=new HashMap<>();
        for (Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        //遍历字符串
        for (int i=0;i<str.length(); ){
        int count=1;//小计数器
            Byte b=null;
            while(true){
                String key=str.substring(i,i+count);
                b=map.get(key);
                if(b!=null){//map中包含该键
                    list.add(b);
                    break;
                }else{
                    count++;
                }
            }
            i+=count;
        }
        //把集合转换成byte数组
        byte[] bytes=new byte[list.size()];
        for (int k=0;k<bytes.length;k++){
            bytes[k]=list.get(k);//将集合里的元素放入到byte数组中
        }
        return bytes;
    }

    /**
     *    压缩文件
     * @param src 源文件路径
     * @param dest 目标文件路径
     */
    public static void ZipFile(String src,String dest){
        FileInputStream is=null;//输入流
        OutputStream os=null;//输出流
        ObjectOutputStream oos=null;//对象输出流
        try {
            is = new FileInputStream(src);
            byte[] b=new byte[is.available()];//创建字节数组，用于存放文件
            is.read(b);//将文件读到数组里
            //压缩
            byte[] huffmanBytes = huffmanZip(b);//得到压缩后的字节数组
            os=new FileOutputStream(dest);//创建文件输出流
            //使用对象流便于区分字节数组对象和哈夫曼编码
            oos=new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);//写入字节数组对象
            oos.writeObject(huffmanCodes);//写入哈夫曼编码，方便以后解码时使用，否则无法解压
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压缩
     * @param zipFile 准备解压的文件路径
     * @param dest 解压后文件的生成路径
     */
    public static void unZipFile(String zipFile,String dest){
        InputStream is=null;//输入流
        ObjectInputStream ois=null;//对象输入流
        OutputStream os=null;
        try {
            is = new FileInputStream(zipFile);
            ois=new ObjectInputStream(is);
            //读取压缩后的字节数组huffmanBytes
            byte[] huffmanBytes=(byte[]) ois.readObject();
            //System.out.println(Arrays.toString(huffmanBytes));
            //读取哈夫曼编码表
            Map<Byte,String> huffmanCodes=(Map<Byte, String>) ois.readObject();
            //解压
            String str=byteToBinary(huffmanBytes);//得到二进制字符串
            //System.out.println(str);
            byte[] bytes=toSourceString(str,huffmanCodes);//得到解压后的字节数组
            //System.out.println(new String(bytes));
            //将数组写入到输出流
            os=new FileOutputStream(dest);
            os.write(bytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
class Node implements Comparable<Node>{
    Byte data;//存放数据(字符)本身，如'a'=>97,以ascii的形式
    int weight;//权值，代表字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;//从小到大排列
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
}
