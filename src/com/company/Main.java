package com.company;
import java.lang.management.*;
import java.lang.management.ManagementFactory;
import java.util.Collections;
import java.util.Random;
import java.util.Arrays;
import java.lang.String;


public class Main {

    public static void main(String[] args) {
        int startingSize = 4;
        int maxSize = 5000;
        int minValue = Integer.MIN_VALUE;
        int maxValue = Integer.MAX_VALUE;

        long startTime;
        long endTime;
        // Table Header
        String emptyString = "";

        String header = String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s",emptyString,"Brute Force",emptyString,emptyString,"Faster",emptyString,emptyString,"Fastest",emptyString,emptyString);
        String headerTwo = String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s","N","Time","Doubling Ratio","Expected Doubling","Time","Doubling Ratio","Expected Doubling","Time","Doubling Ratio","Expected Doubling");

        System.out.println(header);
        System.out.println(headerTwo);

        float previousTimeBrute = 1;
        float previousFasterTime = 1;
        float previousFastestTime = 1;
        for(int N = startingSize; N < maxSize; N = N * 2) {
            // Brute Force

            System.out.print(String.format("%-20s", N));
            // Datastructure setup.
            ThreeSum bruteForce = new ThreeSum(N);
            bruteForce.generateRandomList(minValue, maxValue);

            startTime = getCpuTime();
            bruteForce(bruteForce);
            endTime = getCpuTime();
            System.out.print(String.format("%-20s", endTime - startTime));
            System.out.print(String.format("%-20s",((endTime - startTime) / previousTimeBrute)));
            previousTimeBrute = endTime - startTime;
            System.out.print(String.format("%-20s","2"));


            // Datastructure setup.
            ThreeSum faster = new ThreeSum(N);
            faster.generateRandomList(minValue, maxValue);
            startTime = getCpuTime();
            faster.bubbleSortList();
            faster.removeDuplicates();
            fasterThreeSum(faster);
            endTime = getCpuTime();
            System.out.print(String.format("%-20s", endTime - startTime));
            System.out.print(String.format("%-20s",((endTime - startTime) / previousFasterTime)));
            previousFasterTime = endTime - startTime;
            System.out.print(String.format("%-20s","2"));

            // Datastructure setup.
            ThreeSum fastest = new ThreeSum(N);
            faster.generateRandomList(minValue, maxValue);
            startTime = getCpuTime();
            fastest.bubbleSortList();
            fastest.removeDuplicates();
            fastestThreeSum(fastest, 0);
            endTime = getCpuTime();
            System.out.print(String.format("%-20s", endTime - startTime));
            System.out.print(String.format("%-20s",((endTime - startTime) / previousFasterTime)));
            previousFasterTime = endTime - startTime;
            System.out.print(String.format("%-20s","2"));

            System.out.println();
        }
        System.out.println();
    }
    public static long getCpuTime(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

    // Brute Force version of the search. Returns the list of 3 that passed. or a list of 3 -1's
    public static int[] bruteForce(ThreeSum data) {
        for(int i = 0; i < data.listSize() - 1; i++){
            for(int j = 0; j < data.listSize() - 1; j++){
                for(int k = 0; k < data.listSize() - 1; k++){
                    if(data.elementAt(i) + data.elementAt(j) + data.elementAt(k) == 0){
                        int list[] = {data.elementAt(i), data.elementAt(j), data.elementAt(k)};
                        return list;
                    }
                }
            }
        }
        int list[] = {-1,-1,-1};
        return list;
    }

    // Search for 3 sum. My Attempt.
    /*
    public static int[] fastestThreeSum(ThreeSum data){
        int center = data.listSize() / 2;
        int leftCenter = center / 2;
        int rightCenter = (center / 2) + center;


        // Iterative Looping.
        for(int x = 0; x < data.listSize() - 1; x++) {
            // Check first position.
            if (data.elementAt(center) + data.elementAt(rightCenter) + data.elementAt(leftCenter) == 0) {
                int list[] = {data.elementAt(center), data.elementAt(rightCenter), data.elementAt(leftCenter)};
                return list;
                // Left side search. if it's -1 we failed. Else we found it.
            } else{
                int checkValueLeft = binarySearch(data, data.elementAt(center) + data.elementAt(rightCenter));
                if(checkValueLeft != -1){
                    int list[] =  {data.elementAt(center), data.elementAt(rightCenter), data.elementAt(checkValueLeft)};
                    return list;
                    // Right side search.
                } else {
                    int checkValueRight = binarySearch(data, data.elementAt(center) + data.elementAt(leftCenter));
                    if(checkValueRight != -1){
                        int list[] =  {data.elementAt(center), data.elementAt(rightCenter), data.elementAt(checkValueRight)};
                        return list;
                    }
                }
            }
            if(x % 2 == 0){
                center += x;
                leftCenter = center / 2;
                rightCenter = (center / 2) + center;
            }
            else{
                center -= x;
                leftCenter = center / 2;
                rightCenter = (center / 2) + center;
            }
        }


        // Failed to find condition.
        int list[] = {-1,-1,-1};
        return list;
    }
    */

    // Addapted version from geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
    // After attempting and workign on the one above.
    public static int[] fastestThreeSum(ThreeSum data, int sum){
        int l, r;
            for (int i = 0; i < data.listSize() - 2; i++) {

                // To find the other two elements, start two index variables
                // from two corners of the array and move them toward each
                // other
                l = i + 1; // index of the first element in the remaining elements
                r = data.listSize() - 1; // index of the last element
                while (l < r) {
                    if (data.elementAt(i) + data.elementAt(l) + data.elementAt(r)  == sum) {
                        System.out.print("Triplet is " + data.elementAt(i)  + ", " + data.elementAt(l)  + ", " + data.elementAt(r));
                        int[] list = {data.elementAt(i), data.elementAt(l), data.elementAt(r)};
                        return list;
                    }
                    else if (data.elementAt(i) + data.elementAt(l) + data.elementAt(r)  < sum)
                        l++;

                    else // A[i] + A[l] + A[r] > sum
                        r--;
                }
            }

            // If we reach here, then no triplet was found
            int list[] = {-1,-1,-1};
            return list;
    }


    // From the book as N^2log(n) solution to the problem.
    public static int[] fasterThreeSum(ThreeSum data) {
        int n = data.listSize();
        for(int i = 0; i < n; i++){
            for(int j = i +1; j < n; j++){
                int k = binarySearch(data, -(data.elementAt(i) + data.elementAt(j)));
                if(k > j){
                    int list[] = {data.elementAt(i), data.elementAt(j), data.elementAt(k)};
                    return list;
                }
            }
        }
        int list[] = {-1, -1, -1};
        return list;
    }

    // Binary search algorithm
    // Copied from https://www.geeksforgeeks.org/binary-search/
    // For simplicity. minus the changes made to accomodate the ThreeSum class.
    public static int binarySearch(ThreeSum data, int x)
    {
        int l = 0, r = data.listSize() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (data.elementAt(m) == x)
                return m;

            // If x greater, ignore left half
            if (data.elementAt(m) < x)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }

}
