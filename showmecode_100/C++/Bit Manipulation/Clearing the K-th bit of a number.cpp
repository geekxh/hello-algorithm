#include<iostream>
using namespace std;
int main(){
    int n,k,mask;
    cout << "Enter the number and the value of K : ";
    cin >> n >> k;
    mask = ~(1 << (k-1));
    n = n&mask;
    cout << "The number after clearing the K-th bit is : " << n << endl;
    return 0;
}
