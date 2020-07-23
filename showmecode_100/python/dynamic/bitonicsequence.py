"""
Problem Statement
=================

Find the length of the longest Bitonic Sequence in a given sequence of numbers. A Bitonic sequence is a sequence of
numbers which are increasing and then decreasing.

Video
-----
* https://youtu.be/TWHytKnOPaQ

Analysis
--------
* Runtime O(n)

Reference
---------
* http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
"""


def longest_bitonic(sequence):
    length_of_input = len(sequence)
    increasing_sequence = [1] * length_of_input
    decreasing_sequence = [1] * length_of_input

    for i in range(1, length_of_input):
        for j in range(0, i):
            if sequence[i] > sequence[j]:
                increasing_sequence[i] = max(increasing_sequence[i], increasing_sequence[j] + 1)

    for i in range(length_of_input - 2, -1, -1):
        for j in range(length_of_input - 1, i, -1):
            if sequence[i] > sequence[j]:
                decreasing_sequence[i] = max(decreasing_sequence[i], decreasing_sequence[j] + 1)

    max_value = 0

    for i in range(len(sequence)):
        bitonic_sequence_length = increasing_sequence[i] + decreasing_sequence[i] - 1
        max_value = max(max_value, bitonic_sequence_length)

    return max_value


if __name__ == '__main__':
    max_value = longest_bitonic([1, 4, 3, 7, 2, 1, 8, 11, 13, 0])
    assert 7 == max_value  # 1, 4, 7, 8, 11, 13, 0
