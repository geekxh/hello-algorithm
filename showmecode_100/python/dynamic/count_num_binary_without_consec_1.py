"""
Problem Statement
=================

Given a positive integer N, count all the numbers from 1 to 2^N, whose binary representation does not have consecutive
1s.

This is a simple application of fibonacci series.

Video
-----

* https://www.youtube.com/watch?v=a9-NtLIs1Kk

Complexity
----------

* Runtime Complexity: O(n)


Reference
---------

* http://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
"""


def consec_one(num_n):
    f1 = f2 = 1

    for _ in range(num_n):
        f1, f2 = f1 + f2, f1

    return f1

if __name__ == '__main__':
    assert 13 == consec_one(5)
