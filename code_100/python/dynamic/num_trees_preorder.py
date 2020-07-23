"""
Problem Statement
=================

Given the number of nodes N, in a pre-order sequence how many unique trees can be created? Number of tree is exactly
same as number of unique BST create with array of size n. The solution is a catalan number.

Complexity
----------
* Dynamic Programming: O(n^2)
* Recursive Solution: O(2^n)

Video
-----
* https://youtu.be/RUB5ZPfKcnY
"""


def num_trees(num_nodes):
    T = [0 for _ in range(num_nodes + 1)]
    T[0] = 1
    T[1] = 1
    for n in range(2, num_nodes + 1):
        for j in range(0, n):
            T[n] += T[j] * T[n - j - 1]
    return T[num_nodes]


def num_trees_recursive(num_nodes):
    if num_nodes == 0 or num_nodes == 1:
        return 1

    result = 0

    for n in range(1, num_nodes + 1):
        result += num_trees_recursive(n - 1) * num_trees_recursive(num_nodes - n)

    return result




if __name__ == '__main__':
    assert 5 == num_trees(3)
    assert 14 == num_trees(4)
    assert 42 == num_trees(5)
    assert 5 == num_trees_recursive(3)
    assert 14 == num_trees_recursive(4)
    assert 42 == num_trees_recursive(5)
