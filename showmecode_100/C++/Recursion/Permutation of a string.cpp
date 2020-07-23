#include <iostream>
using namespace std;

void permutation(char str[],int k,int n){
    if(k == n){
        for(int j = 0; j < n; j++){
            cout << str[j];
        }
        cout << endl;
    }
    else{
        for(int i = k ; i < n; i++){
            swap(str[i],str[k]);
            permutation(str,k+1,n);
            swap(str[i],str[k]);
        }
    }
}
int main(){
    char str[] = {'A','B','C','D'};
    permutation(str,0,4);
    return  0;
}
