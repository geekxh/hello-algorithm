#include<iostream>
using namespace std;
int main(){
    int n,k;
    cout << "Enter the number and the value of K :";
    cin >> n >> k;
    int mask = 1 << (k - 1);
    n = n | mask;
    cout << "The number after setting the K-th bit is:" << n;
    return 0;
}
