package aCurrent;

import java.util.HashSet;

/**
 * Created by mayur on 25/8/16.
 */
public class IntersectionPoint {
    public static void main(String[] args) {
        LinkList one = new LinkList(1);
        one.next = new LinkList(2);
        one.next.next = new LinkList(3);
        one.next.next.next = new LinkList(4);
        LinkList two = new LinkList(5);
        two.next = new LinkList(6);
        two.next.next = one.next.next.next;
        findIntersection(one, two);
    }

    private static void findIntersection(LinkList one, LinkList two) {
        HashSet<String> set = new HashSet<>();
        while (one != null) {
            set.add(one.toString());
            one = one.next;
        }
        while (two != null) {
            if (set.contains(two.toString())) {
                System.out.println("Intersection : " + two.data);
            }
            two = two.next;
        }
    }
}

class LinkList {
    int data;
    LinkList next;

    LinkList(int data) {
        this.data = data;
    }
}