package com.nntu;

class Sorter {

    /**
     * Returns the difference, measured in milliseconds, between the end
     * of this method and the start, if two arrays is sorted in one thread.
     * @param arr1 the one-dimensional array
     * @param arr2 the one-dimensional array
     * @return the runtime of this method
     */
    long measureSortTimeInOneThread(int[] arr1, int[] arr2) {
        long start = System.currentTimeMillis();

        bubbleSort(arr1);
        bubbleSort(arr2);

        return System.currentTimeMillis() - start;
    }

    /**
     * Returns the difference, measured in milliseconds, between the end
     * of this method and the start, if two arrays is sorted in two threads.
     * @param arr1 the one-dimensional array
     * @param arr2 the one-dimensional array
     * @return the runtime of this method
     */
    long measureSortTimeInTwoThreads(int[] arr1, int[] arr2) {
        long start = System.currentTimeMillis();

        // Sort `arr1` in other thread and `arr2` in main thread
        Thread sortHelper = new Thread(() -> bubbleSort(arr1));
        sortHelper.start();
        bubbleSort(arr2);
        // Wait for completing other thread to get the real time of sorting two arrays
        try {
            sortHelper.join();
        } catch (InterruptedException ie) { ie.printStackTrace(); }

        return System.currentTimeMillis() - start;
    }

    /**
     * Fills the provided array with random values from 0 to 1000.
     * @param arr the one-dimensional array
     */
    void randomlyFillArray (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1001);
        }
    }

    /**
     * Sorts the provided array from the lowest number to the greatest using Bubble sort.
     * @param arr the one-dimensional array
     */
    private void bubbleSort(int[] arr) {
        int temp;
        boolean isChanged;

        for (int i = 0; i < arr.length; i++) {
            isChanged = false;

            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isChanged = true;
                }
            }

            // Check whether the array has been modified in full iteration by `i`
            if (!isChanged) return;
        }
    }
}