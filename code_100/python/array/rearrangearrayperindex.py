# http://www.geeksforgeeks.org/rearrange-array-arrj-becomes-arri-j/

def rearrange(input):
    for i in range(len(input)):
        input[i] += 1

    for i in range(len(input)):
        if input[i] > 0:
            rearrange_util(input, i)

    for i in range(len(input)):
        input[i] = -input[i] - 1

def rearrange_util(input, start):
    i = start + 1
    v = input[start]
    while v > 0:
        t = input[v-1]
        input[v-1] = -i
        i = v
        v = t

if __name__ == '__main__':
    input = [1, 2, 0, 5, 3, 4];
    rearrange(input)
    print(input)
