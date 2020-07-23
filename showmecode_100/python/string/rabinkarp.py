#Rabin Karp algorithm
# Java code https://github.com/mission-peace/interview/blob/master/src/com/interview/string/RabinKarpSearch.java

prime = 101
def pattern_matching(text, pattern):
    m = len(pattern)
    n = len(text)
    pattern_hash = create_hash(pattern, m - 1)
    text_hash = create_hash(text, m - 1)

    for i in range(1, n - m + 2):
        if pattern_hash == text_hash:
            if check_equal(text[i-1:i+m-1], pattern[0:]) is True:
                return i - 1;
        if i < n - m + 1:    
            text_hash = recalculate_hash(text, i-1, i+m-1, text_hash, m)
    return -1;
    
def check_equal(str1, str2):
    if len(str1) != len(str2):
        return False;
    i = 0
    j = 0
    for i, j in zip(str1, str2):
        if i != j:
            return False;
    return True
    
def create_hash(input, end):
    hash = 0
    for i in range(end + 1):
        hash = hash + ord(input[i])*pow(prime, i)
    return hash

def recalculate_hash(input, old_index, new_index, old_hash, pattern_len):
    new_hash = old_hash - ord(input[old_index])
    new_hash = new_hash/prime
    new_hash += ord(input[new_index])*pow(prime, pattern_len - 1)
    return new_hash;


index = pattern_matching("TusharRoy", "sharRoy")
print("Index ", index)
index = pattern_matching("TusharRoy", "Roy")
print("Index ", index)
index = pattern_matching("TusharRoy", "shar")
print("Index ", index)
index = pattern_matching("TusharRoy", "usha")
print("Index ", index)
index = pattern_matching("TusharRoy", "Tus")
print("Index ", index)
index = pattern_matching("TusharRoy", "Roa")
print("Index ", index)
