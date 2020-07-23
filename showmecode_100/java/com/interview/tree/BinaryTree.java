package com.interview.tree;

/**
 * Date 07/07/2014 
 * @author tusroy
 * 
 * Youtube link - https://youtu.be/bmaeYtlO2OE
 * Youtube link - https://youtu.be/_SiwrPXG9-g
 * Youtube link - https://youtu.be/NA8B84DZYSA
 *
 */
class NodeRef{
    Node node;
}

enum Color{
    RED,
    BLACK
}

class Node{
    Node left;
    Node right;
    Node next;
    int data;
    int lis;
    int height;
    int size;
    Color color;
    
    public static Node newNode(int data){
        Node n = new Node();
        n.left = null;
        n.right = null;
        n.data = data;
        n.lis = -1;
        n.height = 1;
        n.size = 1;
        n.color = Color.RED;
        return n;
    }
}

public class BinaryTree {
    public Node addNode(int data, Node head){
        Node tempHead = head;
        Node n = Node.newNode(data);
        if(head == null){
            head = n;
            return head;
        }
        Node prev = null;
        while(head != null){
            prev = head;
            if(head.data < data){
                head = head.right;
            }else{
                head = head.left;
            }
        }
        if(prev.data < data){
            prev.right = n;
        }else{
            prev.left = n;
        }
        return tempHead;
    }
    
    class IntegerRef{
        int height;
    }
    
    public int height(Node root){
        if(root == null){
            return 0;
        }
        int leftHeight  = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(19, head);
        head = bt.addNode(20, head);
        head = bt.addNode(-1, head);
        head = bt.addNode(21, head);
        System.out.println(bt.height(head));
        
    }
}
