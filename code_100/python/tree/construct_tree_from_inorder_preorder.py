from binary_tree import Node


class ConstructTreeFromInorderPreOrder:
    def __init__(self):
        self.index = 0

    def _createTree(self, inorder, preorder, start, end):
        if start > end:
            return None
        i = 0
        for i in range(start, end + 1):
            if preorder[self.index] == inorder[i]:
                break

        node = Node.newNode(preorder[self.index])
        self.index += 1
        node.left = self._createTree(inorder, preorder, start, i - 1)
        node.right = self._createTree(inorder, preorder, i + 1, end)
        return node

    def createTree(self, inorder, preorder):
        return self._createTree(inorder, preorder, 0, len(inorder) - 1)
