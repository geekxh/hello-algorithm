# http://www.geeksforgeeks.org/rearrange-array-alternating-positive-negative-items-o1-extra-space/

def rearrange(input):
    for i in range (len(input)):
        if i%2 == 0 and input[i] >= 0:
            index_of_next_negative = find_next(input, i+1, False)
            if index_of_next_negative == -1:
                return
            else:
                right_rotate(input, i, index_of_next_negative)
        elif i % 2 != 0 and input[i] < 0:
            index_of_next_positive = find_next(input, i+1, True)
            if index_of_next_positive == -1:
                return
            else:
                right_rotate(input, i, index_of_next_positive)

def find_next(input, start, isPositive):
    for i in range(start, len(input)):
        if (isPositive and input[i] >= 0) or (not isPositive and input[i] < 0):
            return i;
    return -1

def right_rotate(input, start, end):
    t = input[end]
    for i in range(end, start -1, -1):
        input[i] = input[i-1]
    input[start] = t

if __name__ == '__main__':
    input = [-5, -2, 5, 2, 4, 7, 1, 8, 0, -8];
    rearrange(input)
    print(input)
    
