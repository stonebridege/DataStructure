package part4;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(10);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(5);
        arrayStack.push(118);
        arrayStack.push(98);
        arrayStack.push(231);
        arrayStack.listStack();
        System.out.printf("出栈的数据是：%d\n", arrayStack.pop());
        System.out.printf("出栈的数据是：%d\n", arrayStack.pop());
        arrayStack.listStack();
    }
}

//定义一个 ArrayStack 表示栈
class ArrayStack {
    /**
     * 栈的容量
     */
    private int maxSize;

    /**
     * 数组，数组模拟栈，数据就放在该数组
     */
    private int[] stack;

    /**
     * top表示栈顶，初始化为-1。在栈中每增加一个数据，top则加1
     */
    private int top = -1;

    /**
     * 构造函数
     *
     * @param maxSize:栈的容量
     */
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    /**
     * 判断栈数据是否已满
     *
     * @return :ture>已满，false>未满
     */
    public boolean isFull() {
        return maxSize - 1 == this.top;
    }

    /**
     * 判断栈是否为空
     *
     * @return ：ture>空，false>非空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 将数据压入栈中
     *
     * @param value :要压入栈中的数据
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        top++;
        this.stack[top] = value;
    }

    /**
     * 将数据出栈
     *
     * @return ：弹出的数据
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，没有数据可以出栈");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈中的数据并不出栈
     */
    public void listStack() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，没有数据可以出栈");
        }
        for (int index = top; index >= 0; index--) {
            System.out.printf("第%d个数据:%d\n", (index + 1), stack[index]);
        }
    }
}
