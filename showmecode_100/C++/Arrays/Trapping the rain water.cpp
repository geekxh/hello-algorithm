#include <iostream>
using namespace std;

int trapped_water(int array[],int size){
    int amount = 0;
    int left[size],right[size];
    left[0] = array[0]; right[size-1] = array[size-1];
    for(int i = 1; i < size; i++){
        left[i] = max(left[i-1],array[i]);
    }
    for(int i = size-2; i >=0; i--){
        right[i] = max(right[i+1],array[i]);
    }
    for(int i = 0  ; i < size;i++){
        amount += min(left[i],right[i]) - array[i];
    }
    return  amount;
}

int main(){
    int array[] = {1,0,3,4,5,0,5,7,7,8,9,0};
    int size = sizeof(array) / sizeof(int);
    cout << trapped_water(array,size);
    return  0;
}
