
/**
 * <h1>MinHeap Class</h1>
 * This is the MinHeap class which has capacity,currentSize, minHeap from type HeapNode and array of indexes as instance variable.
 * 
 * @author Rahaf , sarah , Somayah 
 * @version 8.2
 * @since 11-11-2021
 */

class MinHeap {

    int capacity;//the capacity of heap
    int currentSize;//Current heap size
    HeapNode[] miginHeap; //array of type Heap Node
    int[] indexes; //the indexes will be used to decrease the key

    /**
     * This is a MinHeap constructor that indicates parameters and create new Heap Node with capacity by 1.
     *
     * @param capacity the capacity of the Minheap. 
     */
    public MinHeap(int capacity) {

        this.capacity = capacity;
        // new Heap Node with capacity + 1
        minHeap = new HeapNode[capacity + 1];
        indexes = new int[capacity];
        minHeap[0] = new HeapNode();
        minHeap[0].key = Integer.MIN_VALUE;
        minHeap[0].vertex = -1;
        currentSize = 0;

    }

    /**
     * This is the insert method which insert a node into the heap.
     * 
     * @param x the node that want to insert it to the heap.
     */
    public void insert(HeapNode x) {
        
        currentSize++;
        int idx = currentSize;
        minHeap[idx] = x;
        indexes[x.vertex] = idx;
        bubbleUp(idx);
        
    }

    /**
     * This is the bubbleUp method which heapify the node at pos .
     * 
     * @param pos the index of node.
     */
    public void bubbleUp(int pos) {
        
        int parentIdx = pos / 2;
        int currentIdx = pos;
        while (currentIdx > 0 && minHeap[parentIdx].key > minHeap[currentIdx].key) {
            HeapNode currentNode = minHeap[currentIdx];
            HeapNode parentNode = minHeap[parentIdx];

            //swap the positions
            indexes[currentNode.vertex] = parentIdx;
            indexes[parentNode.vertex] = currentIdx;
            swap(currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx / 2;
            
        }
    }

    /**
     * This is the extractMin method which update the indexes[] and move the last node to the top.
     * 
     * @return min node
     */
    public HeapNode extractMin() {
        
        HeapNode min = minHeap[1];
        HeapNode lastNode = minHeap[currentSize];
        //update the indexes[] and move the last node to the top
        indexes[lastNode.vertex] = 1;
        minHeap[1] = lastNode;
        minHeap[currentSize] = null;
        sink(1);
        currentSize--;
        return min;
        
    }

    /**
     * This is the sinkDown method which make the parent node larger than its tow children .
     * 
     * @param k node index.
     */
    public void sink(int k) {
        
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k + 1;
        if (leftChildIdx < heapSize() && minHeap[smallest].key > minHeap[leftChildIdx].key) {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < heapSize() && minHeap[smallest].key > minHeap[rightChildIdx].key) {
            smallest = rightChildIdx;
        }
        if (smallest != k) {

            HeapNode smallestNode = minHeap[smallest];
            HeapNode kNode = minHeap[k];

            //swap the positions
            indexes[smallestNode.vertex] = k;
            indexes[kNode.vertex] = smallest;
            swap(k, smallest);
            sink(smallest);
        }
    
    }

    /**
     * This is the swap method which swap two nodes of the heap.
     * 
     * @param a vertex a
     * @param b vertex b
     */
    public void swap(int a, int b) {
        
        HeapNode temp = minHeap[a];
        minHeap[a] = minHeap[b];
        minHeap[b] = temp;
        
    }

    /**
     * This is the isEmpty method which check if th heap is Empty or not.
     * 
     * @return currentSize == 0
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * This is the heapSize method which return the heap size.
     * 
     * @return currentSize of heap.
     */
    public int heapSize() {
        return currentSize;
    }

}
