package com.chenxt.bootdemo.algo;

import java.util.Stack;

public class Fibonacci {
    public static int Fibonacci(int num) {
        if (num < 2) {
            return num;
        }
        return Fibonacci(num - 1) + Fibonacci(num - 2);
    }

    static class Node {
        int n = 0;//结点下标
        int tag = 0;//标志信息：1为左，2为右
    }

    public static int FibonacciStack(int n) {
        int sum = 0;
        Stack<Node> s = new Stack<>();
        do {
            while (n > 1)//先依次压入栈
            {
                Node w = new Node();
                w.n = n;
                w.tag = 1;
                s.push(w);
                n--;
            }

            sum = sum + n;

            while (!s.empty()) {
                Node w = s.pop();

                if (w.tag == 1)//如果是左子树，放到右边，压栈，n-2
                {
                    w.tag = 2;
                    s.push(w);
                    n = w.n - 2;
                    break;
                }
            }
        } while (!s.empty());
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(FibonacciStack(15));
        System.out.println(Fibonacci(15));
    }
}