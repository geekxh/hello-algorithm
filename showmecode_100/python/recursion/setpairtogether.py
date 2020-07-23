# http://www.geeksforgeeks.org/minimum-number-of-swaps-required-for-arranging-pairs-adjacent-to-each-other/

def find_minimum_swaps(input, pair):
    index = {}
    for i, val in enumerate(input):
        index[val] = i
    return find_minimum_swaps_util(input, pair, index, 0)

def find_minimum_swaps_util(input, pair, index, current):
    if current == len(input):
        return 0

    v1 = input[current]
    v2 = input[current + 1]
    pv2 = pair[v1]

    if pv2 == v2:
        return find_minimum_swaps_util(input, pair, index, current + 2)
    else:
        idx1 = index.get(v1)
        idx2 = index.get(v2)
        idx3 = index.get(pair[v1])
        idx4 = index.get(pair[v2])

        swap(index, input, idx2, idx3)
        val1 = find_minimum_swaps_util(input, pair, index, current+2)
        swap(index, input, idx2, idx3)

        swap(index, input, idx1, idx4)
        val2 = find_minimum_swaps_util(input, pair, index, current+2)
        swap(index, input, idx1, idx4)

        return 1 + max(val1, val2)
        

def swap(index, input, i, j):
    index[input[i]] = j
    index[input[j]] = i
    t = input[i]
    input[i] = input[j]
    input[j] = t

if __name__ == '__main__':
    input = [3, 5, 6, 4, 1, 2]
    pair = {}
    pair[1] = 3
    pair[3] = 1
    pair[2] = 6
    pair[6] = 2
    pair[4] = 5
    pair[5] = 4

    print(find_minimum_swaps(input, pair))
    
