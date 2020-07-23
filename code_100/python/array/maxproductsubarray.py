#  http://www.geeksforgeeks.org/maximum-product-subarray/

def max_product(input):
    max_ending = 1
    min_ending = 1
    max_so_far = 1
    for i in input:
        if i > 0:
            max_ending = max_ending * i
            min_ending = min(min_ending*i, 1)
        elif i == 0:
            max_ending = 1
            min_ending = 1
        else:
            t = max_ending
            max_ending = max(min_ending*i, 1)
            min_ending = t * i
        if max_so_far < max_ending:
            max_so_far = max_ending
    return max_so_far

if __name__ == '__main__':
    input = [-6,-3,8,-9,-1,-1,3,6,9,0,3,-1]
    print(max_product(input))
