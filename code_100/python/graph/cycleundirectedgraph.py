# detect cycle in undirected graph
# https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/CycleUndirectedGraph.java

from graph import *
from disjointset import *

def has_cycle_dfs(graph):
    visited = set()
    for vertex in graph.all_vertex.values():
        if vertex in visited:
            continue
        flag = has_cycle_dfs_util(vertex, visited, None)
        if flag:
            return True
    return False        

def has_cycle_dfs_util(vertex, visited, parent):
    visited.add(vertex)
    for adjacent in vertex.adjacent_vertices:
        if parent is not None and adjacent == parent:
            continue
        if adjacent in visited:
            return True
        has_cycle = has_cycle_dfs_util(adjacent, visited, vertex)
        if has_cycle:
            return True
    return False

def has_cycle_using_disjoint_set(graph):
    disjoint_set = DisjointSet()

    for vertex in graph.all_vertex.values():
        disjoint_set.make_set(vertex.id)

    for edge in graph.all_edges:
        parent1 = disjoint_set.find_set(edge.vertex1.id)
        parent2 = disjoint_set.find_set(edge.vertex2.id)
        if parent1 == parent2:
            return True
        disjoint_set.union(edge.vertex1.id, edge.vertex2.id)

    return False


if __name__ == '__main__':
    graph = Graph(False)
    graph.add_edge(0,1)
    graph.add_edge(1,2)
    graph.add_edge(0,3)
    graph.add_edge(3,4)
    graph.add_edge(4,5)
    graph.add_edge(5,1)

    has_cycle1 = has_cycle_dfs(graph)
    has_cycle2 = has_cycle_using_disjoint_set(graph)
    print(str(has_cycle1) + " " + str(has_cycle2))
        
