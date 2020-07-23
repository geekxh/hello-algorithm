#include<iostream>
using namespace std;

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


int main(){
    UnionFind uf(5);
    cout << uf.size() << endl;        // 5 disjoint sets are there
    uf.merge(0,1);
    cout << uf.size() << endl;        // 4 disjoint sets are there
    uf.merge(0,2);
    cout << uf.size() << endl;        // 3 disjoint sets are there
    uf.merge(1,2);
    cout << uf.size() << endl;        // 3 disjoint sets are there
    uf.clear();
    return 0;
}
