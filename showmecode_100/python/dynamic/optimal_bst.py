"""
Problem Statement
=================

Given a sorted array keys[0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts, where freq[i] is the
number of searches to keys[i]. Construct a binary search tree of all keys such that the total cost of all the searches
is as small as possible.

Video
-----
* https://youtu.be/hgA4xxlVvfQ

Analysis
--------
* Recursive: Exponential O(n^n)
* Dynamic Programming: O(n^3)

Reference
---------
* http://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/
"""


def min_cost_bst(input_array, freq):
    size = rows = cols = len(input_array)
    T = [[0 for _ in range(cols)] for _ in range(rows)]

    for idx in range(rows):
        T[idx][idx] = freq[idx]

    for sub_tree_size in range(2, size + 1):
        for start in range(size + 1 - sub_tree_size):
            end = start + sub_tree_size - 1
            T[start][end] = float("inf")
            total = sum(freq[start:end + 1])
            for k in range(start, end + 1):
                val = total + (0 if k - 1 < 0 else T[start][k - 1]) + (0 if k + 1 > end else T[k + 1][end])
                T[start][end] = min(val, T[start][end])

    return T[0][-1]


def min_cost_bst_recursive_helper(input_array, freq, low_index, high_index, level):
    if low_index > high_index:
        return 0
    min_value = float("inf")

    for index in range(low_index, high_index + 1):
        val = (min_cost_bst_recursive_helper(input_array, freq, low_index, index - 1, level + 1)        # left tree
               + level * freq[index]                                                                    # value at level
               + min_cost_bst_recursive_helper(input_array, freq, index + 1, high_index, level + 1))    # right tree
        min_value = min(val, min_value)

    return min_value


def min_cost_bst_recursive(input_array, freq):
    return min_cost_bst_recursive_helper(input_array, freq, 0, len(input_array) - 1, 1)


if __name__ == '__main__':
    input_array = [10, 12, 16, 21]
    freq = [4, 2, 6, 3]
    expected = 26
    assert expected == min_cost_bst(input_array, freq)
    assert expected == min_cost_bst_recursive(input_array, freq)
    input_array = [10, 12, 20, 35, 46]
    freq = [34, 8, 50, 21, 16]
    expected = 232
    assert expected == min_cost_bst(input_array, freq)
    assert expected == min_cost_bst_recursive(input_array, freq)
