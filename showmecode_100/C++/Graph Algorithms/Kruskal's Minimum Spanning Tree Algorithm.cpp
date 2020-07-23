#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

struct edge{int src,des,weight;};

class UnionFind {
    int *parent, *ranks, _size;
public:
    UnionFind(){
    }
    UnionFind(int size){
        parent = new int[size]; ranks = new int[size];
        for(int element = 0 ; element < size ; element++){
            parent[element] = element , ranks[element] = 0 ;
        }
        _size = size;
    }
    void resize(int size){
        parent = new int[size]; ranks = new int[size];
        for(int element = 0 ; element < size ; element++){
            parent[element] = element , ranks[element] = 0 ;
        }
        _size = size;
    }
    int find(int element){
        if(parent[element] == element){
            return element;
        }
        else{
            return parent[element] = find(parent[element]);          // Path Compression algorithm
        }
    }
    bool connected(int x,int y){
        if(find(x) == find(y)){
            return true;
        }
        else{
            return false;
        }
    }
    void merge(int x,int y){
        x = find(x);
        y = find(y);
        if(x != y){                                                   // Union by Rank algorithm
            if(ranks[x] > ranks[y]){
                parent[y] = x;
            }
            else if(ranks[x] < ranks[y]){
                parent[x] = y;
            }
            else{
                parent[x] = y; ranks[y] ++ ;
            }
            _size--;
        }
    }
    void clear(){
        delete [] parent; delete [] ranks;
    }
    int size(){
        return _size;
    }
};
bool comparator(const edge &a,const edge &b){
    return a.weight < b.weight;
}

vector<edge> kruskalsAlgorithm(vector<edge>graph,int vertices){
    UnionFind uf(vertices);
    vector<edge>spanningTree;
    sort(graph.begin(),graph.end(),comparator);
    spanningTree.push_back(graph[0]);
    uf.merge(graph[0].src,graph[0].des);
    for(int i=1;i<graph.size();i++){
        if(!uf.connected(graph[i].src,graph[i].des)){
            uf.merge(graph[i].src,graph[i].des);
            spanningTree.push_back(graph[i]);
        }
    }
    return spanningTree;
}
int main(){
    vector<edge>graph;
    int e,v;
    cin >> e >> v;
    graph.resize(e);
    for(int i=0;i<e;i++){
        cin >> graph[i].src >> graph[i].des >> graph[i].weight;
    }
    vector<edge> spanningTree = kruskalsAlgorithm(graph,v);
    for(edge x : spanningTree){
        cout << x.src << " " << x.des << " " << x.weight << endl;
    }
    return 0;
}
