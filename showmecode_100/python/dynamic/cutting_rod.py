"""
Problem Statement
=================

Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
Determine the maximum value obtainable by cutting up the rod and selling the pieces.

Video
-----

* https://youtu.be/IRwVmTmN6go


Time Complexity
---------------

1. Recursive Solution = O(2^n)
2. Dynamic Programming Solution = O(n^2)

Reference
---------
http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
"""


def max_profit_dp(prices, rod_length):
    rod_length_values = [0 for _ in range(rod_length + 1)]

    for length in range(1, rod_length + 1):

        max_value = float("-inf")

        for cut_length in range(1, length + 1):
            max_value = max(max_value, prices[cut_length - 1] + rod_length_values[length - cut_length])

        rod_length_values[length] = max_value

    return rod_length_values[rod_length]


def max_profit_recursive(prices, rod_length):
    if rod_length == 0:
        return 0

    max_price = float('-inf')

    for length in range(1, rod_length + 1):
        max_price = max(max_price, prices[length - 1] + max_profit_recursive(prices, rod_length - length))

    return max_price


if __name__ == '__main__':
    prices = [3,5,8,9,10,20,22,25]
    rod_length = 8
    expected_max_profit = 26
    assert expected_max_profit == max_profit_recursive(prices, rod_length)
    assert expected_max_profit == max_profit_dp(prices, rod_length)
