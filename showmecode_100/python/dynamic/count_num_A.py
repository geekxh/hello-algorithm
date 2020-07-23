"""
Problem Statement
=================

Imagine you have a special keyboard with the following keys:

Key 1:  Prints 'A' on screen
Key 2: (Ctrl-A): Select screen
Key 3: (Ctrl-C): Copy selection to buffer
Key 4: (Ctrl-V): Print buffer on screen appending it
                 after what has already been printed.

If you can only press the keyboard for N times (with the above four
keys), write a program to produce maximum numbers of A's. That is to
say, the input parameter is N (No. of keys that you can press), the
output is M (No. of As that you can produce).

Complexity
----------

* Recursive Solution : Exponential > O(2^n)
* Dynamic Programming: Quadratic O(n^2)

Reference
---------
* http://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/
"""


def count_a_recursive(n_times):
    if n_times < 7:
        return n_times

    result = float("-inf")

    for sub_prob in range(n_times - 3, 0, -1):
        result = max(result, (n_times - sub_prob - 1) * count_a_recursive(sub_prob))

    return result


def count_a(n_times):
    if n_times < 7:
        return n_times

    T = [0 for _ in range(n_times + 1)]

    for num in range(7):
        T[num] = num

    for n in range(7, n_times + 1):
        for sub_prob in range(n - 3, 0, -1):
            T[n] = max(T[n], T[sub_prob] * (n - sub_prob - 1))

    return T[n_times]


if __name__ == '__main__':
    expected = 9
    assert expected == count_a_recursive(7)
    assert expected == count_a(7)
