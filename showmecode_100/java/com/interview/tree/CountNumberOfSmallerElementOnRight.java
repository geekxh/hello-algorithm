package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/count-smaller-elements-on-right-side/
 * Test cases
 * All same elements
 * Many duplicates
 * Negative numbers
 * 0s
 * Sorted or reverse sorted
 */
public class CountNumberOfSmallerElementOnRight {
    private Node leftRotate(Node root){
        Node newRoot = root.right;
        root.right = root.right.left;
        newRoot.left = root;
        root.height = setHeight(root);
        root.size = setSize(root);
        newRoot.height = setHeight(newRoot);
        newRoot.size = setSize(newRoot);
        return newRoot;
    }
    
    private Node rightRotate(Node root){
        Node newRoot = root.left;
        root.left = root.left.right;
        newRoot.right = root;
        root.height = setHeight(root);
        root.size = setSize(root);
        newRoot.height = setHeight(newRoot);
        newRoot.size = setSize(newRoot);
        return newRoot;
    }

    private int setHeight(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0));
    }
    
    private int height(Node root){
        if(root == null){
            return 0;
        }else {
            return root.height;
        }
    }
    
    private int setSize(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.size : 0), (root.right != null ? root.right.size : 0));
    }
    
    private Node insert(Node root, int data,int count[],int pos,int val){
        if(root == null){
            count[pos] = val;
            return Node.newNode(data);
        }
        if(root.data <= data){
            val++;
            if(root.left != null){
                val += root.left.size;
            }
            root.right = insert(root.right,data,count,pos,val);
        }
        else{
            root.left = insert(root.left,data,count,pos,val);
        }
        if(height(root.left) - height(root.right) > 1){
            if(height(root.left.left) >= height(root.left.right)){
                root = rightRotate(root);
        }else{
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        }else if(height(root.right) - height(root.left) > 1){
            if(height(root.right.right) >= height(root.right.left)){
                root = leftRotate(root);
            }else{
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        }
        else{
            root.height = setHeight(root);
            root.size = setSize(root);
        }
        return root;
    }
    
    public int[] count(int input[]){
        int count[] = new int[input.length];
        Node root = null;
        for(int i=input.length-1; i >= 0; i--){
            root = insert(root,input[i],count,i,0);
        }
        return count;
    }

    public static void main(String args[]){
        int input[] =  {12, 1, 2, 3, 0, 11, 4};
        CountNumberOfSmallerElementOnRight cns = new CountNumberOfSmallerElementOnRight();
        int count[] = cns.count(input);
        for(int c : count){
            System.out.print(c + " ");
        }
        System.out.println();
        int input1[] = {5, 4, 3, 2, 1};
        int count1[] = cns.count(input1);
        for(int c : count1){
            System.out.print(c + " ");
        }
        
        System.out.println();
        int input2[] = {1,2,3,4,5};
        int count2[] = cns.count(input2);
        for(int c : count2){
            System.out.print(c + " ");
        }
    }
}
