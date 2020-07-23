# http://www.geeksforgeeks.org/find-zeroes-to-be-flipped-so-that-number-of-consecutive-1s-is-maximized/

def flip_0s_to_maximize_consecutive_1s(input, flips_allowed):
    window_start = 0
    count_zero = 0
    result = 0
    for i in range(len(input)):
        if input[i] == 1:
            result = max(result, i - window_start + 1)
        else:
            if count_zero < flips_allowed:
                count_zero = count_zero + 1
                result = max(result, i - window_start + 1)
            else:
                while True:
                    if input[window_start] == 0:
                        window_start = window_start + 1
                        break
                    window_start = window_start + 1
    return result
    
if __name__ == '__main__':
    input = [0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1]
    print(flip_0s_to_maximize_consecutive_1s(input, 1))
