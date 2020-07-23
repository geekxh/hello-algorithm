#include <iostream>
#include <queue>
using namespace std;

int main(){
    int n,k;
    priority_queue<int,vector<int>,greater<int>>Q;
    cout << "Enter the the value of K : ";
    cin >> k;
    while(cin >> n){
        cout << k << "-th largest element of the stream : ";
        if(Q.size() < k){
            Q.push(n);
            if(Q.size() == k){
                cout  << Q.top() 3<< endl;
            }
            else{
                cout << "NULL" << endl;
            }
        }
        else{
            if(Q.top() < n){
                Q.pop();
                Q.push(n);
            }
            cout << Q.top() << endl;
        }
        cout << "Enter next element of the stream : ";
    }
    return  0;
}
