from collections import namedtuple

Color = namedtuple("Color", "RED BLACK")


class Node:
    def __init__(self):
        self.color = None
        self.height = None
        self.lis = None
        self.data = None
        self.size = None
        self.next = None
        self.right = None
        self.left = None

    @staticmethod
    def newNode(data):
        n = Node()
        n.data = data
        n.lis = -1
        n.height = 1
        n.size = 1
        n.color = Color.RED
        return n


class BinaryTree:

    def __init__(self):
        pass

    @staticmethod
    def add_head(data, head):
        temp_head = head
        n = Node.newNode(data)

        if head is None:
            head = n
            return head

        prev = None

        while head is not None:
            prev = head
            if head.data < data:
                head = head.right
            else:
                head = head.left

        if prev.data < data:
            prev.right = n
        else:
            prev.left = n

        return temp_head
