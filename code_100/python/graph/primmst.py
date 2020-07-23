#Prim's MST
# Java code https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/PrimMST.java

from graph import Graph
from priorityqueue import PriorityQueue
import sys

def minimum_spanning_tree(graph):

    min_heap = PriorityQueue(True)
    vertex_to_edge = {}
    result = []

    for vertex in graph.all_vertex.values():
        min_heap.add_task(sys.maxsize, vertex)
    
    start_vertex = next(iter((graph.all_vertex.values())))
    min_heap.change_task_priority(0, start_vertex)
    while min_heap.is_empty() is False:
        current = min_heap.pop_task()
        
        if(current in vertex_to_edge):
            spanning_tree_edge = vertex_to_edge[current]
            result.append(spanning_tree_edge)

        for edge in current.edges:
            adjacent = get_other_vertex_for_edge(current, edge)
            if min_heap.contains_task(adjacent) is True and min_heap.get_task_priority(adjacent) > edge.weight:
                   min_heap.change_task_priority(edge.weight, adjacent)
                   vertex_to_edge[adjacent] = edge
                   
    return result


def get_other_vertex_for_edge(vertex, edge):
    if edge.vertex1.id == vertex.id:
        return edge.vertex2
    else:
        return edge.vertex1

if __name__ == '__main__':
    graph = Graph(False)
    graph.add_edge(1,2,3)
    graph.add_edge(2,3,1)
    graph.add_edge(3,1,1)
    graph.add_edge(1,4,1)
    graph.add_edge(2,4,3)
    graph.add_edge(4,5,6)
    graph.add_edge(5,6,2)
    graph.add_edge(3,5,5)
    graph.add_edge(3,6,4)
    
    result = minimum_spanning_tree(graph)
    print(result)    
