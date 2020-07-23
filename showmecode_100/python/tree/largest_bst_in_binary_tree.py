""" Given a binary tree, find size of largest binary search subtree in this binary tree.

Approach
--------

Traverse tree in post order fashion. Left and right nodes return 4 piece of information to root which isBST, size of max
BST, min and max in those subtree.

If both left and right subtree are BST and this node data is greater than max of left and less than min of right then it
returns to above level left size + right size + 1 and new min will be min of left side and new max will be max of right
side.

Video link
----------
* https://youtu.be/4fiDs7CCxkc

References
----------
* http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
* https://leetcode.com/problems/largest-bst-subtree/
* http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
"""

from construct_tree_from_inorder_preorder import ConstructTreeFromInorderPreOrder

class MinMax:

    def __init__(self):
        self.min = float("inf")
        self.max = float("-inf")
        self.isBST = True
        self.size = 0


class LargestBSTBinaryTree:

    def largestBST(self, root):
        m = self.largest(root)
        return m.size

    def largest(self, root):

        if root is None:
            return MinMax()

        leftMinMax = self.largest(root.left)
        rightMinMax = self.largest(root.right)

        m = MinMax()

        if ((leftMinMax.isBST == False or rightMinMax.isBST == False)
            or (leftMinMax.max > root.data  or rightMinMax.min <= root.data)):

            m.isBST = False
            m.size = max(leftMinMax.size, rightMinMax.size)
            return m

        m.isBST = True
        m.size = leftMinMax.size + rightMinMax.size + 1
        m.min = leftMinMax.min if root.left is not None else root.data
        m.max = rightMinMax.max if root.right is not None else root.data
        return m


if __name__ == '__main__':
    lbi = LargestBSTBinaryTree()
    ctf = ConstructTreeFromInorderPreOrder()
    inorder  = [-7, -6, -5, -4, -3, -2, 1, 2, 3, 16, 6, 10, 11, 12, 14]
    preorder = [3, -2, -3, -4, -5, -6, -7, 1, 2, 16, 10, 6, 12, 11, 14]
    root = ctf.createTree(inorder, preorder)
    largestBSTSize = lbi.largestBST(root)
    print "Size of the largest BST in the Binary Tree is ", largestBSTSize
    assert 8 == lbi.largestBST(root)
