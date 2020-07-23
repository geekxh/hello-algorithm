# http://www.geeksforgeeks.org/find-number-of-triangles-possible/

def number_of_triangles(input):
    input.sort()
    count = 0
    for i in range(len(input)-2):
        k = i + 2
        for j in range(i+1, len(input)):
            while k < len(input) and input[i] + input[j] > input[k]:
                k = k + 1
            count += k - j - 1
    return count

if __name__ == '__main__':
    input = [15, 9, 8, 3, 4, 5, 6]
    print(number_of_triangles(input))
    
    
            
 
