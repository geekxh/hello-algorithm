package com.interview.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * http://www.geeksforgeeks.org/maximum-bipartite-matching/
 */
public class MaximumBiparteMatching {

    public int findMaxMatching(Map<Integer,List<Integer>> jobApplications, List<Integer> allJobs){
        
        Map<Integer,Integer> match = new HashMap<Integer,Integer>();
        int maxMatch = 0;
        for(Integer candidate : jobApplications.keySet()){
            Set<Integer> jobsSeen = new HashSet<Integer>();
            maxMatch += matchJobs(candidate, jobApplications, match, jobsSeen) ==true ? 1 : 0;
        }
        return maxMatch;
    }
    
    private boolean matchJobs(Integer candidate, Map<Integer,List<Integer>> jobApplications,Map<Integer,Integer> match,Set<Integer> jobsSeen){
    
        for(int job : jobApplications.get(candidate)){
            if(jobsSeen.contains(job)){
                continue;
            }
            jobsSeen.add(job);
            
            if(match.get(job) == null){
                match.put(job, candidate);
                return true;
            }
            boolean flag = matchJobs(match.get(job),jobApplications,match,jobsSeen);
            if(flag){
                match.put(job, candidate);
                return true;
            }
        }
        return false;
    }
    
    public static void main(String args[]){
        List<Integer> app0 = new ArrayList<Integer>();
        app0.add(10);
        app0.add(11);
        app0.add(13);
        
        List<Integer> app1 = new ArrayList<Integer>();
        app1.add(10);
        
        List<Integer> app2 = new ArrayList<Integer>();
        app2.add(12);

        List<Integer> app3 = new ArrayList<Integer>();
        app3.add(12);
        app3.add(10);
        app3.add(11);
        
        Map<Integer,List<Integer>> jobApplications = new HashMap<Integer,List<Integer>>();
        jobApplications.put(0, app0);
        jobApplications.put(1, app1);
        jobApplications.put(2, app2);
        jobApplications.put(3, app3);
        MaximumBiparteMatching mbm = new MaximumBiparteMatching();
        List<Integer> allJobs = new ArrayList<Integer>();
        allJobs.add(10);
        allJobs.add(11);
        allJobs.add(12);
        allJobs.add(13);
        System.out.print(mbm.findMaxMatching(jobApplications, allJobs));
        
    }
    
}
