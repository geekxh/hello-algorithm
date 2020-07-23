#include<iostream>
using namespace std;
int main(){
    int n;
    cout << "Enter the number : ";
    cin >> n ;
    n = n & (n-1);
    cout << "The number after toggling right most set bit : " << n << endl;
    return 0;
}
