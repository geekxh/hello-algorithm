"""
Problem Statement
=================

Given a certain number of eggs and a certain number of floors, determine the minimum number of attempts required to find
the egg breaking floor.

Analysis
--------

* Dynamic Programming Time Complexity: O(eggs * num_floors^2)
* Recursive Solution: Exponential

Video
-----

* https://youtu.be/3hcaVyX00_4

Reference
---------

* http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/

"""


def min_attempts_egg_drop(eggs, floors):
    num_eggs = eggs + 1
    num_floors = floors + 1

    T = [[floor if egg == 1 else 0 for floor in range(num_floors)] for egg in range(num_eggs)]

    for egg in range(2, num_eggs):
        for floor in range(1, num_floors):
            T[egg][floor] = min(1 + max(T[egg - 1][k - 1], T[egg][floor - k]) for k in range(1, floor + 1))

    return T[num_eggs - 1][num_floors - 1]


def min_attempts_egg_drop_recursive(eggs, floors):
    if eggs == 1 or floors == 0:
        return floors

    min_value = float("inf")

    for floor in range(1, floors + 1):
        min_value = min(min_value,
                        1 + max(min_attempts_egg_drop_recursive(eggs - 1, floor - 1),
                                min_attempts_egg_drop_recursive(eggs, floors - floor)))

    return min_value


if __name__ == '__main__':
    eggs = 3
    floors = 100
    expected_attempts = 9

    assert expected_attempts == min_attempts_egg_drop(eggs, floors)

    eggs = 2
    floors = 6
    expected_attempts = 3
    assert expected_attempts == min_attempts_egg_drop_recursive(eggs, floors)
