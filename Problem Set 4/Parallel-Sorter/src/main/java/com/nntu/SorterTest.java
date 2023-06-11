package com.nntu;

public class SorterTest {

    private static final int SIZE_OF_ARRAY = 10_000;

    public static void main(String[] args) {

        // `Sorter` contains sort methods to compare
        Sorter sorter = new Sorter();

        // Create first array and its identical copy
        int[] arrOne = new int[SIZE_OF_ARRAY];
        sorter.randomlyFillArray(arrOne);
        int[] arrOneCopy = arrOne.clone();

        // Create second array and its identical copy
        int[] arrTwo = new int[SIZE_OF_ARRAY];
        sorter.randomlyFillArray(arrTwo);
        int[] arrTwoCopy = arrTwo.clone();

        System.out.println(
                "One thread have sorted two arrays in " + sorter.measureSortTimeInOneThread(arrOne, arrTwo)
                        + " milliseconds"
        );

        System.out.println(
                "Two threads have sorted two arrays in " + sorter.measureSortTimeInTwoThreads(arrOneCopy, arrTwoCopy)
                        + " milliseconds"
        );
    }
}