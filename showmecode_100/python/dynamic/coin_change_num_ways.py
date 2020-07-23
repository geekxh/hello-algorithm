"""
Problem Statement
=================

Given a total and coins of certain denominations find number of ways total can be formed from coins assuming infinity
supply of coins.

Analysis
--------
* Runtime : O(num_of_coins * total)

Video
-----
* https://youtu.be/_fgjrs570YE

Reference
---------
* http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
"""


def coin_changing_num_ways(coins, total):
    cols = total + 1   # 1 for value 0 in total
    rows = len(coins)
    T = [[1 if col == 0 else 0 for col in range(cols)] for _ in range(rows)]

    for i in range(rows):
        for j in range(cols):
            if (i - 1) < 0:
                continue
            if j < coins[i]:
                T[i][j] = T[i - 1][j]
            else:
                T[i][j] = T[i - 1][j] + T[i][j - coins[i]]

    return T[rows - 1][cols - 1]


def coin_changing_num_ways2(coins, total):
    cols = total + 1
    num_coins = len(coins)

    # Using 1-D Array instead of 2-D Array. Approach is same as coin_changing_num_ways.
    T = [1 if col == 0 else 0 for col in range(cols)]

    for i in range(num_coins):
        for col in range(1, cols):
            if col >= coins[i]:
                T[col] += T[col - coins[i]]

    return T[cols - 1]


def print_coin_changes_recursive(coins, total, results_stack, pos):
    if total == 0:
        for coin in results_stack:
            print "%d " % coin,
        print

    for idx in range(pos, len(coins)):
        if total >= coins[idx]:
            results_stack.append(coins[idx])
            print_coin_changes_recursive(coins, total - coins[idx], results_stack, idx)
            results_stack.pop()        # Remove last inserted coin from stack to use new coin with different index.


def print_coin_changes(coins, total):
    print_coin_changes_recursive(coins, total, list(), 0)


if __name__ == '__main__':
    coins = [1, 2, 3]
    total = 5
    expected = 5
    assert expected == coin_changing_num_ways(coins, total)
    assert expected == coin_changing_num_ways2(coins, total)
    print_coin_changes(coins, total)
