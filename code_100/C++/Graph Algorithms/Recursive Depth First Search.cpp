#include<iostream>
#include<vector>
#include<list>
using namespace std;
void depth_first_search(vector<list<int>> graph,int src,vector<bool> &visited){
    if(!visited[src]){
        cout << src << " ";
        visited[src] = true;
        for(list<int>::iterator itr = graph[src].begin();itr != graph[src].end();itr++){
            depth_first_search(graph,*itr,visited);
        }
    }
}
int main(){
    vector<list<int>> graph;
    vector<bool> visited;
    int v,e,src,des;
    cin >> v >> e;
    graph.resize(v);
    visited.resize(v,false);
    while(e--){
        cin >> src >> des;
        graph[src].push_back(des);
    }
    cin >> src;
    depth_first_search(graph,src,visited);
    return 0;
}
