package com.interview.array;

/**
 * http://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
 * You can solve this one using kadane wrap since it finds max contiguous sum
 * for wrapped array. That start point is a best place to start a tour.
 * Test cases
 * Check if length of both input array is same
 * Check that there exists a path after kadane wrap responds
 * Check that there is at least one positive difference before you call kadane
 */
public class GasStationCircle {

    public int startTour(int gasAvailable[],int gasRequired[]){
        int start = -1;
        int end = 0;
        int currentGas = 0;
        boolean visitedOnce = false;
        while(start != end){
            currentGas += gasAvailable[end] - gasRequired[end];
            if(start == -1){
                start = end;
            }
            if(end == gasAvailable.length-1 && visitedOnce == false){
                visitedOnce = true;
            }else if(end == gasAvailable.length-1 && visitedOnce == true){
                return -1;
            }
            if(currentGas < 0){
                start = -1;
                currentGas = 0;
            }
            end = (end + 1)%gasAvailable.length;
        }
        
        return end;
    }
    
    /**
     * If it is not guaranteed that tour exists then once you get
     * result of kadanewrap make an actual trip to see if value is positive
     * @return -1 if no solution exists otherwise returns gas station at which to start.
     */
    public int startTour1(int gasAvailable[], int gasRequired[]){
        int diff[] = new int[gasAvailable.length];
        for(int i=0; i < diff.length; i++){
            diff[i] = gasAvailable[i] - gasRequired[i];
        }

        boolean allNegative = true;
        for (int i = 0; i < diff.length; i++) {
            if (diff[i] >= 0) {
                allNegative = false;
                break;
            }
        }

        if (allNegative) {
            return -1;
        }

        KadaneWrapArray kwa = new KadaneWrapArray();
        Triplet t = kwa.kadaneWrap(diff);
        //make sure this solution leads to answer
        int i = t.start;
        int netGas = 0;
        do {
            netGas += diff[i];
            i = (i + 1)%diff.length;
            if (netGas < 0) {
                return -1;
            }
        } while (i != t.start);

        return t.start;
    }

    public static void main(String args[]){
        GasStationCircle gsc = new GasStationCircle();
        int[] gasAvailable = {4, 4, 6};
        int[] gasRequired = {5, 6, 1};
        System.out.println(gsc.startTour(gasAvailable, gasRequired));
        System.out.println(gsc.startTour1(gasAvailable, gasRequired));
    }
}
