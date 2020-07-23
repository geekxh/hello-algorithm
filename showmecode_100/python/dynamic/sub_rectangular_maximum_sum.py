"""
Problem Statement
=================

Write a program to find maximum sum rectangle in give 2D matrix. Assume there is at least one positive number in the 2D
matrix.

Solution:
--------

* Keep temp array with size as number of rows. Start left and right from 0 and keep adding values for each row and
  maintain them in this temp array.
* Run Kadane's algorithm to find max sum subarray in temp. Now increment right by 1.
* When right reaches last column reset right to 1 and left to 1.

Analysis
--------
* Space complexity of this algorithm is O(row)
* Time complexity of this algorithm is O(row*col*col)

Video
-----
* https://youtu.be/yCQN096CwWM

References
----------
* http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
"""

from collections import namedtuple

Result = namedtuple("Result","maxSum leftBound rightBound upBound lowBound")

KadanesResult = namedtuple("KadanesResult","maxSum start end")


def kadanes(temp):
    max = 0
    maxStart = -1
    maxEnd = -1
    currentStart = 0
    maxSoFar = 0

    for i in range(0, len(temp)):
        maxSoFar += temp[i]

        if maxSoFar < 0:
            maxSoFar = 0
            currentStart = i + 1

        if maxSoFar > max:
            maxStart = currentStart
            maxEnd = i
            max = maxSoFar

    return KadanesResult(max, maxStart, maxEnd)


def max_sub_sub_rectangle(rectangle):

    rows = len(rectangle)
    cols = len(rectangle[0])

    result = Result(float("-inf"), -1, -1, -1, -1)

    for left in range(cols):
        temp = [0 for _ in range(rows)]
        for right in range(left, cols):
            for i in range(rows):
                temp[i] += rectangle[i][right]

            kadanes_result = kadanes(temp)
            if kadanes_result.maxSum > result.maxSum:
                result = Result(kadanes_result.maxSum, left, right, kadanes_result.start, kadanes_result.end)

    return result

if __name__ == '__main__':
    rectangle = [[2,  1, -3, -4,  5],
                 [0,  6,  3,  4,  1],
                 [2, -2, -1,  4, -5],
                 [-3,  3,  1,  0,  3]]

    result = max_sub_sub_rectangle(rectangle)
    assert 18 == result.maxSum
    print result
