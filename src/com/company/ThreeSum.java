package com.company;
import java.lang.management.*;
import java.lang.management.ManagementFactory;
import java.util.Collections;
import java.util.Random;
import java.util.Arrays;
import java.lang.String;


public class ThreeSum {
    private int list[];

    public ThreeSum(int length){
        list = new int[length];
    }

    // Simple bubble sort.
    // https://www.geeksforgeeks.org/bubble-sort/
    // Used above for simplicity's sake.
    public void bubbleSortList(){
        int listLength = list.length;
        for(int i = 0; i < listLength - 1; i++) {
            for(int j = 0; j < listLength - i - 1; j++){
                if(list[j] > list[j+1]){
                    int temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }
            }
        }
    }
    // Assumes the list to already be sorted before this will work.
    public void removeDuplicates(){
        int listLength = list.length;
        int[] newList = new int[listLength - getNumDuplicates()];
        int newListIndex = 0;
        newList[0] = list[0];

        // Put the elements into the new array.
        for(int x = 1; x < listLength - 1; x++){
            if(list[x] != newList[newListIndex]) {
                newListIndex++;
                newList[newListIndex] = list[x];
            }
        }
        //Assign list to the newlist.
        list = newList;
    }
    // Fills the datastructure with random values.
    public void generateRandomList(int min, int max){
        for(int x = 0; x < list.length; x++) {
            list[x] = (int)(Math.random() * (max - min + 1) + max);
        }
    }
    private int getNumDuplicates(){
        // Find how many duplicates there are.
        int listLength = list.length;
        int duplicates = 0;

        for(int x = 0; x < listLength - 2; x++){
            if(list[x] == list[x+1]) {
                duplicates++;
            }
        }
        return duplicates;
    }
    public int listSize(){
        return list.length;
    }
    public int elementAt(int index){
        return list[index];
    }

}
