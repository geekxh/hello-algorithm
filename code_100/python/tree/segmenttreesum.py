def create_segment_tree(input):
    size = next_power_of_2(len(input));
    segment_tree = [0 for x in range(2*size - 1)]
    construct_tree(segment_tree, input, 0, len(input) - 1, 0)
    return segment_tree

def construct_tree(segment_tree, input, low, high, pos):
    if low == high:
        segment_tree[pos] = input[low]
        return

    mid = (low + high)/2
    construct_tree(segment_tree, input, low, mid, 2*pos + 1)
    construct_tree(segment_tree, input, mid + 1, high, 2*pos + 2)
    segment_tree[pos] = segment_tree[2*pos+1] + segment_tree[2*pos+2]

def sum_range_query(segment_tree, q_low, q_high, len):
    return sum_range_query_util(segment_tree, 0, len - 1, q_low, q_high, 0)

def sum_range_query_util(segment_tree, low, high, q_low, q_high, pos):
    if q_low <= low and q_high >= high:
        return segment_tree[pos]

    if q_high < low or q_low > high:
        return 0

    mid = (low + high)/2
    return sum_range_query_util(segment_tree, low, mid, q_low, q_high, 2*pos + 1)\
           + sum_range_query_util(segment_tree, mid + 1, high, q_low, q_high, 2*pos + 2)

def update_value(input, segment_tree, new_value, index):
    diff = new_value - input[index]
    input[index] = new_value
    update_value_util(segment_tree, 0, len(input)-1, diff, index, 0)

def update_value_util(segment_tree, low, high, diff, index, pos):
    if low > index or high < index:
        return
    segment_tree[pos] += diff

    if low >= high:
        return
    mid = (low + high)/2
    update_value_util(segment_tree, low, mid, diff, index, 2*pos + 1)
    update_value_util(segment_tree, mid + 1, high, diff, index, 2*pos + 2)


def next_power_of_2(n):
    if n == 0:
        return 1
    if n & (n - 1) == 0:
        return n
    while n & (n - 1) > 0:
        n &= (n - 1)

    return n << 1

if __name__ == '__main__':
    input = [1,3,5,7,9,11]
    segment_tree = create_segment_tree(input)
    print(segment_tree)
    print(sum_range_query(segment_tree, 2, 5, len(input)))
    print(sum_range_query(segment_tree, 1, 3, len(input)))
    update_value(input, segment_tree, 4, 3)
    print(sum_range_query(segment_tree, 2, 5, len(input)))
    print(sum_range_query(segment_tree, 1, 3, len(input)))


