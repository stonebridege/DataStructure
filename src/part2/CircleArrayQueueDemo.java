package part2;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        System.out.println("测试数组模拟环形队列的案例~~~");
        // 创建一个环形队列
        CircleArray queue = new CircleArray(5); //说明设置5, 其队列的有效数据最大是4
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            System.out.println("n(num): 查看队列中有多少数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'n': // 查看队列头的数据
                    try {
                        int num = queue.size();
                        System.out.printf("查看队列中有个%d数据\n", num);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }

}


class CircleArray {
    private final int maxSize; // 表示数组的最大容量
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    //front 的初始值 = 0
    private int front;
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    //rear 的初始值 = 0
    private int rear;
    private final int[] circleQueue; // 该数据用于存放数据, 模拟队列

    public CircleArray(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.circleQueue = new int[arrMaxSize];
    }

    /**
     * 判断队列是否满
     *
     * @return :true or false
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     *
     * @return :true or false
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 求出当前队列有效数据的个数
     *
     * @return :循环队列中的元素个数
     */
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    /**
     * 将数据n添加到循环队列中
     *
     * @param n ：需要添加到循环队列中的数据
     */
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满，不可以添加数据");
            return;
        }
        circleQueue[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 将队列中的第一个数据出列
     *
     * @return :出列的第一个数据
     */
    public int getQueue() {
        int rtnVal = circleQueue[front];
        circleQueue[front] = 0;
        front = (front + 1) % maxSize;
        return rtnVal;
    }


    /**
     * 依次显示队列的所有数据,并不所有数据出列
     */
    public void showQueue() {
        int index = front;
        for (int i = front, queueSize = size(); i < queueSize; i++) {
            System.out.println(circleQueue[(i % maxSize)]);
        }
    }


    // 显示队列的头数据， 注意不是取出数据
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return circleQueue[front];
    }
}