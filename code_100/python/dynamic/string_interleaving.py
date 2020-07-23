"""
Problem Statement
=================

Given three strings A, B and C. Write a function that checks whether C is an interleaving of A and B. C is said to be
interleaving A and B, if it contains all characters of A and B and order of all characters in individual strings is
preserved.

http://www.geeksforgeeks.org/check-whether-a-given-string-is-an-interleaving-of-two-other-given-strings-set-2/

Video: https://www.youtube.com/watch?v=ih2OZ9-M3OM
"""

def is_interleaved_recursive(str1, str2, str3, pos1, pos2, pos3):
    if pos1 == len(str1) and pos2 == len(str2) and pos3 == len(str3):
        return True

    if pos3 == len(str3):
        return False

    return (((pos1 < len(str1) and str1[pos1] == str3[pos3]) and is_interleaved_recursive(str1, str2, str3, pos1 + 1, pos2, pos3 + 1)) or
            (pos2 < len(str2) and str2[pos2] == str3[pos3]) and is_interleaved_recursive(str1, str2, str3, pos1, pos2 + 1, pos3 + 1))


def is_interleaved(str1, str2, str3):
    if len(str3) != (len(str1) + len(str2)):
        return False

    cols = len(str1) + 1
    rows = len(str2) + 1

    T = [[False for _ in range(cols)] for _ in range(rows)]

    for row in range(rows):
        for col in range(cols):
            index = row + col - 1
            if row == 0 and col == 0:
                T[row][col] = True
            elif row == 0:
                if str3[index] == str1[col - 1]:
                    T[row][col] = True and T[row][col - 1]
            elif col == 0:
                if str3[index] == str2[row - 1]:
                    T[row][col] = True and T[row - 1][col]
            else:
                T[row][col] = ((T[row][col - 1] if str3[index] == str1[col - 1] else False) or
                               (T[row - 1][col] if str3[index] == str2[row - 1] else False))

    return T[rows - 1][cols - 1]


if __name__ == '__main__':
    str1 = "XXYM"
    str2 = "XXZT"
    str3 = "XXXZXYTM"

    assert True == is_interleaved(str1, str2, str3)
    assert True == is_interleaved_recursive(str1, str2, str3, 0, 0, 0)
