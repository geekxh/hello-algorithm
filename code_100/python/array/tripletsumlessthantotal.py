# http://www.geeksforgeeks.org/count-triplets-with-sum-smaller-that-a-given-value/

def find_all_triplet(input, total):
    input.sort()
    result = 0
    for i in range(len(input) - 2):
        j = i + 1
        k = len(input) - 1
        while j < k:
            if input[i] + input[j] + input[k] >= total:
                k = k - 1
            else:
                result += k - j
                j = j + 1
    return result


if __name__ == '__main__':
    input = [5, 1, 3, 4, 7]
    print(find_all_triplet(input, 12))
    
