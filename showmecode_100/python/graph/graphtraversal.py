#doing BFS and DFS traversal of the graph
# java code https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/GraphTraversal.java

from graph import *
import queue

def dfs_util(v, visited):
    if v in visited:
        return
    visited.add(v)
    print(v)
    for vertex in v.adjacent_vertices:
        dfs_util(vertex, visited)
        
def dfs(graph):
    visited = set()
    for id in graph.all_vertex:
        dfs_util(graph.all_vertex[id], visited)

def bfs(graph):
    q = queue.Queue()
    visited = set()
    for vertex in graph.all_vertex.values():
        if vertex not in visited:
            q.put(vertex)
            visited.add(vertex)
            while not q.empty():
                v = q.get();
                print(v)
                for adj in v.adjacent_vertices:
                    if adj not in visited:
                        q.put(adj)
                        visited.add(adj)
                    
                
if __name__ == '__main__':   
    g = Graph(False)
    g.add_edge(1,2,10)
    g.add_edge(2,3,5)
    g.add_edge(1,4,6)

    dfs(g)
    bfs(g)
