# detect cycle in directed graph
# https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/CycleInDirectedGraph.java

from graph import *

def has_cycle(graph):
    white = set()
    gray = set()
    black = set()

    for vertex in graph.all_vertex.values():
        white.add(vertex)

    while len(white) > 0:
        current = next(iter(white))       
        if dfs(current, white, gray, black) == True:
            return True

    return False        

def dfs(current, white, gray, black):
    move_vertex(current, white, gray)
    for neighbor in current.adjacent_vertices:
        if neighbor in black:
            continue
        if neighbor in gray:
            return True
        if dfs(neighbor, white, gray, black) == True:
            return True
        
    move_vertex(current, gray, black)
    return False

def move_vertex(vertex, source_set, destination_set):
    source_set.remove(vertex)
    destination_set.add(vertex)
        
if __name__ == '__main__':
    graph = Graph(True)
    graph.add_edge(1,2)
    graph.add_edge(1,3)
    graph.add_edge(2,3)
    graph.add_edge(4,1)
    graph.add_edge(4,5)
    graph.add_edge(5,6)
    graph.add_edge(6,4)

    print(has_cycle(graph));
     
