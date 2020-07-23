# floyd warshall all pair shortest path
# java code https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/FloydWarshallAllPairShortestPath.java
import sys

INF = 1000000

class NegativeWeightCycleException(Exception):
    def __init__(self):
        pass

def all_pair_shortest_path(distance_matrix):

    size = len(distance_matrix)
    distance = [[0 for x in range(size)]
                for x in range (size)]
    
    path = [[0 for x in range(size)]
                for x in range (size)]

    for i in range(size):
        for j in range(size):
            distance[i][j] = distance_matrix[i][j]
            if distance_matrix[i][j] != INF and i != j:
                path[i][j] = i
            else:
                path[i][j] = -1


    for k in range(size):
        for i in range(size):
            for j in range(size):
                if distance[i][k] == INF or distance[k][j] == INF:
                    continue
                if distance[i][j] > distance[i][k] + distance[k][j]:
                    distance[i][j] = distance[i][k] + distance[k][j]
                    path[i][j] = path[k][j]

    for i in range(size):
        if distance[i][i] < 0:
            raise NegativeWeightCycleException()

    print_path(path, 3, 2)
    return (distance, path)

def print_path(path, start, end):
    stack = []
    stack.append(end)
    while True:
        end = path[start][end]
        if end == -1:
            return
        stack.append(end)
        if end == start:
            break

    print(stack[::-1])

if __name__ == '__main__':
    
    distance_matrix = [[0, 3, 6, 15],
             [INF, 0, -2, INF],
             [INF, INF, 0, 2],
             [1, INF, INF, 0]]

    distance, path = all_pair_shortest_path(distance_matrix)

    print(distance)
    #print(path)
    
    
    
    
