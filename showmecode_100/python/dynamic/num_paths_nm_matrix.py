"""
Problem Statement
=================

Count the number of Paths from 1,1 to N,M in an NxM matrix.

Analysis
--------

* Dynamic Programing Solution: O(rows * cols)
* Recursive: O(2^rows) if rows > cols else O(2^cols)


References
----------
* http://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/

"""


def num_paths_matrix(rows, cols):
    T = [[1 if row == 0 or col == 0 else 0 for row in range(cols)] for col in range(rows)]
    for row in range(1, rows):
        for col in range(1, cols):
            T[row][col] = T[row - 1][col] + T[row][col - 1]
    return T[rows - 1][cols - 1]


def num_paths_matrix_recursive(rows, cols):
    if rows == 1 or cols == 1:
        return 1
    return num_paths_matrix(rows-1, cols) + num_paths_matrix(rows, cols - 1)


if __name__ == '__main__':
    rows = 3
    cols = 3
    expected = 6
    assert expected == num_paths_matrix(rows, cols)
    assert expected == num_paths_matrix_recursive(rows, cols)
