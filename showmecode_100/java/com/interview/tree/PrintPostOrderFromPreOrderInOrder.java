package com.interview.tree;

/**
 * Date 11/02/2015
 * @author Tushar Roy
 *
 * Given a preorder/inorder traversal of binary tree create postorder traversal of binary tree
 * without creating the tree
 *
 * Time complexity - O(n)
 *
 * References
 * http://www.geeksforgeeks.org/print-postorder-from-given-inorder-and-preorder-traversals/
 */

import java.util.concurrent.atomic.AtomicInteger;

public class PrintPostOrderFromPreOrderInOrder {

    public int[] postOrder(int[] preorder, int[] inorder) {
        int[] post = new int[inorder.length];
        AtomicInteger postIndex = new AtomicInteger(post.length - 1);
        postOrder(preorder, inorder, post, 0, inorder.length -1, 0, postIndex);
        return post;
    }

    private void postOrder(int[] preorder, int[] inorder, int post[], int low, int high, int preIndex, AtomicInteger postIndex) {
        if(low > high) {
            return;
        }
        post[postIndex.getAndDecrement()] = preorder[preIndex];
        int i;
        for(i=0; i < inorder.length; i++) {
            if(preorder[preIndex] == inorder[i]) {
                break;
            }
        }
        postOrder(preorder, inorder, post, i+1, high, preIndex + (i - low) + 1, postIndex);
        postOrder(preorder, inorder, post, low, i-1, preIndex + 1, postIndex);
    }

    public static void main(String args[]) {
        int preorder[] = {10, 5, 3, 21, 20, 18, 9 , 16};
        int inorder[] = {3, 5, 21, 10, 18, 9, 20, 16};
        PrintPostOrderFromPreOrderInOrder ppp = new PrintPostOrderFromPreOrderInOrder();
        int postorder[] = ppp.postOrder(preorder, inorder);
        for(int i : postorder) {
            System.out.print(i + " ");
        }
    }
}
