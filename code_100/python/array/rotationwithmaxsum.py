# http://www.geeksforgeeks.org/find-maximum-value-of-sum-iarri-with-only-rotations-on-given-array-allowed/

def max_sum(input):
    arr_sum = 0
    rotation_sum = 0
    for i in range(len(input)):
        arr_sum += input[i]
        rotation_sum += i*input[i]

    max_rotation_sum = rotation_sum

    for i in range(1, len(input)):
         rotation_sum += len(input)*input[i-1] - arr_sum
         max_rotation_sum = max(max_rotation_sum, rotation_sum)

    return max_rotation_sum

if __name__ == '__main__':
    input = [10, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    print(max_sum(input))
