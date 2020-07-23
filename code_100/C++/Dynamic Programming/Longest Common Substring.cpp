#include <iostream>
using namespace std;

int longest_common_substring(string x,string y){
    int m =  x.size();
    int n =  y.size();
    int lcs[m+1][n+1];
    for(int i = 0 ; i < m; i++){
        lcs[i][0] = 0;
    }
    for(int j = 0; j < n; j++){
        lcs[0][j] = 0;
    }
    for(int i = 1; i <= m; i++){
        for(int j = 1; j <=n; j++){
            if(x[i-1] == y[j-1]){
                lcs[i][j] = 1 + lcs[i-1][j-1];
            }
            else{
                lcs[i][j] = 0;
            }
        }
    }
    return lcs[m][n];
}
int main(){
    string x,y;
    cin >> x >> y;
    cout << longest_common_substring(x,y);
    return 0;
}