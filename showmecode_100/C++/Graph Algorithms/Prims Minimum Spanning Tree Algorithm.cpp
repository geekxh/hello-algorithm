#include<iostream>
#include<climits>
#include<vector>
#include<queue>
#include<list>
using namespace std;

const int INF = INT_MAX;

class edge{ public: int src,des,weight; edge(){}edge(int s,int d,int w): src(s),des(d),weight(w){}};
class compare { public: bool operator()(const edge &a,const edge &b){ return a.weight < b.weight; }};

vector<edge> primsAlgorithm(vector<list<pair<int,int>>> graph,edge minEdge){
    vector<edge>spanningTree;
    priority_queue<edge,vector<edge>,compare> Q;
    while(spanningTree.size() == graph.size()-1){
        spanningTree.push_back(minEdge);
        for(list<pair<int,int>>::iterator it = graph[minEdge.src].begin();it!=graph[minEdge.src].end();it++){
            Q.push(edge(minEdge.src,it->first,it->second));
        }
        for(list<pair<int,int>>::iterator it = graph[minEdge.des].begin();it!=graph[minEdge.des].end();it++){
            Q.push(edge(minEdge.des,it->first,it->second));
        }
        minEdge = Q.top(); Q.pop();
    }
    return spanningTree;
}

int main(){
    vector<list<pair<int,int>>>graph;
    int v,e,src,des,weight;
    cin >> v >> e;
    graph.resize(v);
    edge minEdge;
    minEdge.weight = INF;
    while(e--){
        cin >> src >> des >> weight;
        graph[src].push_back(make_pair(des,weight));
        graph[des].push_back(make_pair(src,weight));
        if(weight < minEdge.weight){
            minEdge.src = src, minEdge.des = des, minEdge.weight = weight;
        }
    }
    vector<edge> spanningTree = primsAlgorithm(graph,minEdge);
    for(edge x : spanningTree){
        cout << x.src << " " << x.des << " " << x.weight << endl;
    }
    return 0;
}
