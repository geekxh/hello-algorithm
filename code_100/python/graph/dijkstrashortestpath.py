#dijkstra's algorithm

# java code https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/DijkstraShortestPath.java

from priorityqueue import PriorityQueue
from graph import Graph
import sys


def shortest_path(graph, sourceVertex):

    min_heap = PriorityQueue(True)
    distance = {}
    parent = {}

    for vertex in graph.all_vertex.values():
        min_heap.add_task(sys.maxsize, vertex)

    min_heap.change_task_priority(0, sourceVertex)
    distance[sourceVertex] = 0
    parent[sourceVertex] = None

    while min_heap.is_empty() is False:
        task = min_heap.peek_task()
        weight = min_heap.get_task_priority(task)               
        current =  min_heap.pop_task()
        distance[current] = weight

        for edge in current.edges:
            adjacent = get_other_vertex_for_edge(current, edge)
            if min_heap.contains_task(adjacent) is False:
                continue

            new_distance = distance[current] + edge.weight;
            if min_heap.get_task_priority(adjacent) > new_distance:
                min_heap.change_task_priority(new_distance, adjacent)
                parent[adjacent] = current
                

    return distance


def get_other_vertex_for_edge(vertex, edge):
    if edge.vertex1.id == vertex.id:
        return edge.vertex2
    else:
        return edge.vertex1


if __name__ == '__main__':
    graph = Graph(False)
    graph.add_edge(1,2,5)
    graph.add_edge(2,3,2)
    graph.add_edge(1,4,9)
    graph.add_edge(1,5,3)
    graph.add_edge(5,6,2)
    graph.add_edge(6,4,2)
    graph.add_edge(3,4,3)
    
    distance = shortest_path(graph, graph.all_vertex[1])
    print(distance)
