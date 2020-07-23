int lis(int array[],int n){
    int dp[n],lis_value = -1;
    for(int i=0;i<n;i++){
        dp[i] = 1;
    }
    for(int i=1;i<n;i++){
        for(int j=0;j<i;j++){
            if(array[i] > array[j] and dp[i] < dp[j]+1){
                dp[i] = dp[j] + 1;
            }
        }
    }
    for(int i=0;i<n;i++){
        if(lis_value < dp[i]){
            lis_value = dp[i];
        }
    }
    return lis_value;
}
