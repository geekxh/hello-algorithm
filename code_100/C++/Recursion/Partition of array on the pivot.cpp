#include<iostream>
using namespace std;
void partition(int array[],int low,int high){
    int i = low-1, pivot = array[high-1];
    for(int j = low ; j < high ; j++){
        if(array[j] <= pivot){
            i++;
            swap(array[i],array[j]);
        }
    }
    swap(array[i+1],array[high-1]);
}
int main(){
    int n;
    cin >> n;
    int array[n];
    partition(array,0,n);
    return 0;
}
