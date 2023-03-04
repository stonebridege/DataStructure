package part3;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        /**
         * 生成多个节点数据进行测试
         */
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "林冲", "豹子头");
        HeroNode hero6 = new HeroNode(6, "林冲", "豹子头");
        HeroNode hero7 = new HeroNode(7, "林冲", "豹子头");
        HeroNode hero8 = new HeroNode(8, "林冲", "豹子头");


        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        singleLinkedList.addByOrder(hero7);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero6);
        singleLinkedList.addByOrder(hero5);
        singleLinkedList.addByOrder(hero8);
        singleLinkedList.addByOrder(hero3);


        System.out.println("原来链表的情况~~");
        singleLinkedList.list();

        System.out.println("倒数第1个节点数据：" + findLastIndexNode(singleLinkedList.getHead(), 1));
        System.out.println("倒数第3个节点数据：" + findLastIndexNode(singleLinkedList.getHead(), 3));
        System.out.println("倒数第5个节点数据：" + findLastIndexNode(singleLinkedList.getHead(), 5));

        System.out.println(getLength(singleLinkedList.getHead()));

        System.out.println("修改后的链表的情况~~");
        HeroNode hero15 = new HeroNode(5, "林冲1", "豹子头");
        HeroNode hero16 = new HeroNode(6, "林冲1", "豹子头");
        HeroNode hero17 = new HeroNode(7, "林冲1", "豹子头");
        HeroNode hero18 = new HeroNode(100, "林冲1", "豹子头");
        singleLinkedList.update(hero15);
        singleLinkedList.update(hero16);
        singleLinkedList.update(hero17);
        singleLinkedList.update(hero18);
        singleLinkedList.list();

        System.out.println("反转");
        reversalList(singleLinkedList.getHead());
        singleLinkedList.list();
        System.out.println(getLength(singleLinkedList.getHead()));

        System.out.println("删除指定链表的情况~~");
        singleLinkedList.deleteNode(1);
        singleLinkedList.deleteNode(5);
        singleLinkedList.deleteNode(3);
        singleLinkedList.deleteNode(7);
        singleLinkedList.list();
        System.out.println(getLength(singleLinkedList.getHead()));


        System.out.println("测试逆序打印单链表, 没有改变链表的结构~~");
        singleLinkedList.reversePrint(singleLinkedList.getHead());


    }


    //将单链表反转
    public static void reversalList(HeroNode head) {
        //1.如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //2.定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode tempNode = head.next;
        //3.定义next指向当前节点[tempNode]的下一个节点
        HeroNode next = null;
        //4.定义新的链表reverseHead的头
        HeroNode reverseHead = new HeroNode(0, "", "");
        //5.遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (tempNode != null) {
            //5.1.先用next暂时保存当前节点的后面所有的数据，因为后面需要使用
            next = tempNode.next;
            //5.2.将tempNode的下一个节点指向新的链表的最前端,即将reverseHead里原有的数据全部保存在tempNode下
            tempNode.next = reverseHead.next;
            //5.3.将将tempNode连接到新的链表reverseHead上
            reverseHead.next = tempNode;
            //5.4.让tempNode后移
            tempNode = next;
        }
        //6.将head.next指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    //查找单链表中的倒数第k个结点 【新浪面试题】
    //思路
    //1. 编写一个方法，接收head节点，同时接收一个index
    //2. index 表示是倒数第index个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    //5. 如果找到了，则返回该节点，否则返回nulll
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode heroNode = head.next;
        for (int i = 0; i < size - index; i++) {
            heroNode = heroNode.next;
        }
        return heroNode;
    }


    /**
     * 方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int count = 0;
        HeroNode tempNode = head;
        while (tempNode.next != null) {
            count++;
            tempNode = tempNode.next;
        }
        return count;
    }
}


/**
 * 定义SingleLinkedList，用于管理链表上的所有节点
 */
class SingleLinkedList {
    /**
     * 先初始化一个头节点, 头节点不存放任何数据
     */
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 返回头节点
     *
     * @return :返回头节点
     */
    public HeroNode getHead() {
        return head;
    }

    /**
     * 在链表末尾添加一个新节点
     *
     * @param heroNode :要添加的新节点
     */
    public void add(HeroNode heroNode) {
        HeroNode tempNode = head;
        while (true) {
            if (tempNode.next != null) {
                tempNode = tempNode.next;
            } else {
                tempNode.next = heroNode;
                break;
            }
        }
    }


    /**
     * 根据新增节点的序号在链表的合适位置添加新的节点(如果有这个排名，则添加失败，并给出提示)
     *
     * @param heroNode ：要被添加到链表中的节点
     */
    public void addByOrder(HeroNode heroNode) {
        if (head.no >= heroNode.no) {
            throw new RuntimeException("要添加的节点序号不得小于或者等于头节点的序号!");
        }
        //1.情况1：如果头节点后无任何数据，则直接添加新节点
        if (head.next == null) {
            head.next = heroNode;
            return;
        }
        HeroNode tempNode = this.head;
        // 遍历链表，找到新节点的位置
        while (tempNode.next != null && tempNode.next.no < heroNode.no) {
            tempNode = tempNode.next;
        }
        // 将新节点插入到链表中
        heroNode.next = tempNode.next;
        tempNode.next = heroNode;
    }


    /**
     * 修改节点的信息, 根据no编号来修改，即no编号不能改.
     *
     * @param newHeroNode :要更新节点的数据
     */
    public void update(HeroNode newHeroNode) {
        HeroNode tempNode = head;
        while (true) {
            if ((tempNode.next != null)) {
                if (tempNode.next.no == newHeroNode.no) {
                    newHeroNode.next = tempNode.next.next;
                    tempNode.next = newHeroNode;
                    break;
                }
            } else {
                System.out.printf("要更新的 %d 节点不存在\n", newHeroNode.no);
                break;
            }
            tempNode = tempNode.next;
        }
    }

    /**
     * 根据no删除指定的节点
     * 1.head不能动，因此我们需要一个tempNode辅助节点找到待删除节点的前一个节点
     * 2.说明我们在比较时，是temp.next.no和需要删除的节点的no比较
     *
     * @param no ：要删除节点的序号
     */
    public void deleteNode(int no) {
        HeroNode tempNode = head;
        while (true) {
            if ((tempNode.next != null)) {
                if (tempNode.next.no == no) {
                    tempNode.next = tempNode.next.next;
                    break;
                }
            } else {
                System.out.printf("要删除的 %d 节点不存在\n", no);
                break;
            }
            tempNode = tempNode.next;
        }
    }

    /**
     * 遍历链表
     */
    public void list() {
        HeroNode tempNode = head;
        if (tempNode.next == null) {
            throw new RuntimeException("链表为空");
        }

        while (tempNode.next != null) {
            System.out.println(tempNode.next);
            tempNode = tempNode.next;
        }
    }

    public static void reversePrint(HeroNode head) {
        HeroNode tempNode = head;
        if (tempNode.next == null) {
            throw new RuntimeException("链表为空");
        }
        Stack<HeroNode> stack = new Stack<>();
        while (tempNode.next != null) {
            stack.push(tempNode.next);
            tempNode = tempNode.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}


/**
 * 定义HeroNode,即链表的节点对象
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点


    /**
     * 自定义构造器
     *
     * @param no       ：序号
     * @param name     ：名字
     * @param nickname ：昵称
     */
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    /**
     * 重新定义toString方法
     *
     * @return :打印节点所有信息
     */
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}