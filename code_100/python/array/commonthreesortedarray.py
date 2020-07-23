# http://www.geeksforgeeks.org/find-common-elements-three-sorted-arrays/

def common_elements(input1, input2, input3):
    result = []
    i = 0
    j = 0
    k = 0
    while i < len(input1) and j < len(input2) and k < len(input3):
        if input1[i] == input2[j] and input2[j] == input3[k]:
            result.append(input1[i])
            i = i + 1
            j = j + 1
            k = k + 1
        elif input1[i] < input2[j]:
            i = i + 1
        elif input2[j] < input3[k]:
            j = j + 1
        else:
            k = k + 1
    return result

if __name__ == '__main__':
    input1 = [1, 5, 10, 20, 40, 80]
    input2 = [6, 7, 20, 80, 100]
    input3 = [3, 4, 15, 20, 30, 70, 80, 120]

    print(common_elements(input1, input2, input3))
