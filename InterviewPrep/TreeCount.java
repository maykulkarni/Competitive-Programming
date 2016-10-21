package InterviewPrep;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by mayur on 11/7/16.
 */
class Tree {
    int data;
    Tree left;
    Tree right;

    Tree(int data) {
        this.data = data;
    }

    public String toString() {
        return String.valueOf(data);
    }
}

public class TreeCount {
    public static void main(String[] args) {
        Tree myTree = new Tree(5);
        myTree.left = new Tree(3);
        myTree.left.left = new Tree(2);
        myTree.left.right = new Tree(4);
        myTree.right = new Tree(7);
        myTree.right.left = new Tree(6);
        myTree.right.right = new Tree(8);
        Deque<Integer> dq = new ArrayDeque<>();
        dq.push(5);
        print(myTree, dq);
    }

    private static void print(Tree myTree, Deque<Integer> list) {
        if (myTree.left == null && myTree.right == null) {
            System.out.println(list);
            list.pop();
        } else {
            if (myTree.left != null) {
                list.push(myTree.left.data);
                print(myTree.left, list);
            }
            if (myTree.right != null) {
                list.push(myTree.right.data);
                print(myTree.right, list);
            }
            list.pop();
        }
    }
}
