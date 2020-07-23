#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

const int MAX = 1000*1000;
const int LMT = 1000;

vector<bool> prime(MAX+1,true);

int seiveEratosthenes(){
    prime[0] = prime[1] = false;
    for(int i = 2; i <= LMT; i++){
        if(prime[i]){
            for(int j = i + i; j <= MAX ; j += i){
                prime[j] = false;
            }
        }
    }
    return count_if(prime.begin(),prime.end(),[](bool p){ return p == true;});
}
int main(){
    cout << seiveEratosthenes();
    return 0;
}
