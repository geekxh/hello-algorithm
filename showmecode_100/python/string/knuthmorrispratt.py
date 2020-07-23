#  Knuth-Morris-Pratt algorithm 


# Compute temporary array to maintain size of suffix which is same as prefix
# Time/space complexity is O(size of pattern)
def compute_temporary_array(pattern):
    n = len(pattern)
    lsp = [0 for j in range(n)]
    index = 0
    i = 1
    while i < len(pattern):
        if pattern[i] == pattern[index]:
            lsp[i] = index + 1
            index += 1
            i += 1
        else:
            if index != 0:
                index = lsp[index - 1]
            else:
                lsp[i] = 0
                i += 1
    return lsp


# KMP algorithm of pattern matching.
def kmp(text, pattern):
    lsp = compute_temporary_array(pattern)
    i = 0
    j = 0
    while i < len(text) and j < len(pattern):
        if text[i] == pattern[j]:
            i += 1
            j += 1
        else:
            if j != 0:
                j = lsp[j - 1]
            else:
                i += 1
    if j == len(pattern):
        return True
    else:
        return False

src = 'abcxabcdabcdabcy'
sub_string = 'abcdabcy'
result = kmp(src, sub_string)
print(result)


