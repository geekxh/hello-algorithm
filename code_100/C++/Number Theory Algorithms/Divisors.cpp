#include<iostream>
#include<set>
using namespace std;
set<int> generateDivisors(long long int num){
    set<int> divisors;
    for(int i = 1 ; i*i <= num; i++ ){
        if(num % i == 0){
            divisors.insert(i);
            if( i != num/i ){
                    divisors.insert(num/i);
            }
        }
    }
    return divisors;
}
int main(){
    set<int> d = generateDivisors(23);
    for(int x : d){
        cout << x << " ";
    }
    return 0;
}
