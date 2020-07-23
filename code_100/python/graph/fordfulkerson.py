#ford fulkerson method Edomonds Karp algorithm for finding max flow
# java code https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/FordFulkerson.java
from queue import Queue
import sys

def max_flow(capacity, source, sink):
    residual_capacity = [x[:] for x in capacity]
    augmented_paths = []
    max_flow = 0
    while True:
        found_augmented_path, parent = bfs(residual_capacity, source, sink)
        if not found_augmented_path:
            break
        augmented_path = []
        v = sink
        flow = sys.maxsize
        while not v == source:
            augmented_path.append(v)
            u = parent[v]
            if flow > residual_capacity[u][v]:
                flow = residual_capacity[u][v]
            v = u
        augmented_path.append(source)
        augmented_paths.append(augmented_path[::-1])
        max_flow += flow

        v = sink
        while not v == source:
             u = parent[v]
             residual_capacity[u][v] -= flow
             residual_capacity[v][u] += flow
             v = u

    print("Augmented path")
    print(augmented_paths)
    return max_flow

def bfs(residual_capacity, source, sink):
    visited = set()
    queue = Queue()
    parent = {}
    queue.put(source)
    visited.add(source)
    found_augmented_path = False
    while not queue.empty():
        u = queue.get()
        for v in range(len(residual_capacity)):
            if v not in visited and residual_capacity[u][v] > 0:
                parent[v] = u
                visited.add(v)
                queue.put(v)
                if v == sink:
                    found_augmented_path = True
                    break;
    return found_augmented_path, parent                               

if __name__ == '__main__':
    capacity = [[0, 3, 0, 3, 0, 0, 0],
                [0, 0, 4, 0, 0, 0, 0],
                [3, 0, 0, 1, 2, 0, 0],
                [0, 0, 0, 0, 2, 6, 0],
                [0, 1, 0, 0, 0, 0, 1],
                [0, 0, 0, 0, 0, 0, 9],
                [0, 0, 0, 0, 0, 0, 0]]

    max_val = max_flow(capacity, 0, 6)
    print(max_val)
