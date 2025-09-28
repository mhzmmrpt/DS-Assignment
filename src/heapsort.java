import java.util.Scanner;

class MaxHeap {
    private int[] heap;
    private int capacity;
    private int size;

    public MaxHeap(int initialCapacity) {
        capacity = initialCapacity;
        heap = new int[capacity];
        size = 0;
    }

    public MaxHeap() {
        this(10);
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    private void resize() {
        capacity *= 2;
        int[] newHeap = new int[capacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    private void heapifyUp(int i) {
        while (i > 0 && heap[i] > heap[parent(i)]) {
            int tmp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = tmp;
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && heap[left] > heap[largest])
            largest = left;

        if (right < size && heap[right] > heap[largest])
            largest = right;

        if (largest != i) {
            int tmp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = tmp;
            heapifyDown(largest);
        }
    }

    public void insert(int value) {
        if (size == capacity) {
            resize();
        }
        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    public int deleteMax() {
        if (size == 0) {
            System.out.println("Heap is empty. Cannot delete.");
            return -1;
        }
        int maxVal = heap[0];
        heap[0] = heap[--size];
        heapifyDown(0);
        return maxVal;
    }

    public void display() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}

public class heapsort {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        Scanner sc = new Scanner(System.in);
        char command;

        while (sc.hasNext()) {
            command = sc.next().charAt(0);
            if (command == 'e') break;

            if (command == 'a') {
                int value = sc.nextInt();
                maxHeap.insert(value);
            } else if (command == 'd') {
                int deleted = maxHeap.deleteMax();
                if (deleted != -1) {
                    System.out.println(deleted);
                }
            } else if (command == 'p') {
                maxHeap.display();
            }
        }
        sc.close();
    }
}
