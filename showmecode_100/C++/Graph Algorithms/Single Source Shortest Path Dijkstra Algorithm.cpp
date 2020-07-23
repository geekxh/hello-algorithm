#include<iostream>
#include<queue>
#include<vector>
#include<list>
#include<climits>
using namespace std;
struct compare{
    bool operator()(const pair<int,int> &a,const pair<int,int> &b){
        return a.second > b.second;
    }
};
vector<int> dijkshtra(vector<list<pair<int,int>>> graph,int src){
    priority_queue<pair<int,int>,vector<pair<int,int>>,compare> Q;
    vector<int> distances(graph.size(),INT_MAX);
    vector<bool> visited(graph.size(),false);
    distances[src] = 0;
    Q.push(make_pair(src,0));
    while(!Q.empty()){
        pair<int,int> current = Q.top(); Q.pop();
        cout << "Currently at" << current.first << endl;
        if(!visited[current.first]){
            visited[current.first] = true;
            for(list<pair<int,int>> :: iterator vertex = graph[current.first].begin();vertex != graph[current.first].end();vertex++){
                if(current.second + vertex->second < distances[vertex->first]){
                    distances[vertex->first] = current.second + vertex->second;
                    Q.push(make_pair(vertex->first,distances[vertex->first]));
                }
            }
        }
    }
    return distances;
}
int main(){
    vector<list<pair<int,int>>> graph;
    int v,e,src,des,weight;
    cin >> v >> e;
    graph.resize(v);
    while(e--){
        cin >> src >> des >> weight;
        graph[src].push_back(make_pair(des,weight));
    }
    cin >> src;
    vector<int> distances = dijkshtra(graph,src);
    for(vector<int> :: iterator itr = distances.begin();itr != distances.end();itr++){
        cout << *itr << " ";
    }
    return 0;
}
