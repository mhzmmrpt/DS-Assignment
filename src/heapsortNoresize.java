class MaxHeap {
    private int[] heap;
    private int capacity;
    private int size;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

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
            System.out.println("Heap is full. Cannot insert.");
            return;
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
public class heapsortNoresize {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(20); // heap ขนาดสูงสุด 20 ตัว

        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);

        maxHeap.display(); // 70 30 50

        System.out.println("Delete max: " + maxHeap.deleteMax());
        maxHeap.display(); // 50 30
    }
}
