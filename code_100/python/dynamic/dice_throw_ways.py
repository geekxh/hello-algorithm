"""
Problem Statement
=================

Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X. X is the summation of values
on each face when all the dice are thrown.

Complexity
----------

* Run time complexity: O(m * n * x) where m is number of faces, n is number of dice and x is given sum.


References
----------

* http://www.geeksforgeeks.org/dice-throw-problem/
"""


def num_ways(faces, dices, sumX):

    T = [[0 for _ in range(sumX + 1)] for _ in range(dices + 1)]

    # For a single dice
    for face_value in range(1, faces + 1):
        if face_value <= sumX:
            T[1][face_value] = 1

    for dice in range(2, dices + 1):
        for partial_sum in range(1, sumX + 1):
            for face_value in range(1, faces + 1):
                if face_value < partial_sum:
                    T[dice][partial_sum] += T[dice - 1][partial_sum - face_value]

    return T[dices][sumX]


if __name__ == '__main__':
    assert 7 == num_ways(3, 3, 6)
