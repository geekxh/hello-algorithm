#include <iostream>
using namespace std;
int longest_palindromic_subsequence(string str){
    int table[str.size()][str.size()];
    for(int i = 0 ; i < str.size(); i++){
        table[i][i] = 1;
    }
    for(int l = 1 ; l < str.size() ; l++){
        int i = 0, j = l;
        while(j != str.size()){
            if(str[i] == str[j]){
                table[i][j] = 2 + table[i+1][j-1];
            }
            else{
                table[i][j] = max(table[i+1][j],table[i][j-1]);
            }
            i++;j++;
        }
    }
    return table[0][str.size()-1];
}
int main(){
    string str;
    cin >> str;
    cout << longest_palindromic_subsequence(str);
    return  0;
}