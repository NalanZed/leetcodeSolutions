package bfs;

import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list1.addLast(1);
        list1.addLast(2);
        list1.addLast(3);
        list2.addFirst(1);
        list2.addFirst(2);
        list2.addFirst(3);
        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
    }
}
