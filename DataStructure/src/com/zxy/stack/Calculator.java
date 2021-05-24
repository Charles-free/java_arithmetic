package com.zxy.stack;

public class Calculator {
    public static void main(String[] args) {
        ArrayStack2 numstack = new ArrayStack2(6);
        ArrayStack2 operstack= new ArrayStack2(6);
        int index=0;
        int num1;
        int num2;
        int oper=0;
        int res=0;
        char ch=' ';
        String keepNum="";//用于拼接多位数
        String expression="5-2*9+80";
        while(true){
            ch=expression.substring(index,index+1).charAt(0);
            if(operstack.isOper(ch)) {//如果是一个操作符
                if (!operstack.isEmpty()) {//如果符号栈中非空
                    while(!operstack.isEmpty()&&operstack.priority(ch) <= operstack.priority(operstack.peek())) {//比较优先级
                        //利用循环，在从operstack移除一个操作符后，ch和移除的这个运算符的前面一个运算符比较，优先级如果还相等，继续运算
                        num1 = numstack.pop();
                        num2 = numstack.pop();
                        oper = operstack.pop();
                        res = operstack.cal(num1, num2, oper);
                        numstack.push(res);
                    }
                        operstack.push(ch);
                }else //如果符号栈中为空
                    operstack.push(ch);
            }else{   //如果不是一个操作符（是一个数）
                //不能发现一个数就立即入栈，可能是一个多位数，需要向expression的index后面再看，如果是数就扫描，是符号就入栈
                //需要定义一个字符串用来拼接
                //numstack.push(ch-48);
                keepNum+=ch;
                //防止越界（ch为最后一个时会报错）
                //如果ch是expression最后一位则直接入栈
                if(index==expression.length()-1){
                    numstack.push(Integer.parseInt(keepNum));
                }else if (operstack.isOper(expression.substring(index+1,index+2).charAt(0))){
                    //如果后一位是运算符则入栈
                    numstack.push(Integer.parseInt(keepNum));//转化为int型
                    //重要！！！keepNum清空
                    keepNum="";
                }
            }
            index++;
            if(index>=expression.length()){
                break;
            }
        }
        //扫描完毕
        while(true){
            if(operstack.isEmpty()){//如果符号栈为空，则计算到最后的结果，数栈中还剩最后一个数字
                break;
            }//否则运算
            num1 = numstack.pop();
            num2 = numstack.pop();
            oper=operstack.pop();
            res = operstack.cal(num1, num2, oper);
            numstack.push(res);
        }
        int res2=numstack.pop();
        System.out.println("运算结果为："+res2);
    }
}

    class ArrayStack2 {
        private int maxsize;  //栈的大小
        private int[] stack;
        private int top = -1;  //设置栈顶值默认为-1

        public ArrayStack2(int maxsize) {
            this.maxsize = maxsize;
            stack = new int[this.maxsize];//初始化数组
        }

        //判断栈是否满
        public boolean isFull() {
            return top == maxsize - 1;
        }

        //判断栈是否为空
        public boolean isEmpty() {
            return top == -1;
        }

        //入栈
        public void push(int value) {
            if (isFull()) {
                System.out.println("栈满了");
            }
            top++;
            stack[top] = value;
        }

        //出栈
        public int pop() {
            if (isEmpty()) {
                //抛出异常
                throw new RuntimeException("栈为空");
            }
            int value = stack[top];
            //System.out.printf("stack[%d]=%d已出栈！\n", top, value);
            top--;
            return value;
        }

        //显示栈
        public void showStack() {
            if (isEmpty()) {
                throw new RuntimeException("栈为空,无法显示");
            }
            for (int i = top; i > -1; i--) {
                System.out.printf("stack[%d]=%d\n", i, stack[i]);
            }
        }

        //返回运算符的优先级，用数字表示，数字越大优先级越高
        public int priority(int oper) {
            if (oper == '+' || oper == '-') {
                return 0;
            }
            if (oper == '*' || oper == '/') {
                return 1;
            }else
                return -1;
        }
        //判断是不是一个运算符
        public boolean isOper(char val){
            return val=='+'||val == '-'||val == '*' || val == '/';
        }
        //返回栈顶的值，只是查看不是pop
        public int peek(){
            return stack[top];
        }
        //计算方法
        public int cal(int num1,int num2,int oper){
            int res=0;//用于存放计算结果
            switch(oper){
                case'+':
                    res=num1+num2;
                    break;
                case'-':
                    res=num2-num1;//注意顺序
                    break;
                case '*':
                    res=num1*num2;
                    break;
                case '/':
                    res=num2/num1;
                    break;
                default:
                    break;
            }
            return res;

        }
    }



