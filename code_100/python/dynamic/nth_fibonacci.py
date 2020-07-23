"""
Problem Statement
=================

Given the number n, find the nth fibanacci number.

The fibonacci series is 0, 1, 1, 2, 3 ...

And follows the formula Fn = Fn-1 + Fn-2

Complexity
----------

* Recursive Solution: O(2^n)
* Dynamic Programming: O(n)

"""


def fibonacci_recursive(n):
    if n == 0 or n == 1:
        return n

    return fibonacci_recursive(n - 1) + fibonacci_recursive(n - 2)


def fibonacci(n):
    n1, n2 = 0, 1

    if n == n1 or n == n2:
        return n

    for i in range(2, n + 1):
        n1, n2 = n2, n1 + n2

    return n2

if __name__ == '__main__':
    assert 610 == fibonacci_recursive(15)
    assert 610 == fibonacci(15)
