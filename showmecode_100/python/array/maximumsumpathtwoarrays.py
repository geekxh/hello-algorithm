# http://www.geeksforgeeks.org/maximum-sum-path-across-two-arrays/

def max_sum(input1, input2):
    max_sum = 0
    i = 0
    j = 0
    sum1 = 0
    sum2 = 0
    while i < len(input1) and j < len(input2):
        if input1[i] == input2[j]:
            if sum1 > sum2:
                max_sum += sum1 + input1[i]
            else:
                max_sum += sum2 + input2[j]
            i = i + 1
            j = j + 1
            sum1 = 0
            sum2 = 0
        elif input1[i] < input2[j]:
            sum1 += input1[i]
            i = i + 1
        else:
            sum2 += input2[j]
            j = j + 1

    while i < len(input1):
        sum1 += input1[i]
        i = i + 1

    while j < len(input2):
        sum2 += input2[j]
        j = j + 1

    if sum1 > sum2:
        max_sum += sum1
    else:
        max_sum += sum2

    return max_sum

if __name__ == '__main__':
    input1 = [2, 3, 7, 10, 12, 15, 30, 34]
    input2 = [1, 5, 7, 8, 10, 15, 16, 19]

    print(max_sum(input1, input2))
        
