import random
import numpy as np
import time

def bubbleSort(arr):
    n = len(arr)
     
    # Traverse through all array elements
    for i in range(n):
        swapped = False
 
        # Last i elements are already in place
        for j in range(0, n-i-1):
 
            # Traverse the array from 0 to n-i-1
            # Swap if the element found is greater
            # than the next element
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]
                swapped = True
        if (swapped == False):
            break

def insertionSort(arr):

    # Traverse through 1 to len(arr)
    for i in range(1, len(arr)):

        key = arr[i]

        # Move elements of arr[0..i-1], that are
        # greater than key, to one position ahead
        # of their current position
        j = i-1
        while j >= 0 and key < arr[j] :
                arr[j + 1] = arr[j]
                j -= 1
        arr[j + 1] = key

def merge(arr1,arr2):
    i=0
    j=0
    result=[]
    while(i<len(arr1) and j<len(arr2)):
        if arr2[j]>arr1[i]:
            result.append(arr1[i])
            i+=1
        else:
            result.append(arr2[j])
            j+=1
    while(i<len(arr1)):
        result.append(arr1[i])
        i+=1
    while(j<len(arr2)):
        result.append(arr2[j])
        j+=1
    
    return result
    
def mergeSort(arr):
    if len(arr)<=1:
        return arr
    mid = len(arr)//2
    left = mergeSort(arr[:mid])
    right = mergeSort(arr[mid:])
    
    return merge(left,right)

def selectionSort(A):
    for i in range(len(A)): 
      
    # Find the minimum element in remaining  
    # unsorted array 
        min_idx = i 
        for j in range(i+1, len(A)): 
            if A[min_idx] > A[j]: 
                min_idx = j 
              
    # Swap the found minimum element with  
    # the first element         
    A[i], A[min_idx] = A[min_idx], A[i] 

def partition(array, low, high):

    # Choose the rightmost element as pivot
    pivot = array[high]

    # Pointer for greater element
    i = low - 1

    # Traverse through all elements
    # compare each element with pivot
    for j in range(low, high):
        if array[j] <= pivot:

            # If element smaller than pivot is found
            # swap it with the greater element pointed by i
            i = i + 1

            # Swapping element at i with element at j
            (array[i], array[j]) = (array[j], array[i])

    # Swap the pivot element with
    # the greater element specified by i
    (array[i + 1], array[high]) = (array[high], array[i + 1])

    # Return the position from where partition is done
    return i + 1


# Function to perform quicksort
def quicksort(array, low, high):
    if low < high:

        # Find pivot element such that
        # element smaller than pivot are on the left
        # element greater than pivot are on the right
        pi = partition(array, low, high)

        # Recursive call on the left of pivot
        quicksort(array, low, pi - 1)

        # Recursive call on the right of pivot
        quicksort(array, pi + 1, high)

def heapify(arr, N, i):
    largest = i  # Initialize largest as root
    l = 2 * i + 1     # left = 2*i + 1
    r = 2 * i + 2     # right = 2*i + 2

    # See if left child of root exists and is
    # greater than root
    if l < N and arr[largest] < arr[l]:
        largest = l

    # See if right child of root exists and is
    # greater than root
    if r < N and arr[largest] < arr[r]:
        largest = r

    # Change root, if needed
    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]  # swap

        # Heapify the root.
        heapify(arr, N, largest)

# The main function to sort an array of given size


def heapSort(arr):
    N = len(arr)

    # Build a maxheap.
    for i in range(N//2 - 1, -1, -1):
        heapify(arr, N, i)

    # One by one extract elements
    for i in range(N-1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]  # swap
        heapify(arr, i, 0)

def search(arr, N, x):

    for i in range(0, N):
        if (arr[i] == x):
            return i
    return -1

def binarySearch(arr, l, r, x):
 
    while l <= r:
 
        mid = l + (r - l) // 2
 
        # Check if x is present at mid
        if arr[mid] == x:
            #print("mid")
            return mid
 
        # If x is greater, ignore left half
        elif arr[mid] > x:
            #print("r")
            return binarySearch(arr, l, mid-1, x)
 
        # If x is smaller, ignore right half
        else:
            #print("l")
            return binarySearch(arr, mid+1, r, x)
 
    # If we reach here, then the element
    # was not present
    return -1

def generate_random_array(size):
    # Generates an array with random integers
    return np.random.randint(low=np.iinfo(np.int32).min, high=np.iinfo(np.int32).max, size=size, dtype=np.int32)

if __name__ == "__main__":
    
    cases = [1000000, 2000000, 5000000, 10000000, 12500000, 15000000, 17500000, 20000000]
    for i in range(len(cases)):
        start = time.time()
        arr = generate_random_array(cases[i])
        flag = arr[-1]
        s = binarySearch(arr, 0, len(arr)-1, flag)
        if(s == -1):
            print("error")
        
        stop = time.time()
        elapsed = stop-start
        print("Time: ", elapsed)


