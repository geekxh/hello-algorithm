"""
Problem Statement
=================

Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15,
shows the first 11 ugly numbers. By convention, 1 is included.

Write a program to find the kth ugly number.

Complexity
----------

* Time Complexity O(n)
* Space Complexity O(n)

Reference
---------
* http://www.geeksforgeeks.org/ugly-numbers/
"""


def ugly_number(kth):
    ugly_factors = [1]      # By convention 1 is included.

    factor_index = {
        2: 0,
        3: 0,
        5: 0}

    for num in range(1, kth):
        minimal_factor = min(min(ugly_factors[factor_index[2]] * 2, ugly_factors[factor_index[3]] * 3),
                             ugly_factors[factor_index[5]] * 5)

        ugly_factors.append(minimal_factor)

        for factor in [2, 3, 5]:
            if minimal_factor % factor == 0:
                factor_index[factor] += 1

    return ugly_factors[kth - 1]

if __name__ == '__main__':
    assert 5832 == ugly_number(150)
