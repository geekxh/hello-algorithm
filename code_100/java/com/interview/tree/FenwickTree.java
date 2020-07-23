package com.interview.tree;

/**
 * Date 04/27/2015
 * @author tusroy
 * 
 * Write a program to implement fenwick or binary indexed tree
 * 
 * A Fenwick tree or binary indexed tree is a data structure providing efficient methods
 * for calculation and manipulation of the prefix sums of a table of values.
 * 
 * Space complexity for fenwick tree is O(n)
 * Time complexity to create fenwick tree is O(nlogn)
 * Time complexity to update value is O(logn)
 * Time complexity to get prefix sum is O(logn)
 * 
 * References
 * http://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
 * https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
 * http://en.wikipedia.org/wiki/Fenwick_tree
 */
public class FenwickTree {

    /**
     * Start from index+1 if you updating index in original array. Keep adding this value 
     * for next node till you reach outside range of tree
     */
    public void updateBinaryIndexedTree(int binaryIndexedTree[], int val, int index){
        while(index < binaryIndexedTree.length){
            binaryIndexedTree[index] += val;
            index = getNext(index);
        }
    }
    
    /**
     * Start from index+1 if you want prefix sum 0 to index. Keep adding value
     * till you reach 0
     */
    public int getSum(int binaryIndexedTree[], int index){
        index = index + 1;
        int sum = 0;
        while(index > 0){
            sum += binaryIndexedTree[index];
            index = getParent(index);
        }
        return sum;
    }
    
    /**
     * Creating tree is like updating Fenwick tree for every value in array
     */
    public int[] createTree(int input[]){
        int binaryIndexedTree[] = new int[input.length+1];
        for(int i=1; i <= input.length; i++){
            updateBinaryIndexedTree(binaryIndexedTree, input[i-1], i);
        }
        return binaryIndexedTree;
    }
    
    /**
     * To get parent
     * 1) 2's complement to get minus of index
     * 2) AND this with index
     * 3) Subtract that from index
     */
    private int getParent(int index){
        return index - (index & -index);
    }
    
    /**
     * To get next
     * 1) 2's complement of get minus of index
     * 2) AND this with index
     * 3) Add it to index
     */
    private int getNext(int index){
        return index + (index & -index);
    }
    
    public static void main(String args[]){
        int input[] = {1,2,3,4,5,6,7};
        FenwickTree ft = new FenwickTree();
        int binaryIndexedTree[] = ft.createTree(input);
        assert 1 == ft.getSum(binaryIndexedTree, 0);
        assert 3 == ft.getSum(binaryIndexedTree, 1);
        assert 6 == ft.getSum(binaryIndexedTree, 2);
        assert 10 == ft.getSum(binaryIndexedTree, 3);
        assert 15 == ft.getSum(binaryIndexedTree, 4);
        assert 21 == ft.getSum(binaryIndexedTree, 5);
        assert 28 == ft.getSum(binaryIndexedTree, 6);
    }
}
