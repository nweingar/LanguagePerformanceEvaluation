import java.util.Random;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void bubbleSort(int arr[], int n)
    {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }
    static void insertionsort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    static void selectionsort(int arr[])
    {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
    static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    static void mergesort(int arr[], int l, int r)
    {
        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergesort(arr, l, m);
            mergesort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // This function takes last element as pivot,
    // places the pivot element at its correct position
    // in sorted array, and places all smaller to left
    // of pivot and all greater elements to right of pivot
    static int partition(int[] arr, int low, int high)
    {
        // Choosing the pivot
        int pivot = arr[high];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (arr[j] < pivot) {

                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    // The main function that implements QuickSort
    // arr[] --> Array to be sorted,
    // low --> Starting index,
    // high --> Ending index
    static void quickSort(int[] arr, int low, int high)
    {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    public static void heapsort(int arr[])
    {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    static void heapify(int arr[], int N, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }
    private static int[] generateRandomArray(int size, Random rand) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt();
        }
        return array;
    }

    public static int search(int arr[], int N, int x)
    {
        for (int i = 0; i < N; i++) {
            if (arr[i] == x)
                return i;
        }
        return -1;
    }

    public static int binarySearch(int arr[], int x)
    {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == x)
                return m;

            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // If we reach here, then element was
        // not present
        return -1;
    }

    public static void main(String[] args) {
        int[] cases={10000,20000,50000,100000,125000,150000,175000,200000};
        cases=new int[]{1000000, 2000000, 5000000, 10000000, 12500000, 15000000, 17500000, 20000000};
        Random random = new Random();

        for(int i=0; i < cases.length; i++) {
            long start = System.nanoTime();
            int[] arr = generateRandomArray(cases[i], random);
            quickSort(arr, 0, arr.length-1);
            int flag = arr[arr.length-1];
            int result = binarySearch(arr, flag);
            if(result == -1){
                System.out.println("Error: Element not present");
                break;
            }
            else{
                long end = System.nanoTime();
                double elapsed = (end-start)*1e-9;
                System.out.println("Run Time: " + elapsed);
            }

        }
        /*
        for(int i=0; i < cases.length; i++) {
            long start = System.nanoTime();
            int[] arr = generateRandomArray(cases[i], random);
            bubbleSort(arr, arr.length);
            long end = System.nanoTime();
            double elapsed = (end-start)*1e-9;
            System.out.println("Run Time: " + elapsed);
        }

         */
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        /*
        for(int i=0; i < cases.length; i++) {
            long start = System.nanoTime();
            int[] arr = generateRandomArray(cases[i], random);
            insertionsort(arr);
            long end = System.nanoTime();
            double elapsed = (end-start)*1e-9;
            System.out.println("Run Time: " + elapsed);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i=0; i < cases.length; i++) {
            long start = System.nanoTime();
            int[] arr = generateRandomArray(cases[i], random);
            selectionsort(arr);
            long end = System.nanoTime();
            double elapsed = (end-start)*1e-9;
            System.out.println("Run Time: " + elapsed);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        */
        /*
        cases=new int[]{1000000, 2000000, 5000000, 10000000, 12500000, 15000000, 17500000, 20000000};
        for(int i=0; i < cases.length; i++) {
            long start = System.nanoTime();
            int[] arr = generateRandomArray(cases[i], random);
            mergesort(arr, 0, arr.length-1);
            long end = System.nanoTime();
            double elapsed = (end-start)*1e-9;
            System.out.println("Run Time: " + elapsed);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i=0; i < cases.length; i++) {
            long start = System.nanoTime();
            int[] arr = generateRandomArray(cases[i], random);
            quickSort(arr, 0, arr.length-1);
            long end = System.nanoTime();
            double elapsed = (end-start)*1e-9;
            System.out.println("Run Time: " + elapsed);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i=0; i < cases.length; i++) {
            long start = System.nanoTime();
            int[] arr = generateRandomArray(cases[i], random);
            heapsort(arr);
            long end = System.nanoTime();
            double elapsed = (end-start)*1e-9;
            System.out.println("Run Time: " + elapsed);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

         */
    }
}