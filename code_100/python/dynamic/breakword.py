"""
Problem Statement
=================

Given a string and a dictionary, split the string in to multiple words so that each word belongs to the dictionary.

Video
-----

* https://youtu.be/WepWFGxiwRs

Analysis
--------

* word_break_recursive: Exponential
* word_break_dp : O(n^3)

Solution
--------

if input[i..j] belongs in a dictionary:
    DP[i][j] = True
else:
    DP[i][j] = True if DP[i][k-1] and DP[k][j] for any k between i to j.

Multiple different implementations are given below.
"""


def word_break_recursive(given_string, dictionary):
    """"Returns None if the given string cannot be broken into words, otherwise returns space separate words."""

    given_string_length = len(given_string)
    if given_string_length == 0:
        return ""
    string = ""
    for i in range(given_string_length):
        string += given_string[i]
        if string in dictionary:
            r = word_break_recursive(given_string[i + 1:], dictionary)
            if r is not None:
                string += " " + r
                return string
    return None


def word_break_dp(given_string, dictionary):
    """Returns None if the given string cannot be broken into words, otherwise returns space separated words."""

    given_string_length = len(given_string)

    # -1 indicates the word cannot be split.
    DP = [[-1 for _ in range(given_string_length)] for _ in range(given_string_length)]

    for substring_length in range(1, given_string_length + 1):
        for start in range(0, given_string_length - substring_length + 1):
            end = start + substring_length - 1
            substring = given_string[start: end + 1]
            if substring in dictionary:
                DP[start][end] = start
                continue

            for split in range(start + 1, end + 1):
                if DP[start][split - 1] != -1 and DP[split][end] != -1:
                    DP[start][end] = split
                    break

    if DP[0][-1] == -1:
        return None

    words = []
    start_index = 0
    end_index = given_string_length - 1
    while start_index < given_string_length:
        split_index = DP[start_index][end_index]
        if start_index == split_index:
            words.append(given_string[start_index: end_index + 1])
            break
        else:
            words.append(given_string[start_index: split_index])
        start_index = split_index

    return " ".join(words)


def is_word_break_possible(given_string, dictionary):
    """Returns if any word break is possible amongst the multiple word breaks in the sentence."""

    DP = dict()
    max_word_length = len(max(dictionary, key=len))
    return is_word_break_possible_recursive_helper(given_string, dictionary, 0, max_word_length, DP)


def is_word_break_possible_recursive_helper(given_string, dictionary, start, max_word_length, DP):
    if start == len(given_string):
        return True

    if start in DP:
        return DP[start]

    for i in range(start, start + max_word_length):
        if i < len(given_string):
            new_word = given_string[start: i + 1]
            if new_word in dictionary:
                continue
            if is_word_break_possible_recursive_helper(given_string, dictionary, i + 1, max_word_length, DP):
                DP[start] = True
                return True

    DP[start] = False
    return False


def all_possible_word_break_helper(given_string, dictionary, start, max_word_length, DP):
    """"Returns all possible word breaks in a given sentence."""
    if start == len(given_string):
        return [""]

    if start in DP:
        return DP[start]

    words = []
    for i in range(start, start + max_word_length):
        if i < len(given_string):
            new_word = given_string[start: i + 1]
            if new_word not in dictionary:
                continue
            sub_words = all_possible_word_break_helper(given_string, dictionary, i + 1, max_word_length, DP)
            for word in sub_words:
                extra_space = "" if len(word) == 0 else " "
                words.append(new_word + extra_space + word)

    DP[start] = words
    return words


def all_possible_word_breaks(given_string, dictionary):
    DP = dict()
    max_word_length = len(max(dictionary, key=len))
    return all_possible_word_break_helper(given_string, dictionary, 0, max_word_length, DP)


if __name__ == '__main__':
    dictionary = {"joy", "likes", "to", "play"}
    given_string = "joylikestoplay"

    assert True == is_word_break_possible(given_string, dictionary)
    assert "joy likes to play " == word_break_recursive(given_string, dictionary)
    assert "joy likes to play" == word_break_dp(given_string, dictionary)

    dictionary = {"pea", "nut", "peanut", "butter"}
    given_string = "peanutbutter"
    assert ['pea nut butter', 'peanut butter'] == all_possible_word_breaks(given_string, dictionary)
