"""
Problem Statement
=================

Given an array p[] which represents the chain of matrices such that the ith matrix Ai is of dimension p[i-1] x p[i]. We
need to write a function matrix_chain_order() that should return the minimum number of multiplications needed to
multiply the chain.

Video
-----
* https://youtu.be/vgLJZMUfnsU

Note
----

In the code below we give matrices length as an array and each matrix takes 2 indices from the array.
For e.g. {2, 3, 4} represents two matrices (2, 3) and (3, 4) in (row, col) format.


Complexity
----------

Time Complexity: O(n^3)

Reference
---------

* http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
"""


def matrix_chain_order(matrices):
    matrices_length = len(matrices)

    T = [[0 for _ in range(matrices_length)] for _ in range(matrices_length)]

    for gap in range(2, matrices_length):
        for index_i in range(0, matrices_length - gap):
            index_j = index_i + gap
            T[index_i][index_j] = 10000
            for index_k in range(index_i + 1, index_j):
                temp = T[index_i][index_k] + T[index_k][index_j] + matrices[index_i] * matrices[index_k] * matrices[index_j]
                if temp < T[index_i][index_j]:
                    T[index_i][index_j] = temp

    return T[0][-1]


if __name__ == '__main__':
    matrices = [4, 2, 3, 5, 3]
    assert 84 == matrix_chain_order(matrices)
