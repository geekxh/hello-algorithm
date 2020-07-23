#include<iostream>
#include<vector>
using namespace std;

void floydWarshall(vector<vector<int>> &graph){
    for (int k=0; k<graph.size(); ++k){
        for (int i=0; i<graph.size(); ++i){
            for (int j=0; j<graph.size(); ++j){
                graph[i][j] = min (graph[i][j], graph[i][k] + graph[k][j]);
            }
        }
    }
}

int main(){
    vector<vector<int>> graph;
    int v,e,src,des,weight;
    cin >> v >> e;
    graph.resize(v,vector<int>(v,0));
    while(e--){
        cin >> src >> des >> weight;
        graph[src][des] = weight;
    }
    floydWarshall(graph);
    for(int i=0;i<v;i++){
        for(int j=0;j<v;j++){
            cout << graph[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}
