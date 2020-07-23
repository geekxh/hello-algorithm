# http://www.geeksforgeeks.org/count-inversions-of-size-three-in-a-give-array/

def find_inversions(input):
    inversion = 0
    for i in range(1, len(input) - 1):
        larger = 0
        for k in range(0, i):
            if input[k] > input[i]:
                larger = larger + 1
        smaller = 0
        for k in range(i+1, len(input)):
            if input[k] < input[i]:
                smaller = smaller + 1

        inversion += larger*smaller
    return inversion

if __name__ == '__main__':
    input = [9, 6, 4, 5, 8]
    print(find_inversions(input))
    
