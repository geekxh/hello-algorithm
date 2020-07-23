#topological sort
# java code https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/TopologicalSort.java

from graph import Graph

def top_sort(graph):
    stack = []
    visited = set()
    for vertex in graph.all_vertex.values():
        if vertex in visited:
            continue
        top_sort_util(vertex, stack, visited)
    return stack

def top_sort_util(vertex, stack, visited):
    visited.add(vertex)
    for adjacent in vertex.adjacent_vertices:
        if adjacent in visited:
            continue
        top_sort_util(adjacent, stack, visited)
    stack.append(vertex)

if __name__ == '__main__':
    graph = Graph(True)
    graph.add_edge(1,3)
    graph.add_edge(1,2)
    graph.add_edge(3,4)
    graph.add_edge(5,6)
    graph.add_edge(6,3)
    graph.add_edge(3,8)
    graph.add_edge(8,11)

    stack = top_sort(graph)

    print(stack[::-1])

     
            
