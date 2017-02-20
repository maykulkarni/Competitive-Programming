package Random;

public class Demo {
    public static void main(String[] args) {
        ListNode myList = new Demo().new ListNode();
        myList.node = 1;
        myList.next = new Demo().new ListNode();
        myList.next.node = 2;
        myList.next.next = new Demo().new ListNode();
        myList.next.next.node = 3;
        new Demo().reverse(null, myList);
        System.out.println(myList.node + " " + myList.next.node + " " + myList.next.next.node);
    }

    public void reverse(ListNode prev, ListNode currNode) {
        if (currNode == null) {
            return;
        } else {
            reverse(currNode, currNode.next);
            currNode.next = prev;
        }
    }

    class ListNode {
        int node;
        ListNode next;

        @Override
        public String toString() {
            return String.valueOf(this.node);
        }
    }
}