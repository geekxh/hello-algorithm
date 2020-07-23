package com.interview.suffixprefix;

/**
 * http://www.geeksforgeeks.org/ternary-search-tree/
 */
public class TernaryTree {

    private Node root = null;
    
    class Node{
        char data;
        boolean isLeaf;
        Node left, right, eq;
    }
    
    public void insert(String data){
        Node root = insert(this.root,data,0);
        this.root = root;
    }
    
    public boolean search(String data){
        return search(root,data,0);
    }
    
    private boolean search(Node root,String data,int pos){
        if(pos == data.length()){
            return true;
        }
        if(root == null){
            return false;
        }
        if(root.data == data.charAt(pos)){
            boolean result = search(root.eq,data,pos+1);
            if(pos == data.length() -1){
                return result && root.isLeaf;
            }
            return result;
        }else if(root.data < data.charAt(pos)){
            return search(root.right,data,pos);
        }else{
            return search(root.left,data,pos);
        }
    }
    private Node insert(Node root,String data,int pos){
        if(pos == data.length()){
            return root;
        }
        if(root == null){
            root = new Node();
            root.data = data.charAt(pos);
            root.eq = insert(root.eq,data,pos+1);
            if(pos == (data.length()-1)){
                root.isLeaf = true;
            }
        }else{
            if(root.data == data.charAt(pos)){
                root.eq = insert(root.eq,data,pos+1);
                if(pos == (data.length()-1)){
                    root.isLeaf = true;
                }
            }
            else if(root.data < data.charAt(pos)){
                root.right = insert(root.right,data,pos);
            }else{
                root.left = insert(root.left,data,pos);
            }
        }
        return root;
    }
    
    public static void main(String args[]){
        TernaryTree tt = new TernaryTree();
        tt.insert("cute");
        tt.insert("as");
        tt.insert("at");
        tt.insert("cut");
        tt.insert("cup");
        tt.insert("time");
        tt.insert("tax");
        tt.insert("bat");
        System.out.println(tt.search("cute"));
        System.out.println(tt.search("cut"));
        System.out.println(tt.search("tax"));
        System.out.println(tt.search("as"));
        System.out.println(tt.search("abat"));
        
    }
}
