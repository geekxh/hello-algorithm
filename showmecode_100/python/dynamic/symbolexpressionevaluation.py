"""
Problem Statement
=================

Let there be a binary operation for 3 symbols a, b, c and result of these binary operation given in a table.
Given an expression of these 3 symbols and a final result, tell if this expression can be parenthesize in certain
way to produce the final result.

Complexity
----------

* Run time Complexity: O(n^3)
* SpaceL O(n^2)

Where n is the length of the expression.
"""


def evaluate_expression(expression_map, expression, result):
    expression_length = len(expression)
    T = [[set() for _ in range(expression_length)] for _ in range(len(expression))]

    for idx, expr in enumerate(expression):
        T[idx][idx].add(expr)

    # We take a sub expression of length 2 until the total expression length
    for sub_length in range(2, expression_length + 1):
        for left_index in range(0, expression_length - sub_length + 1):
            right_index = left_index + sub_length - 1
            # we split the expression at different k indices for the total sub-expression length and store the result.
            # at T[left_index][right_index]
            # Like bbc, will be treated for (b(bc) and ((bb) c) and the final result is stored in a set at T[0][2]
            for k in range(left_index, right_index):
                for expr1 in T[left_index][k]:
                    for expr2 in T[k+1][right_index]:
                        T[left_index][right_index].add(expression_map[(expr1, expr2)])

    for expr in T[0][-1]:
        if result in expr:
            return True

    return False

if __name__ == '__main__':
    expressions = ['a', 'b', 'c']
    # expression table denotes the binary operation between two expression and its result.
    expression_table = [
        ['b', 'b', 'a'],
        ['c', 'b', 'a'],
        ['a', 'a', 'c']
    ]

    # For convenience, we can modify it to be more explicit and use the expression table

    expression_map = {
        ('a', 'a'): 'b',
        ('a', 'b'): 'b',
        ('a', 'c'): 'a',
        ('b', 'a'): 'c',
        ('b', 'b'): 'b',
        ('b', 'c'): 'a',
        ('c', 'a'): 'a',
        ('c', 'b'): 'a',
        ('c', 'c'): 'c'
    }

    assert True == evaluate_expression(expression_map, 'bbbbac', 'a')
