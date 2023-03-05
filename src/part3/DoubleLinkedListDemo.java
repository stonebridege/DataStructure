package part3;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero5 = new HeroNode2(5, "佐助", "二柱子");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero5);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        HeroNode2 newHeroNode1 = new HeroNode2(5, "公孙胜", "入云龙");
        HeroNode2 newHeroNode2 = new HeroNode2(3, "公孙胜", "入云龙");
        HeroNode2 newHeroNode3 = new HeroNode2(1, "公孙胜", "入云龙");
        doubleLinkedList.updateNode(newHeroNode);
        doubleLinkedList.updateNode(newHeroNode1);
        doubleLinkedList.updateNode(newHeroNode2);
        doubleLinkedList.updateNode(newHeroNode3);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
        // 删除
        doubleLinkedList.deleteNode(3);
        doubleLinkedList.deleteNode(5);
        doubleLinkedList.deleteNode(1);
        doubleLinkedList.deleteNode(0);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }
}

/**
 * 定义一个双向链表的类
 */
class DoubleLinkedList {

    /**
     * 先初始化一个头节点, 头节点不要动, 不存放具体的数据
     */
    private final HeroNode2 head = new HeroNode2(0, "", "");


    /**
     * 遍历双向链表
     */
    public void list() {
        //1.判断该链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空");
        }
        //2.获取head节点后第一个节点
        HeroNode2 heroNode = head.next;
        //3.遍历每个节点，如果该节点不会空则打印，游标移到下一个节点
        while (heroNode != null) {
            System.out.println(heroNode);
            heroNode = heroNode.next;
        }
    }


    /**
     * 在双向链表的最后添加一个节点
     *
     * @param heroNode :新节点
     */
    public void add(HeroNode2 heroNode) {
        HeroNode2 tempNode = head;
        //1.遍历链表找到最后一个节点
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        //2.将最后一个节点tempNode的next指向新节点
        tempNode.next = heroNode;
        //3.将新节点的pre指向tempNode
        heroNode.pre = tempNode;
    }

    /**
     * 修改一个节点的内容, 可以看到双向链表的节点内容修改和单向链表一样
     *
     * @param newHeroNode :新节点
     */
    public void updateNode(HeroNode2 newHeroNode) {
        //1.判断该链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空无法更新节点");
        }
        //2.遍历所有节点，找到符合要求的节点
        HeroNode2 tempNode = head.next;
        while (tempNode != null) {
            //3.找到节点后，将新节点newHeroNode替换原先节点tempNode
            if (tempNode.no == newHeroNode.no) {
                newHeroNode.pre = tempNode.pre;
                newHeroNode.next = tempNode.next;
                tempNode.pre.next = newHeroNode;
                //4.如果tempNode不是链表的最后一个节点，则将其后面的节点的pre指向newHeroNode
                if (tempNode.next != null) {
                    tempNode.next.pre = newHeroNode;
                }
                break;
            }
            tempNode = tempNode.next;
        }
    }

    /**
     * 从双向链表中删除一个节点
     *
     * @param no:要删除节点的编号 1.对于双向链表，我们可以直接找到要删除的这个节点
     *                    2.找到后，自我删除即可
     */
    public void deleteNode(int no) {
        //1.判断该链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空无法更新节点");
        }
        //2.遍历所有节点，找到符合要求的节点
        HeroNode2 tempNode = head.next;
        while (tempNode != null) {
            //3.找到节点后，删除目标节点
            if (tempNode.no == no) {
                tempNode.pre.next = tempNode.next;
                //4.如果目标节点不是链表的最后一个节点则将目标节点的下一个节点的pre指向目标节点的pre
                if (tempNode.next != null) {
                    tempNode.next.pre = tempNode.pre;
                }
                break;
            }
            tempNode = tempNode.next;
        }
    }
}

/**
 * 定义HeroNode2，每个HeroNode 对象就是一个节点
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点, 默认为null
    public HeroNode2 pre; // 指向前一个节点, 默认为null

    /**
     * 节点构造器
     *
     * @param no       :节点编号
     * @param name     :节点名称
     * @param nickname :节点昵称
     */
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    /**
     * 重写toString，打印每个节点的信息
     *
     * @return :节点信息
     */
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}