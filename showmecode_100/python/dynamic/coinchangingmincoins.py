"""
Problem Statement
=================

Given coins of certain denominations with infinite supply find minimum number of coins it takes to form given total

Video
-----

* Topdown DP - https://youtu.be/Kf_M7RdHr1M
* Bottom Up DP - https://youtu.be/Y0ZqKpToTic (Approach 1. 2D array.)
* Bottom up DP - https://youtu.be/NJuKJ8sasGk (Same as Approach 1. Uses 1D array since 2D array is not required.)

Analysis
--------

* Time complexity - O(len(coins) * total)
* Space complexity - O(len(coins) * total)
"""


def min_coins(coins, total):
    cols = total + 1
    rows = len(coins)
    T = [[0 if col == 0 else float("inf") for col in range(cols)] for _ in range(rows)]

    for i in range(rows):
        for j in range(1, cols):
            if j < coins[i]:
                T[i][j] = T[i - 1][j]
            else:
                T[i][j] = min(T[i - 1][j], 1 + T[i][j - coins[i]])

    return T[rows - 1][cols - 1]


def print_coins(R, coins):
    start = len(R) - 1

    if R[start] == -1:
        print "No Solution Possible."
        return

    print "Coins:",
    while start != 0:
        coin = coins[R[start]]
        print "%d " % coin,
        start = start - coin


def min_coins2(coins, total):
    cols = total + 1
    T =[0 if idx == 0 else float("inf") for idx in range(cols)]
    R = [-1 for _ in range(total + 1)]

    for j in range(len(coins)):
        for i in range(1, cols):
            coin = coins[j]
            if i >= coins[j]:
                if T[i] > 1 + T[i - coin]:
                    T[i] = 1 + T[i - coin]
                    R[i] = j

    print_coins(R, coins)
    return T[cols - 1]


def min_coins_top_down(coins, total, memo):
    if total == 0:
        return 0

    if total in memo:
        return memo[total]

    min_value = float("inf")
    for i in range(len(coins)):
        coin = coins[i]
        if coin > total:
            continue
        val = min_coins_top_down(coins, total - coin, memo)
        min_value = min(min_value, val)

    min_value += 1

    memo[total] = min_value
    return min_value


if __name__ == '__main__':
    coins = [1, 5, 6, 8]
    total = 11
    expected = 2
    assert expected == min_coins(coins, total)
    assert expected == min_coins2(coins, total)
    assert expected == min_coins_top_down(coins, total, dict())
