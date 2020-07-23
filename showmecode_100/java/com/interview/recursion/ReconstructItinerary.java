package com.interview.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date 03/08/2016
 * @author Tushar Roy
 *
 * Reconstruct itinerary based on ticket.
 *
 * https://leetcode.com/problems/reconstruct-itinerary/
 */
public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        List<Itinerary> input = new ArrayList<>();
        for (String[] ticket : tickets) {
            input.add(new Itinerary(ticket[0], ticket[1]));
        }
        Collections.sort(input);
        List<String> output = new ArrayList<>();
        boolean[] used = new boolean[input.size()];
        findItineraryUtil(input, used, "JFK", output, 0);
        return output;
    }

    boolean findItineraryUtil(List<Itinerary> input, boolean[] used, String end, List<String> output, int count) {
        if (count == used.length) {
            output.add(end);
            return true;
        }
        for (int i = 0; i < input.size(); i++) {
            if (used[i]) {
                continue;
            }
            Itinerary itr = input.get(i);
            if (itr.start.equals(end)) {
                output.add(itr.start);
                used[i] = true;
                if (findItineraryUtil(input, used, itr.dest, output, count + 1)) {
                    return true;
                }
                used[i] = false;
                output.remove(output.size() - 1);
            }
        }
        return false;
    }

    class Itinerary implements Comparable<Itinerary> {
        String start;
        String dest;
        Itinerary(String start, String dest) {
            this.start = start;
            this.dest = dest;
        }

        @Override
        public int compareTo(Itinerary other) {
            if (this.start.equals(other.start)) {
                return this.dest.compareTo(other.dest);
            } else {
                return this.start.compareTo(other.start);
            }
        }
    }

    public static void main(String args[]) {
        String input[][] = {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        ReconstructItinerary ri = new ReconstructItinerary();
        List<String> output = ri.findItinerary(input);
        output.forEach(r -> System.out.print(r + " "));
    }
}
