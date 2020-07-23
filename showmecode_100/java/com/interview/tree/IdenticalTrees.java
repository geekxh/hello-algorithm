package com.interview.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * http://www.geeksforgeeks.org/check-for-identical-bsts-without-building-the-trees/
 * Test cases
 * Both array should be same size and have exact same elements and first element should be same
 */
public class IdenticalTrees {

    private class BigSmall{
        int big;
        int small;
    }
    
    public boolean sameBST(int tree1[], int tree2[]){
        return sameBST(tree1,tree2,Integer.MIN_VALUE,Integer.MAX_VALUE,0);
    }
    
    private boolean sameBST(int tree1[],int tree2[],int min,int max,int index){
        if(index ==tree1.length){
            return true;
        }
        
        int i=0;
        for(i=0; i < tree2.length; i++){
            if(tree2[i] == tree1[index]){
                break;
            }
        }
        
        BigSmall bs1 = nextBigSmall(tree1,index,min,max);
        BigSmall bs2 = nextBigSmall(tree2,i,min,max);
        
        boolean r1=  tree1[bs1.big] == tree2[bs2.big] && tree1[bs1.small] == tree2[bs2.small] ;
        if(r1 == false){
            return false;
        }
        boolean r2 = true,r3 =true;
        if(bs1.small !=  0){
            r2 = sameBST(tree1,tree2,min,tree1[index],bs1.small);
        }
        if(bs1.big != 0){
            r3 = sameBST(tree1,tree2,tree1[index],max,bs1.big);
        }
        return r2 && r3;
    }
    
    private BigSmall nextBigSmall(int[] tree,int index,int min,int max){
        BigSmall bs = new BigSmall();
        boolean seenBig = false, seenSmall = false;
        for(int i=index+1; i < tree.length && (!seenBig || !seenSmall); i++){
            if(tree[i] > max || tree[i] < min){
                continue;
            }
            if(!seenBig && tree[i] > tree[index]){
                bs.big = i;
                seenBig = true;
            }
            if(!seenSmall && tree[i] < tree[index]){
                bs.small = i;
                seenSmall = true;
            }
        }
        return bs;
    }
    
    /**
     * Little simpler version of above implementation. Uses more space but atleast its easy to understand.
     * All it does is makes 2 list one of larger and one of smaller element than root and then passes
     * them into left and right sides
     * @param arr1
     * @param arr2
     * @return
     */
    
    public boolean sameBST1(int arr1[], int arr2[]){
        List<Integer> list1 = new ArrayList<>();
        for(int i : arr1){
            list1.add(i);
        }
        List<Integer> list2 = new ArrayList<>();
        for(int i : arr2){
            list2.add(i);
        }
        
        return sameBST1(list1, list2);
    }
    
    /**
     * It might not work for duplicate elements in array. For that you have to store
     * both index and actual value to differentiate somehow.
     */
    private boolean sameBST1(List<Integer> arr1, List<Integer> arr2){
        if(arr1.size() == 0 && arr2.size() == 0){
            return true;
        }
        if(arr1.size() == 0 || arr2.size() == 0){
            return false;
        }
        
        if(arr1.get(0) != arr2.get(0)){
            return false;
        }
        List<Integer> smaller1 = new ArrayList<Integer>();
        List<Integer> larger1= new ArrayList<Integer>();
        boolean first = true;
        for(Integer i : arr1){
            if(first){
               first = false;
               continue;
            }
            if(i <= arr1.get(0)){
                smaller1.add(i);
            }else{
                larger1.add(i);
            }
        }
        first = true;
        List<Integer> smaller2 = new ArrayList<Integer>();
        List<Integer> larger2= new ArrayList<Integer>();
        for(Integer i : arr2){
            if(first){
               first = false;
               continue;
            }
            if(i <= arr2.get(0)){
                smaller2.add(i);
            }else{
                larger2.add(i);
            }
        }
        boolean r = compare(smaller1, smaller2) && compare(larger1, larger2);
        if(!r){
            return false;
        }
        return sameBST1(smaller1, smaller2) && sameBST1(larger1, larger2);
        
    }
    
    private boolean compare(List<Integer> l1, List<Integer> l2){
        Set<Integer> s = new HashSet<Integer>();
        for(Integer i : l1){
            s.add(i);
        }
        for(Integer i : l2){
            if(!s.contains(i)){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String args[]){
        int tree1[] = {3,-6,-2,11,9,-10,-1,15,10};
        int tree2[] = {3,11,9,15,-6,10,-2,-1,-10};
        IdenticalTrees it = new IdenticalTrees();
        System.out.println(it.sameBST(tree1, tree2));
        System.out.println(it.sameBST1(tree1, tree2));
    }
}
