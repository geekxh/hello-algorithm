"""
Problem Statement
=================

Given a binary tree, write a program to find the maximum depth at any given node.

For e.g, for this binary tree.

  1
 / \
2   3
   / \
  4   5

The height at 1 is 3, and the height at 3 is 2.

"""

class Node:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None


n1 = Node(1)
n2 = Node(2)
n3 = Node(3)
n4 = Node(4)
n5 = Node(5)

# construct the tree as given in the problem.

n1.left = n2
n1.right = n3
n3.left = n4
n3.right = n5


def find_max_depth(n):
    if n is None:
        return 0
    left_height = find_max_depth(n.left)
    right_height = find_max_depth(n.right)
    if left_height > right_height:
        result = left_height + 1
    else:
        result = right_height + 1
    return result


if __name__ == '__main__':
    assert 3 == find_max_depth(n1)
    assert 2 == find_max_depth(n3)
