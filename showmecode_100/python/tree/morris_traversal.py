"""Morris Traversal of a Binary Tree.

Video
-----

* https://youtu.be/wGXB9OWhPTg

Analysis
--------

* Time complexity O(n)
* Space complexity O(1)

"""

from binary_tree import BinaryTree


class MorrisTraversal:

    def __init__(self):
        pass

    @staticmethod
    def find_predecessor(current):
        predecessor = current.left
        while predecessor.right != current and predecessor.right is not None:
            predecessor = predecessor.right
        return predecessor

    @staticmethod
    def inorder(root_node):
        current = root_node
        while current is not None:
            if current.left is None:
                print "{data} ".format(data=current.data),
                current = current.right
            else:
                predecessor = MorrisTraversal.find_predecessor(current)

                if predecessor.right is None:
                    predecessor.right = current
                    current = current.left
                else:
                    predecessor.right = None
                    print "{data} ".format(data=current.data),
                    current = current.right

    @staticmethod
    def preorder(root_node):
        current = root_node
        while current is not None:
            if current.left is None:
                print "{data} ".format(data=current.data),
                current = current.right
            else:
                predecessor = MorrisTraversal.find_predecessor(current)

                if predecessor.right is None:
                    print "{data} ".format(data=current.data),
                    predecessor.right = current
                    current = current.left
                else:
                    predecessor.right = None
                    current = current.right


if __name__ == '__main__':
    bt = BinaryTree()
    root = None
    root = bt.add_head(10, root)
    root = bt.add_head(50, root)
    root = bt.add_head(-10, root)
    root = bt.add_head(7, root)
    root = bt.add_head(9, root)
    root = bt.add_head(-20, root)
    root = bt.add_head(30, root)

    mt = MorrisTraversal()
    mt.inorder(root)
    print "\n",
    mt.preorder(root)
