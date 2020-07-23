package com.interview.array;

public class StableMarriageProblem {

    private boolean checkIfNewIsBetter(int priority[][], int bride,
            int currentGroom, int suitor) {
        for (int groom : priority[bride]) {
            if (currentGroom == groom) {
                return false;
            }
            if (suitor == groom) {
                return true;
            }
        }
        return false;
    }

    public int[] findPair(int[][] priority) {
        int pair = priority[0].length;
        int groomToBride[] = new int[pair];
        int brideToGroom[] = new int[pair];
        for(int i=0; i < groomToBride.length; i++){
            groomToBride[i] = -1;
        }
        for(int i=0; i < brideToGroom.length; i++){
            brideToGroom[i] = -1;
        }
        int groom ;
        int remaingGrooms = pair;
        while (remaingGrooms > 0) {
            groom = -1;
            for (int hasBride : groomToBride) {
                if (hasBride != -1) {
                    continue;
                }
                groom++;
                for (int bride : priority[groom]) {
                    if (brideToGroom[bride-pair] == -1) {
                        groomToBride[groom] = bride;
                        brideToGroom[bride-pair] = groom;
                        remaingGrooms--;
                        break;
                    } else {
                        boolean flag = checkIfNewIsBetter(priority, bride,
                                brideToGroom[bride-pair], groom);
                        if (flag) {
                            int currentGroom = brideToGroom[bride-pair];
                            brideToGroom[bride-pair] = groom;
                            groomToBride[groom] = bride;
                            groomToBride[currentGroom] = -1;
                        }
                    }
                }
            }
        }
        return groomToBride;
    }
    
    public static void main(String args[]){
        int priority[][] = {{5,4,7,6},
                           {4,5,6,7},
                           {5,4,6,7},
                           {5,4,7,6},
                           {0,1,2,3},
                           {0,1,3,2},
                           {0,3,1,2},
                           {0,1,2,3}};
        StableMarriageProblem smp = new StableMarriageProblem();
        int[] result = smp.findPair(priority);
        for(int i=0; i < result.length; i++){
            System.out.println(i + " " + result[i]);
        }
    }
}
