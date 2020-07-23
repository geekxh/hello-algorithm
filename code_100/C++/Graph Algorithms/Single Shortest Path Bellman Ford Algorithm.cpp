#include<iostream>
#include<vector>
#include<climits>
using namespace std;

struct edge {int src, des, weight;};

pair<bool,vector<int>> bellmanFord(vector<edge> graph,int vertex,int source){
    vector<int> distances(vertex,INT_MAX);
    distances[source] = 0;
    for(int i=0;i<vertex-1;i++){
        for(int j=0;j<graph.size();j++){
            if(distances[graph[j].des] > distances[graph[j].src] + graph[j].weight){
                distances[graph[j].des] = distances[graph[j].src] + graph[j].weight;
            }
        }
    }
    for(int j=0;j<graph.size();j++){
        if(distances[graph[j].des] > distances[graph[j].src] + graph[j].weight){
            return make_pair(false,vector<int>());
        }
    }
    return make_pair(true,distances);
}

int main(){
    int edges,source,vertex;
    vector<edge> graph;
    cin >> edges >> vertex;
    for(int i = 0; i < edges; i++){
        cin >> graph[i].src >> graph[i].des >> graph[i].weight;
    }
    cin >> source;
    pair<bool,vector<int>> result = bellmanFord(graph,vertex,source);
    if(result.first == true){
        cout << "No Cycle Exist ! " << endl;
        for(vector<int>::iterator itr = (result.second).begin();itr!=(result.second).end();itr++){
            cout << *itr << " ";
        }
    }
    else{
        cout << "Graph Has Negative Weight Cycle" << endl;
    }
    return 0;
}
