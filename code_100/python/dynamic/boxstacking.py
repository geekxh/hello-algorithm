"""
Problem Statement
=================

Given different dimensions and unlimited supply of boxes for each dimension, stack boxes on top of each other such that
it has maximum height but with caveat that length and width of box on top should be strictly less than length and width
of box under it. You can rotate boxes as you like.

1) Create all rotations of boxes such that length is always greater or equal to width
2) Sort boxes by base area in non increasing order (length * width). This is because box
   with more area will never ever go on top of box with less area.
3) Take T[] and result[] array of same size as total boxes after all rotations are done
4) Apply longest increasing subsequence type of algorithm to get max height.

Analysis
--------
If n number of dimensions are given total boxes after rotation will be 3n.
* Space complexity is O(n)
* Time complexity - O(nlogn) to sort boxes. O(n^2) to apply DP on it So really O(n^2)

Video
-----
* https://youtu.be/9mod_xRB-O0

References
----------
* http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
* http://people.cs.clemson.edu/~bcdean/dp_practice/
"""

from collections import namedtuple
from itertools import permutations

dimension = namedtuple("Dimension", "height length width")


def create_rotation(given_dimensions):
    """
    A rotation is an order wherein length is greater than or equal to width. Having this constraint avoids the
    repetition of same order, but with width and length switched.

    For e.g (height=3, width=2, length=1) is same the same box for stacking as (height=3, width=1, length=2).

    :param given_dimensions: Original box dimensions
    :return: All the possible rotations of the boxes with the condition that length >= height.
    """
    for current_dim in given_dimensions:
        for (height, length, width) in permutations((current_dim.height, current_dim.length, current_dim.width)):
            if length >= width:
                yield dimension(height, length, width)


def sort_by_decreasing_area(rotations):
    return sorted(rotations, key=lambda dim: dim.length * dim.width, reverse=True)


def can_stack(box1, box2):
    return box1.length < box2.length and box1.width < box2.width


def box_stack_max_height(dimensions):
    boxes = sort_by_decreasing_area([rotation for rotation in create_rotation(dimensions)])
    num_boxes = len(boxes)
    T = [rotation.height for rotation in boxes]
    R = [idx for idx in range(num_boxes)]

    for i in range(1, num_boxes):
        for j in range(0, i):
            if can_stack(boxes[i], boxes[j]):
                stacked_height = T[j] + boxes[i].height
                if stacked_height > T[i]:
                    T[i] = stacked_height
                    R[i] = j

    max_height = max(T)
    start_index = T.index(max_height)

    # Prints the dimensions which were stored in R list.
    while True:
        print boxes[start_index]
        next_index = R[start_index]
        if next_index == start_index:
            break
        start_index = next_index

    return max_height


if __name__ == '__main__':

    d1 = dimension(3, 2, 5)
    d2 = dimension(1, 2, 4)

    assert 11 == box_stack_max_height([d1, d2])
