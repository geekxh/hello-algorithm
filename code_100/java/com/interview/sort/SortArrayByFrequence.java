package com.interview.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/sort-elements-by-frequency/
 */
public class SortArrayByFrequence {

    class SortNode{
        int count;
        int firstIndex;
    }
    
    class FrequenceComparator implements Comparator<Integer>{
        private Map<Integer,SortNode> countMap = null;
        FrequenceComparator(Map<Integer,SortNode> countMap){
            this.countMap = countMap;
        }
        @Override
        public int compare(Integer i1, Integer i2) {
            SortNode n1 = countMap.get(i1);
            SortNode n2 = countMap.get(i2);
            if(n1.count > n2.count){
                return -1;
            }else if(n1.count < n2.count){
                return 1;
            }else{
                return n1.firstIndex < n2.firstIndex ? -1 : 1;
            }
        }
        
    }
    
    public void sortByFrequence(Integer arr[]){
        Map<Integer,SortNode> countMap = new HashMap<Integer,SortNode>();
        int index = 0;
        for(int a : arr){
            if(countMap.containsKey(a)){
                SortNode s = countMap.get(a);
                s.count++;
            }else{
                SortNode s = new SortNode();
                s.count = 1;
                s.firstIndex = index;
                countMap.put(a, s);
            }
            index++;
        }
        
        FrequenceComparator freqComparator = new FrequenceComparator(countMap);
        Arrays.sort(arr,freqComparator);
    }
    
    public static void main(String args[]){
        Integer input[] = {5,2,8,9,9,9,2};
        SortArrayByFrequence saf = new SortArrayByFrequence();
        saf.sortByFrequence(input);
        for(int i : input){
            System.out.println(i + " ");
        }
    }
    
}
