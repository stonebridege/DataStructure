package part3;

public class MergeLinkedList {
    public static void main(String[] args) {
        LinkedList l1 = new LinkedList();
        l1.add(new ListNode(1));
        l1.add(new ListNode(3));
        l1.add(new ListNode(3));
        l1.add(new ListNode(5));
        l1.add(new ListNode(7));

        LinkedList l2 = new LinkedList();
        l2.add(new ListNode(2));
        l2.add(new ListNode(4));
        l2.add(new ListNode(4));
        l2.add(new ListNode(6));
        l2.add(new ListNode(6));
        l2.add(new ListNode(8));
        LinkedList l3 = mergeTwoLists(l1, l2);
        l3.list();

    }

    public static LinkedList mergeTwoLists(LinkedList l1, LinkedList l2) {
        int len1 = LinkedList.getLength(l1.getHead());
        int len2 = LinkedList.getLength(l2.getHead());
        LinkedList l3 = new LinkedList();

        ListNode tempListNode1 = l1.getHead().next;
        ListNode tempListNode2 = l2.getHead().next;
        ListNode tempListNode3 = l3.getHead();
        for (int i = 0, len = len1 + len2; i <= len + 1; i++) {
            if (tempListNode1 == null) {
                tempListNode3.next = tempListNode2;
                break;
            } else if (tempListNode2 == null) {
                tempListNode3.next = tempListNode1;
                break;
            }
            if (tempListNode1.number <= tempListNode2.number) {
                tempListNode3.next = tempListNode1;
                tempListNode1 = tempListNode1.next;
            } else {
                tempListNode3.next = tempListNode2;
                tempListNode2 = tempListNode2.next;
            }
            tempListNode3 = tempListNode3.next;
        }
        return l3;
    }
}

class ListNode {
    public int number;
    public ListNode next; //指向下一个节点

    public ListNode(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "number=" + number +
                '}';
    }
}

class LinkedList {
    private ListNode head = new ListNode(0);

    public ListNode getHead() {
        return head;
    }

    public void add(ListNode listNode) {
        ListNode tempNode = head;
        while (true) {
            if (tempNode.next != null) {
                tempNode = tempNode.next;
            } else {
                tempNode.next = listNode;
                break;
            }
        }
    }

    public void list() {
        ListNode tempNode = head;
        if (tempNode.next == null) {
            throw new RuntimeException("链表为空");
        }

        while (tempNode.next != null) {
            System.out.println(tempNode.next);
            tempNode = tempNode.next;
        }
    }

    public static int getLength(ListNode head) {
        if (head.next == null) {
            return 0;
        }
        int count = 0;
        ListNode tempNode = head;
        while (tempNode.next != null) {
            count++;
            tempNode = tempNode.next;
        }
        return count;
    }
}