package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/b-tree-set-1-insert-2/
 * http://www.geeksforgeeks.org/b-tree-set-1-introduction-2/
 */
public class BTree {
    private BTreeNode root = null;
    private static int T = 2;
    public void insert(int data){
        if(root == null){
            root = BTreeNode.newNode(data);
            return;
        }
        SplitResult sr = insert(root,data);
        if(sr != null){
            BTreeNode newRoot = BTreeNode.newNode();
            newRoot.n = 1;
            newRoot.isLeaf = false;
            newRoot.keys[0] = sr.c;
            newRoot.child[0] = sr.r1;
            newRoot.child[1] = sr.r2;
            root = newRoot;
        }
    }
    
    public boolean search(int data){
        return search(root,data);
    }
    
    public boolean search(BTreeNode root, int data){
        int i =0;
        while(i < root.n && root.keys[i] < data){
            i++;
        }
        if(i < root.n && root.keys[i] == data){
            return true;
        }
        if(root.isLeaf){
            return false;
        }
        return search(root.child[i],data);
    }
    
    private SplitResult insert(BTreeNode root,int data){
        if(root.isLeaf){
            if(!root.isFull()){
                root.insertKey(data, null, null);
                return null;
            }else{
                SplitResult sr = splitNode(root,data,null,null);
                return sr;
            }
        }else{
            int i=0;
            for(; i < root.n; i++){
                if(data <= root.keys[i]){
                    SplitResult sr = insert(root.child[i],data);
                    if(sr == null){
                        return null;
                    }else{
                        if(!root.isFull()){
                            root.insertKey(sr.c, sr.r1, sr.r2);
                            return null;
                        }else{
                            SplitResult sr1 = splitNode(root,sr.c,sr.r1,sr.r2);
                            return sr1;
                        }
                    }
                }
            }
            if(i == root.n){
                SplitResult sr = insert(root.child[i],data);
                if(sr == null){
                    return null;
                }else{
                    if(!root.isFull()){
                        root.insertKey(sr.c, sr.r1, sr.r2);
                        return null;
                    }else{
                        SplitResult sr1 = splitNode(root,sr.c,sr.r1,sr.r2);
                        return sr1;
                    }
                }
            }
        }
        return null;
    }
    
    private SplitResult splitNode(BTreeNode node,int data, BTreeNode nr1, BTreeNode nr2){
        int c = node.keys[node.n/2];
        BTreeNode r1 = BTreeNode.newNode();
        BTreeNode r2 = BTreeNode.newNode();
        r1.n = node.n/2;
        r2.n = node.n - node.n/2-1;
        if(!node.isLeaf){
            r1.isLeaf = false;
            r2.isLeaf = false;
        }
        int i=0;
        for(; i < node.n/2; i++){
            r1.keys[i] = node.keys[i];
            r1.child[i] = node.child[i];
        }
        r1.child[i] = node.child[i];
        i = node.n/2 + 1;
        int j=0;
        for(;i < node.n; i++,j++){
            r2.keys[j] = node.keys[i];
            r2.child[j] = node.child[i];
        }
        r2.child[j] = node.child[i];
        if(data < c){
            r1.insertKey(data, nr1, nr2);
        }else{
            r2.insertKey(data, nr1, nr2);
        }
        SplitResult sr = new SplitResult();
        sr.c = c;
        sr.r1 = r1;
        sr.r2 = r2;
        return sr;
    }
    
    class SplitResult{
        BTreeNode r1;
        BTreeNode r2;
        int c;
    }
    
    public void traverse(){
        traverse(root);
    }
    
    private void traverse(BTreeNode root){
        for(int i=0; i < root.n; i++){
            if(!root.isLeaf){
                traverse(root.child[i]);
            }
            System.out.print(root.keys[i] + " ");
        }
        if(!root.isLeaf){
            traverse(root.child[root.n]);
        }
    }
    
    static class BTreeNode{
        int n ;
        BTreeNode[] child = new BTreeNode[2*T];
        int keys[] = new int[2*T-1];
        boolean isLeaf;
        
        public void insertKey(int data,BTreeNode r1,BTreeNode r2){
            int i = n-1;
            while(i >=0 && data < keys[i]){
                keys[i+1] = keys[i];
                i--;
            }
            keys[i+1] = data;
            int j = n;
            while(j > i+1){
                child[j+1] = child[j];
                j--;
            }
            child[j] = r1;
            child[j+1] = r2;
            n++;
        }
        
        public static BTreeNode newNode(int data){
            BTreeNode node = new BTreeNode();
            node.keys[0] = data;
            node.isLeaf = true;
            node.n = 1;
            return node;
        }
        
        public static BTreeNode newNode(){
            BTreeNode node = new BTreeNode();
            node.isLeaf = true;
            node.n = 0;
            return node;
        }
        
        public boolean isFull(){
            return 2*T - 1 == n;
        }
    }
    
    public static void main(String args[]){
        BTree bTree = new BTree();
        bTree.insert(5);
        bTree.insert(4);
        bTree.insert(3);
        bTree.insert(2);
        bTree.insert(1);
        bTree.insert(6);
        bTree.insert(11);
        bTree.insert(13);
        bTree.insert(8);
        bTree.insert(7);
        bTree.insert(10);
        bTree.insert(9);
        bTree.insert(28);
        bTree.insert(22);
        bTree.insert(12);
        bTree.insert(18);
        bTree.insert(16);
        bTree.traverse();
        System.out.print(bTree.search(28));
        System.out.print(bTree.search(11));
        System.out.print(bTree.search(5));
        System.out.print(bTree.search(21));
        System.out.print(bTree.search(3));
        System.out.print(bTree.search(4));
        System.out.print(bTree.search(14));
    }
}

