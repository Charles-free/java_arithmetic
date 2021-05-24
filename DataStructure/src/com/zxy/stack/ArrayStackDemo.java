package com.zxy.stack;
//用数组方式模拟栈
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack=new ArrayStack(5);
        arrayStack.push(3);
        arrayStack.push(0);
        arrayStack.push(4);
        arrayStack.push(3);
        arrayStack.push(5);
        System.out.println("入栈成功");
        try {
            arrayStack.pop();
            arrayStack.pop();
            arrayStack.pop();
            arrayStack.pop();
            arrayStack.pop();
            arrayStack.pop();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        arrayStack.showStack();

    }
}
class ArrayStack{
    private int maxsize;  //栈的大小
    private int[] stack;
    private int top=-1;  //设置栈顶值默认为-1
    public ArrayStack(int maxsize){
        this.maxsize=maxsize;
        stack=new int[this.maxsize];//初始化数组
    }
    //判断栈是否满
    public boolean isFull(){
        return top==maxsize-1;
    }
    //判断栈是否为空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满了");
        }
        top++;
        stack[top]=value;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈为空");
        }
        int value=stack[top];
        System.out.printf("stack[%d]=%d已出栈！\n", top, value);
        top--;
        return value;
    }
    //显示栈
    public void showStack(){
        if (isEmpty()){
            throw new RuntimeException("栈为空,无法显示");
        }
        for(int i=top;i>-1;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}
