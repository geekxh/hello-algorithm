# http://www.geeksforgeeks.org/find-smallest-value-represented-sum-subset-given-array/

def find_smallest_integer(input):
    result = 1
    for i in range(len(input)):
        if input[i] <= result:
            result += input[i]
        else:
            break
    return result

if __name__ == '__main__':
    input = [1, 2, 3, 8]
    print(find_smallest_integer(input))
