int mcm(int p[], int n){
    int m[n][n];
    int i, j, k, L, q;
    for (i = 1; i < n; i++)
        m[i][i] = 0;
    for (L=2; L<n; L++)   {
        for (i=1; i<=n-L+1; i++){
            j = i+L-1;
            m[i][j] = INT_MAX;
            for (k=i; k<=j-1; k++){
                q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                if (q < m[i][j])
                    m[i][j] = q;
            }
        }
    }
    return m[1][n-1];
}
