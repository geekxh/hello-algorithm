# disjoint sets
# https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/DisjointSet.java

class Node(object):

    def __init__(self, data, parent = None, rank = 0):
        self.data = data
        self.parent = parent
        self.rank = rank

    def __str__(self):
        return str(self.data)

    def __repr__(self):
        return self.__str__()


class DisjointSet(object):

    def __init__(self):
        self.map = {}

    def make_set(self, data):
       node = Node(data)
       node.parent = node
       self.map[data] = node

    def union(self, data1, data2):
        node1 = self.map[data1]
        node2 = self.map[data2]

        parent1 = self.find_set_util(node1)
        parent2 = self.find_set_util(node2)

        if parent1.data == parent2.data:
            return

        if parent1.rank >= parent2.rank:
            if parent1.rank == parent2.rank:
                parent1.rank = parent1.rank + 1
            parent2.parent = parent1
        else:
            parent1.parent = parent2

    def find_set(self, data):
        return self.find_set_util(self.map[data])

    def find_set_util(self, node):
        parent = node.parent
        if parent == node:
            return parent
        node.parent = self.find_set_util(node.parent)
        return node.parent

if __name__ == '__main__':
    ds = DisjointSet()
    ds.make_set(1)
    ds.make_set(2)
    ds.make_set(3)
    ds.make_set(4)
    ds.make_set(5)
    ds.make_set(6)
    ds.make_set(7)

    ds.union(1,2)
    ds.union(2,3)
    ds.union(4,5)
    ds.union(6,7)
    ds.union(5,6)
    ds.union(3,7)

    for i in range(1,8):
        print(ds.find_set(i))
    
    
        
         
