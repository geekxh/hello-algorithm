# http://www.geeksforgeeks.org/longest-span-sum-two-binary-arrays/
# java code https://github.com/mission-peace/interview/blob/master/src/com/interview/array/LongestSameSumSpan.java

def longest_span(input1, input2):
    if len(input1) != len(input2):
        raise ValueError;

    diff = {}
    prefix1 = 0
    prefix2 = 0
    max_span = 0
    diff[0] = -1
    for i in range(len(input1)):
        prefix1 += input1[i]
        prefix2 += input2[i]
        curr_diff = prefix1 - prefix2
        if curr_diff in diff:
            max_span = max(max_span, i - diff[curr_diff])
        else:
            diff[curr_diff] = i
    return max_span

if __name__ == '__main__':
    input1 = [1, 0, 0, 1, 1, 0]
    input2 = [0, 1, 1, 0, 1, 1]

    print(longest_span(input1, input2))
    
    
