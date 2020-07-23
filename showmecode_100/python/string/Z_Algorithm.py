#author: Pankaj Kumar

#time complexity: O(length(string) + length(pattern))

#space complexity: O(length(string) + length(pattern))

#Link to theory: http://www.geeksforgeeks.org/z-algorithm-linear-time-pattern-searching-algorithm/

def z_algo(arr):
    z = [0 for i in range(len(arr))]
    left , right = 0 , 0
    for k in range(1 , len(arr)):
        if k > right:
            left = k
            right = k
            while right < len(arr) and arr[right] == arr[right-left]:
                right += 1
            z[k] = right - left
            right -= 1
        else:
            k1 = k - left
            if z[k1] < right - k + 1:
                z[k] = z[k1]
            else:
                left = k
                while right < len(arr) and arr[right] == arr[right-left]:
                    right += 1
                z[k] = right - left
                right -= 1
    return z
                
            
def makepattern(string , pattern):
    n , m = len(string) , len(pattern)
    str_arr = []
    for i in range(m):
        str_arr.append(pattern[i])
    str_arr.append('$')
    for i in range(n):
        str_arr.append(string[i])
        
    z_values = z_algo(str_arr)
    result = []
    for i in range(len(z_values)):
        if z_values[i] == m:
            result.append(i - m - 1)
    print result



if __name__ == '__main__':
    string = 'abcdeabcd'
    pattern = 'abc'
    makepattern(string , pattern)
