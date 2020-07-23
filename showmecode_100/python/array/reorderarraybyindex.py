# http://www.geeksforgeeks.org/reorder-a-array-according-to-given-indexes/

def reorder(input, index):
    if len(input) != len(index):
        raise ValueError
    for i in range(len(index)):
        while index[i] != i:
            s_index = index[index[i]]
            s_val = input[index[i]]

            index[index[i]] = index[i]
            input[index[i]] = input[i]

            index[i] = s_index
            input[i] = s_val

if __name__ == '__main__':
    input = [50, 40, 70, 60, 90]
    index = [3, 0, 4, 1, 2]

    reorder(input, index)
    print(input)
    print(index)

            
