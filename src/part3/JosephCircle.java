package part3;

public class JosephCircle {
    public static void main(String[] args) {
        //1.测试构建环形链表和遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);// 加入5个小孩节点
        circleSingleLinkedList.showBoy();
        //2.测试约瑟夫小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 5);  // 2->4->1->5->3
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个first节点,当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        //1.nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //2.辅助指针curBoy，帮助构建环形链表
        Boy curBoy = null;
        //3.使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //4.根据编号，创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                //4.1.如果是第一个小孩
                //4.1.1.构成环状
                first = boy;
                first.setNext(first);
                //4.1.2.让curBoy指向第一个小孩
                curBoy = first;
            } else {
                //4.2.如果不是第一个小孩
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoy() {
        Boy tempBoy = first;
        while (true) {
            System.out.printf("第%d个小孩\n", tempBoy.getNo());
            tempBoy = tempBoy.getNext();
            if (tempBoy == first) {
                break;
            }
        }
    }

    // 根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //1.先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        Boy helper = first;
        //2.将helper指向最后小孩的最后一个节点
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        //3.小孩报数前，先让first和helper移动(k-1)次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        System.out.println("依次出圈-----------");
        while (true) {
            //4.如果第一个first和helper指向同一个节点，则此时只剩下最后一个节点了
            if (first == helper) {
                System.out.printf("最后小孩%d出圈\n", first.getNo());
                break;
            }
            //5.让first和helper指针同时的移动countNum - 1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //6.这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //7.这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
    }
}

// 创建一个Boy类，表示一个节点
class Boy {
    private int no;// 编号
    private Boy next; // 指向下一个节点,默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
