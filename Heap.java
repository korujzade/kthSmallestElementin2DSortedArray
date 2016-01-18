/**
 * Created by ko on 17/01/16.
 */
public class kthSmallest {

    public static void main(String args[]) {
        print();
    }

    // print kth smallest element
    public static void print() {
        int mat[][] ={  {10, 20, 30, 40},
                        {15, 25, 35, 45},
                        {25, 29, 37, 48},
                        {32, 33, 39, 50},
                     };

        int k = 2;
        int res = kthSmallest(mat, 4, k);
        System.out.println(k +"th smallest element is: " + res);
    }

    // return kth smallest element in 2d mat[][] 
    public static int kthSmallest(int[][] mat, int n,int k) {
        if (k <= 0 || k > n*n) {
            return Integer.MAX_VALUE;
        }

        // create minHeap of elements from first row
        HeapNode[] nodeArr = new HeapNode[n];
        for (int i = 0; i < n; i++) {
            nodeArr[i] = new HeapNode();
            nodeArr[i].data = mat[0][i];
            nodeArr[i].r = 0;
            nodeArr[i].c = i;
        }
        buildHeap(nodeArr, n);

        HeapNode hr = new HeapNode();
        for (int i = 0; i < k; i++) {
            hr = new HeapNode(nodeArr[0]);
            int nextVal = (hr.r < (n - 1) ? mat[hr.r + 1][hr.c]: Integer.MAX_VALUE);

            nodeArr[0].data = nextVal;
            nodeArr[0].r = hr.r + 1;
            nodeArr[0].c = hr.c;

            // heapify the root
            minHeapify(nodeArr, 0, n);
        }
        return hr.data;
    }

    public static void buildHeap(HeapNode[] arr, int n) {
        int i = (n -1)/2;
        while (i >= 0) {
            minHeapify(arr, i, n);
            i--;
        }
    }

    // min Heapify ith node
    public static void minHeapify(HeapNode arr[], int i, int heap_size) {
        int l = i*2 + 1;
        int r = i*2 + 2;
        int smallest = i;
        if (l < heap_size && arr[l].data < arr[i].data)
            smallest = l;
        if (r < heap_size && arr[r].data < arr[smallest].data)
            smallest = r;
        if (smallest != i ) {
            HeapNode tmp = new HeapNode(arr[i]);
            arr[i] = new HeapNode(arr[smallest]);
            arr[smallest] = new HeapNode(tmp);
            minHeapify(arr, smallest, heap_size);
        }
    }
}

class HeapNode {
    int data, r, c;

    public HeapNode() {
        this.data = data;
        this.r = r;
        this.c = c;
    }

    public HeapNode(HeapNode node) {
        this.data = node.data;
        this.r = node.r;
        this.c = node.c;
    }

}
