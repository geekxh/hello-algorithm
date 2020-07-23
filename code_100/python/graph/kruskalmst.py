# kruskal minimum spanning tree
# java code https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/KruskalMST.java

from disjointset import *
from graph import *

def get_key(edge):
    return edge.weight

def minimum_spanning_tree(graph):

    disjoint_set = DisjointSet()
    sorted_edges = sorted(graph.all_edges, key = get_key)
    print(sorted_edges)

    for vertex in graph.all_vertex.values():
        disjoint_set.make_set(vertex.id)

    result_edge = []

    for edge in sorted_edges:
        root1 = disjoint_set.find_set(edge.vertex1.id)
        root2 = disjoint_set.find_set(edge.vertex2.id)

        if root1 == root2:
            continue
        else:
            result_edge.append(edge)
            disjoint_set.union(edge.vertex1.id, edge.vertex2.id)

    return result_edge

if __name__ == '__main__':
    graph = Graph(False)
    graph.add_edge(1,3,1)
    graph.add_edge(1,2,4)
    graph.add_edge(2,4,2)
    graph.add_edge(2,5,1)
    graph.add_edge(2,6,3)
    graph.add_edge(3,4,5)
    graph.add_edge(3,7,8)
    graph.add_edge(4,7,2)
    graph.add_edge(6,5,2)
    graph.add_edge(6,4,3)

    result = minimum_spanning_tree(graph)
    for edge in result:
        print(edge)
    
