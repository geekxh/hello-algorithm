# http://www.geeksforgeeks.org/convert-array-into-zig-zag-fashion/

def rearrange(input):
    is_less = True
    for i in range(len(input)-1):
        if is_less:
            if input[i] > input[i+1]:
                swap(input, i, i+1)
        else:
            if input[i] < input[i+1]:
                swap(input, i, i+1)
        is_less = not is_less

def swap(input, i, j):
    t = input[i]
    input[i] = input[j]
    input[j] = t

if __name__ == '__main__':
    input = [4, 3, 2, 6, 7, 1, 9]
    rearrange(input)
    print(input)
            
