#include<iostream>
#include<vector>
#include<list>
#include<stack>
using namespace std;
void depth_first_search(vector<list<int>> graph,int src){
    vector<bool>visited(graph.size(),false);
    stack<int>S;
    S.push(src);
    visited[src] = true;
    while(!S.empty()){
        int vertex = S.top(); S.pop();
        cout << vertex << " ";
        for(list<int>::iterator itr = graph[vertex].begin();itr!=graph[vertex].end();itr++){
            if(!visited[*itr])
                S.push(*itr);
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
    depth_first_search(graph,src);
    return 0;
}
