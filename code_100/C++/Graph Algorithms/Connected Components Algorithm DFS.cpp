#include<iostream>
#include<vector>
#include<list>
using namespace std;

void connectedComponentsDFS(vector<list<int>> graph,int src,vector<bool> &visited){
    if(!visited[src]){
        visited[src] = true;
        for(list<int>::iterator itr = graph[src].begin();itr != graph[src].end();itr++){
            connectedComponentsDFS(graph,*itr,visited);
        }
    }
}

int connectedComponents(vector<list<int>> graph){
    int components = 0;
    vector<bool> visited(graph.size(),false);
    for(int src = 0; src < graph.size();src++){
        if(!visited[src]){
            components++;
            connectedComponentsDFS(graph,src,visited);
        }
    }
    return components;
}

int main(){
    vector<list<int>> graph;
    int v,e,src,des;
    cin >> v >> e;
    graph.resize(v);
    while(e--){
        cin >> src >> des;
        graph[src].push_back(des);
    }
    cout << connectedComponents(graph);
    return 0;
}

