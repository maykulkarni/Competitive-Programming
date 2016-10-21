package InterviewPrep;

/**
 * Created by mayur on 6/7/16.
 */

public class SortStack {
    public static void main(String[] a) {
        MyStack myStack = new MyStack(10);
        myStack.push(10);
        myStack.push(22);
        myStack.push(55);
        myStack.push(47);
        myStack.push(8);
        myStack.push(99);
        new SortStackSolver().reverseStack(myStack);
        for (; !myStack.isEmpty(); ) {
            System.out.println(myStack.pop());
        }
    }
}

class SortStackSolver {

    public void reverseStack(MyStack myStack) {
        int currentElement = myStack.pop();
        if (!myStack.isEmpty())
            reverseStack(myStack);
        insertBottom(myStack, currentElement);
    }

    private void insertBottom(MyStack myStack, int currentElement) {
        if (myStack.isEmpty() || myStack.peek() > currentElement)
            myStack.push(currentElement);
        else {
            int temp = myStack.pop();
            insertBottom(myStack, currentElement);
            myStack.push(temp);
        }
    }
}

class MyStack {
    int[] stack;
    int topPointer;

    public MyStack(int size) {
        stack = new int[size];
        topPointer = -1;
    }

    public int peek() {
        return stack[topPointer];
    }

    public int pop() {
        if (this.isEmpty()) {
            System.out.println("Underflow");
            return -1;
        }
        int temp = stack[topPointer];
        topPointer--;
        return temp;
    }

    public boolean isEmpty() {
        return topPointer == -1;
    }

    public void push(int data) {
        topPointer++;
        stack[topPointer] = data;
    }
}