"""
Problem Statement
=================

0/1 Knapsack Problem - Given items of certain weights/values and maximum allowed weight how to pick items to pick items
from this set to maximize sum of value of items such that sum of weights is less than or equal to maximum allowed
weight.

Runtime Analysis
----------------
Time complexity - O(W*total items)

Video
-----
* Topdown DP - https://youtu.be/149WSzQ4E1g
* Bottomup DP - https://youtu.be/8LusJS5-AGo

References
----------
* http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
* https://en.wikipedia.org/wiki/Knapsack_problem
"""


def knapsack_01(values, weights, total):
    total_items = len(weights)

    rows = total_items + 1
    cols = total + 1

    T = [[0 for _ in range(cols)] for _ in range(rows)]

    for i in range(1, rows):
        for j in range(1, cols):
            if j < weights[i - 1]:
                T[i][j] = T[i - 1][j]
            else:
                T[i][j] = max(T[i - 1][j], values[i - 1] + T[i - 1][j - weights[i - 1]])

    return T[rows - 1][cols -1]


def knapsack_01_recursive_util(values, weights, remaining_weight, total_items, current_item, memo):
    if current_item >= total_items or remaining_weight <= 0:
        return 0

    key = (total_items - current_item - 1, remaining_weight)

    if key in memo:
        return memo[key]

    if remaining_weight < weights[current_item]:
        max_value = knapsack_01_recursive_util(values, weights, remaining_weight, total_items, current_item + 1, memo)
    else:
        max_value = max(values[current_item] + knapsack_01_recursive_util(values, weights, remaining_weight - weights[current_item], total_items, current_item + 1, memo),
                        knapsack_01_recursive_util(values, weights, remaining_weight, total_items, current_item + 1, memo))

    memo[key] = max_value
    return max_value


def knapsack_01_recursive(values, weights, total_weight):
    memo = dict()
    return knapsack_01_recursive_util(values, weights, total_weight, len(values), 0, memo)


if __name__ == '__main__':
    total_weight = 7
    weights = [1, 3, 4, 5]
    values = [1, 4, 5, 7]
    expected = 9
    assert expected == knapsack_01(values, weights, total_weight)
    assert expected == knapsack_01_recursive(values, weights, total_weight)
    total_weight = 8
    weights = [2, 2, 4, 5]
    values = [2, 4, 6, 9]
    expected = 13
    assert expected == knapsack_01(values, weights, total_weight)
    assert expected == knapsack_01_recursive(values, weights, total_weight)
