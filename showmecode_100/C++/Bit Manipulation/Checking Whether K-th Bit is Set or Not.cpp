#include<iostream>
using namespace std;
int main(){
    int n,k;
    cout << "Enter the number and the value of K : ";
    cin >> n >> k;
    int mask = 1 << (k-1);
    if(n & mask){
        cout << "Yes K-th bit is set" << endl;
    }
    else{
        cout << "No K-th bit is not set" << endl;
    }
    return 0;
}
