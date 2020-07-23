"""
Problem Statement
=================

Given a string find longest palindromic subsequence in this string.

Complexity
----------

* Dynamic Programming Time Complexity: O(n^2)
* Recursive Solution Time Complexity: O(2^n)

Video
-----
* https://youtu.be/_nCsPn7_OgI

References
----------
* http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
"""


def longest_palindromic_subsequence(given_string):
    rows = cols = string_length = len(given_string)
    T = [[0 for _ in range(cols)] for _ in range(rows)]

    for row in range(rows):
        T[row][row] = 1

    for substring_length in range(2, string_length + 1):
        for row in range(0, string_length - substring_length + 1):
            col = row + substring_length - 1

            if given_string[row] == given_string[col]:
                if string_length == 2:
                    T[row][col] = 2
                else:
                    T[row][col] = 2 + T[row + 1][col - 1]
            else:
                T[row][col] = max(T[row + 1][col], T[row][col - 1])

    return T[0][-1]


def palindromic_subsequence_recursive_helper(given_string, start_index, length):
    if length == 0 or length == 1:
        return length

    if given_string[start_index] == given_string[length - start_index - 1]:
        return 2 + palindromic_subsequence_recursive_helper(given_string, start_index + 1, length - 2)
    else:
        return max(palindromic_subsequence_recursive_helper(given_string, start_index, length - 1),
                   palindromic_subsequence_recursive_helper(given_string, start_index + 1, length - 1))


def longest_palindromic_subsequence_recursive(given_string):
    return palindromic_subsequence_recursive_helper(given_string, 0, len(given_string))


if __name__ == '__main__':
    given_string = "agbdba"
    expected_result = 5
    assert expected_result == longest_palindromic_subsequence(given_string)
    assert expected_result == longest_palindromic_subsequence_recursive(given_string)
