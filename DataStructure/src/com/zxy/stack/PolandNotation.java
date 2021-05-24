package com.zxy.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //原表达式为（32+4）*5-6
        //为了方便，逆波兰式的数字和符号用空格隔开
       // String expression="32 4 + 5 * 6 -";
        //将"3 4 + 5 * 6 - "放到ArrayList中
        //将ArrayList传递给一个方法，遍历ArrayList,配合栈完成运算
       // int res=calculate(getListString(expression));
       // System.out.println("res="+res);

        String expression2="11+((2+3)*4)-5";
        List<String> list=toInfixExpression(expression2);
        List<String> list2=parseSuffixExpressionList(list);
        int result=calculate(list2);
        System.out.println("运算结果为："+result);

    }
    //将后缀表达式转化为list
    public static List<String> getListString(String ex){
        String[] split=ex.split(" ");  //将表达式根据空格分开并放入数组
        List<String> list=new ArrayList<>();
        for(String ele:split){//增强for循环，将数组中的元素都放入到list中
            list.add(ele);
        }
        return list;
    }
    //将中缀表达式转化为list
    public static List<String> toInfixExpression(String s){
        List<String> ls=new ArrayList<>();
        int i=0;//定义一个指针，用于遍历s
        String str;//用于多位数的拼接
        char c;//每遍历到一个字符就放入c
        do{

            if (s.charAt(i)<48||s.charAt(i)>57){//ASCII中48到57为数字
                c=s.charAt(i);
                ls.add(c+"");
                i++;
            }else {
                 str = "";
             while(i<s.length()&&s.charAt(i)>=48&&s.charAt(i)<=57) {
                 c=s.charAt(i);
                 str=str+c;//拼接
                 i++;
             }ls.add(str);
            }
        }while(i<s.length());
        return ls;
    }
    //将中缀表达式对应的list转化为后缀表达式对应的list
    public static List<String > parseSuffixExpressionList(List<String> ls){
        Stack<String> stack=new Stack<>();
        List<String> list=new ArrayList<>();
        for(String item:ls){
            if(item.matches("\\d+")){
                list.add(item);
            }else if (item.equals("(")){
                stack.push(item);
            }else if(item.equals(")")){
                while(!stack.peek().equals("(")){
                    list.add(stack.pop());
                }stack.pop();
            }else{
                while(true) {
                if (stack.size()==0 || stack.peek().equals("(")) {
                    stack.push(item);
                    break;
                } if (priority(item) > priority(stack.peek())) {
                    stack.push(item);
                    break;
                }
                    list.add(stack.pop());

            }
            }
        }
        while (stack.size()!=0){
            list.add(stack.pop());
        }
        return list;
    }

    //返回运算符的优先级，用数字表示，数字越大优先级越高
    public static int priority(String oper) {
        int res=0;
        switch (oper){
            case"+":
                res=1;
                break;
            case "-":
                res=1;
                break;
            case "*":
                res=2;
                break;
            case "/":
                res=2;
                break;
            case "(":
                res=3;
                break;
            case ")":
                res=3;
                break;
            default:
                System.out.println("运算符出错");
        }
        return res;
    }
    public static int calculate(List<String> list){
        //创建栈,只需要一个栈
        Stack<String> stack=new Stack<>();
        //遍历
        for (String item:list){
            if(item.matches("\\d+")){//使用正则表达式，匹配多位数
                stack.push(item);
            } else{
                int num1=Integer.parseInt(stack.pop());
                int num2=Integer.parseInt(stack.pop());
                int res=0;
                if(item.equals("+")){
                    res=num2+num1;
                }else if (item.equals("-")){
                    res=num2-num1;
                }else if (item.equals("*")){
                    res=num2*num1;
                }else if (item.equals("/")){
                    res=num2/num1;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res+"");//因为要放入的是一个字符串，所以加上空字符
            }
        }
        return Integer.parseInt(stack.pop());
        }
    }

