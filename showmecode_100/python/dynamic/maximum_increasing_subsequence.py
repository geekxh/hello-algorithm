"""
Problem Statement
=================

Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence of the given array
such that the integers in the subsequence are in increasing order.

Complexity
----------

* Time Complexity: O(n^2)
* Space Complexity: O(n)

Video
-----

* https://youtu.be/99ssGWhLPUE

Reference
---------
* http://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing-subsequence/
"""


def maximum_sum_subsequence(sequence):
    sequence_length = len(sequence)
    T = [sequence[i] for i in range(sequence_length)]

    for index_i in range(1, sequence_length):
        for index_j in range(0, index_i):
            if sequence[index_j] < sequence[index_i]:
                T[index_i] = max(T[index_i], T[index_j] + sequence[index_i])

    return max(T)

if __name__ == '__main__':
    sequence = [1, 101, 10, 2, 3, 100, 4]
    assert 111 == maximum_sum_subsequence(sequence)
