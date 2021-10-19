package ex3;

public class MyQueue {
    private MyArray myArray;
    private final int Infinit = 9500;
    private int startIndex;
    private int lastIndex;
    private int numOfElements;

    public MyQueue() {
        startIndex = 0;
        lastIndex = 0;
        numOfElements = 0;
        myArray = new MyArray();
    }

    public int getSize() {
        return numOfElements;
    }

    public void enqueue(int value) {
        myArray.set(lastIndex, value);
        lastIndex++;
        numOfElements++;
    }

    public int dequeue() {
        if (numOfElements == 0) return -1;
        int returnElement = myArray.get(startIndex);
        startIndex++;
        numOfElements--;

        return returnElement;
    }

    public boolean isEmpty() {
        return numOfElements == 0;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i = startIndex; i < lastIndex; i++) {
            s.append(myArray.get(i) + ", ");
        }

        return s.toString();
    }

    public static void main(String args[]) {
        MyQueue queue = new MyQueue();
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(10);
        queue.enqueue(-1);
        queue.enqueue(2);
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.getSize());
        System.out.println(queue);
        queue.enqueue(9);
        queue.enqueue(queue.dequeue());
        queue.enqueue(11);
        queue.enqueue(22);
        System.out.println(queue);
        while(!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println("");
        System.out.println(queue);
    }

}
