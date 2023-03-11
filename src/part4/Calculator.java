package part4;

import java.lang.reflect.Array;

public class Calculator {
    public static void main(String[] args) {
        //1.定义各种变量
        //1.1.定义表达式
        String expression = "40*50*2-5+40-5+4-4";
        //1.2.创建两个栈(数栈、符号栈)
        ArrayStack2<Integer> numStack = new ArrayStack2<>(Integer.class, 100);
        ArrayStack2<String> operStack = new ArrayStack2<>(String.class, 100);
        //1.3.用于扫描expression的指针变量
        int index = 0;
        //1.4.需要被计算的num1和num2
        int num1;
        int num2;
        //1.5.操作符号
        String oper;
        //1.6.两数计算的结果
        int res;
        //1.7.如果数值是多为，用StringBuilder对象进行拼接
        StringBuilder builder;
        //1.8.扫描字符串expression得到的字符
        String character = "";
        //2.开始计算
        while (index < expression.length()) {
            //2.1.获取expression的字符串character
            character = expression.substring(index, index + 1);
            //2.2.字符串指针向后移一位
            index++;
            //2.3.判断character是<运算符>还是<数字>
            //   运算符：进行后续计算操作
            //   不是运算符：判断后一位字符是否是数字
            if (ArrayStack2.isOperate(character)) {
                //2.4.如果是运算符，判断<符号栈>是否为空，是则直接入栈
                if (operStack.isEmpty()) {
                    operStack.push(character);
                } else {
                    //2.5.如果符号栈不为空
                    //2.5.1.获取在符号栈栈顶的运算符preOperate
                    String preOperate = operStack.peek();
                    //2.5.2.获取preOperate和当前运算符的优先级数值
                    int preOperatePriority = ArrayStack2.priority(preOperate);
                    int currentOperatePriority = ArrayStack2.priority(character);
                    //2.5.3.如果前《运算符的优先级数值》<=preOperate的优先级数值
                    if (currentOperatePriority <= preOperatePriority) {
                        //2.5.4.从数栈中pop出两个数，在从符号栈中pop出一个符号，进行运算，将得到结果入数栈
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = ArrayStack2.cal(num1, num2, oper);
                        numStack.push(res);
                        //2.5.5.然后将当前的操作符入符号栈
                        operStack.push(character);
                    } else {
                        //2.5.6.如果当前操作符的优先级大于栈中操作符的优先级， 则直接入符号栈。
                        operStack.push(character);
                    }
                }
            } else {
                //2.6.如果不是运算符,是数字,则将其添加到builder中
                builder = new StringBuilder();
                builder.append(character);
                //2.6.1.判断character后面的字符是否为数字，如果是则继续添加到builder，否则就停止遍历expression
                String tempCharacter = "";
                while (index < expression.length()) {
                    tempCharacter = expression.substring(index, index + 1);
                    //tempCharacter是操作符直接跳出
                    if (ArrayStack2.isOperate(tempCharacter)) {
                        break;
                    } else {
                        //tempCharacter是数字继续遍历
                        builder.append(tempCharacter);
                        index++;
                    }
                }
                //2.6.3.将数字添加到数栈
                numStack.push(Integer.parseInt(builder.toString()));
            }
        }
        //3.如果数表中数字个数大于1，则遍历数表和符号表，顺序的从数栈和符号栈中pop出
        // 相应的数和符号，并运行计算，直到数栈里的数字只有一个就是最终结果
        while (numStack.getTop() > 0) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = ArrayStack2.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("%s的计算结果是:%d", expression, numStack.pop());
    }
}

/**
 * 定义一个栈结构
 *
 * @param <T> :泛型
 */
class ArrayStack2<T> {
    // 栈的大小
    private final int maxSize;
    // 数组，数组模拟栈，数据就放在该数组
    private final T[] stack;
    // top表示栈顶，初始化为-1
    private int top = -1;

    /**
     * 构造函数
     *
     * @param clz     :栈中存放的数据类型的类
     * @param maxSize :栈的容量
     */
    public ArrayStack2(Class<T> clz, int maxSize) {
        this.maxSize = maxSize;
        stack = (T[]) Array.newInstance(clz, maxSize);
    }

    /**
     * 获取指向栈顶位置的top，如果top大于-1，则栈中有数据
     *
     * @return :this.top
     */
    public int getTop() {
        return this.top;
    }

    /**
     * 查看栈顶数据的方法，并不出栈
     *
     * @return :栈顶的一个数据
     */
    public T peek() {
        return stack[top];
    }

    /**
     * 判断栈是否已满
     *
     * @return :判断结果
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }


    /**
     * 判断栈是否为空
     *
     * @return :判断结果
     */
    public boolean isEmpty() {
        return top == -1;
    }


    /**
     * 将数据压入栈中(push)
     *
     * @param value :被压入栈的数据
     */
    public void push(T value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈-pop, 将栈顶的数据出栈
     *
     * @return :被出栈的数据
     */
    public T pop() {
        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        T value = stack[top];
        top--;
        return value;
    }

    /**
     * 返回运算符的优先级数值(优先级是程序员来确定,优先级用数字表示,数字越大,则优先级就越高)
     *
     * @param operate :运算符
     * @return :优先级数值
     */
    public static int priority(String operate) {
        if ("*".equals(operate) || "/".equals(operate)) {
            return 20;
        } else if ("+".equals(operate) || "-".equals(operate)) {
            return 10;
        } else {
            return 0;
        }
    }


    /**
     * 判断该字符串是不是操作符号
     *
     * @param operate :字符串
     * @return :判断结果
     */
    public static boolean isOperate(String operate) {
        return "*".equals(operate) || "/".equals(operate) || "+".equals(operate) || "-".equals(operate);
    }

    /**
     * 静态方法：计算方法
     *
     * @param num1    :数1
     * @param num2    :数2
     * @param operate :操作符号
     * @return :计算结果
     */
    public static int cal(int num1, int num2, String operate) {
        int res = 0; // res用于存放计算的结果
        switch (operate) {
            case "*":
                res = num2 * num1;
                break;
            case "/":
                if (num1 == 0) {
                    throw new RuntimeException("被除数不能为零");
                }
                res = num2 / num1;
                break;
            case "+":
                res = num2 + num1;
                break;
            case "-":
                res = num2 - num1;
                break;
            default:
                break;
        }
        return res;
    }
}
