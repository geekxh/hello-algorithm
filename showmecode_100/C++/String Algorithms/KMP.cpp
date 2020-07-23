vector<int> computePrefix(string pat){
    int m = pat.size();
    vector<int> longestPrefix(m);
    for(int i = 1, k = 0; i < m; i++){
        while(k > 0 && pat[k] != pat[i]){
            k = longestPrefix[k - 1];
        }
        if(pat[i] == pat[k]){
            longestPrefix[i] = ++k;
        }
        else{
            longestPrefix[i] = k;
        }
    }
    return longestPrefix;
}
void KMP(string str,string pat){
    int n = str.size();
    int m = pat.size();
    vector<int> longestPrefix = computePrefix(pat);
    for(int i = 0, k = 0; i < n; i++){
        while(k > 0 && pat[k] != str[i]){
            k = longestPrefix[k - 1];
        }
        if(str[i] == pat[k]){
            k++;
        }
        if(k == m){
            cout << i - m + 1 << "\n";
            k = longestPrefix[k - 1];
        }
    }
}
