"""
Problem Statement
=================

Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up to given
total. Another variation is given an array is it possible to split it up into 2 equal sum partitions. Partition need not
be equal sized. Just equal sum.

Video
-----

* https://youtu.be/s6FhG--P7z0

Solution
--------

* Time complexity is O(input.size * total_sum)
* Space complexity is O(input.size*total_sum)

Reference
---------

* https://en.wikipedia.org/wiki/Subset_sum_problem
"""


def subset_sum(sequence, sum_value):
    cols = sum_value + 1         # Plus 1 for 0 valued col.
    rows = len(sequence) + 1     # Plus 1 for 0 valued row.
    T = [[False for _ in range(cols)] for _ in range(rows)]

    for row in range(rows):
        T[row][0] = True

    for index_i in range(1, rows):
        for index_j in range(1, cols):
            if index_j >= sequence[index_i - 1]:
                T[index_i][index_j] = T[index_i - 1][index_j] or T[index_i - 1][index_j - sequence[index_i - 1]]
            else:
                T[index_i][index_j] = T[index_i - 1][index_j]

    return T[rows - 1][cols - 1]


def partition(sequence):
    sequence_sum = sum(sequence)
    if sequence_sum % 2 != 0:
        return False

    expected = sequence_sum / 2

    return subset_sum(sequence, expected)


if __name__ == '__main__':
    sequence = [2, 3, 7, 8]
    assert True == subset_sum(sequence, 11)

    sequence = [1, 3, 5, 5, 2, 1, 1, 6]
    assert True == partition(sequence)
