#include<iostream>
#include<vector>
#include<list>
#include<queue>
using namespace std;
void breadth_first_search(vector<list<int>> graph,int src){
    vector<bool>visited(graph.size(),false);
    queue<int>Q;
    Q.push(src);
    visited[src] = true;
    while(!Q.empty()){
        int vertex = Q.front(); Q.pop();
        cout << vertex << " ";
        for(list<int>::iterator itr = graph[vertex].begin();itr!=graph[vertex].end();itr++){
            if(!visited[*itr])
                Q.push(*itr);
            visited[*itr] = true;
        }
    }
}
int main(){
    vector<list<int>> graph;
    int v,e,src,des;
    cin >> v >> e;
    graph.resize(v);
    while(e--){
        cin >> src >> des;
        graph[src].push_back(des);
        graph[des].push_back(src);
    }
    cin >> src;
    breadth_first_search(graph,src);
    return 0;
}
