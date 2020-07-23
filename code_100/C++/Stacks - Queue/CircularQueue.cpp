#include <iostream>
using namespace std;
class circular_queue
{
	private :
		int *array ;
		int front, back ;
		int MAX;
	public :
		circular_queue( int maxsize = 10 ) ;
		void enqueue ( int item ) ;
		int dequeue( ) ;
		void display( ) ;
} ;
circular_queue :: circular_queue( int maxsize )
{
	MAX = maxsize ;
	array = new int [ MAX ];
	front = back = -1 ;
	for ( int i = 0 ; i < MAX ; i++ )
		array[i] = 0 ;
}
void circular_queue :: enqueue(int item){
	if((back+1)%MAX == front){
		cout << "Queue is full" << endl;
		return ;
	}
	back = ( back + 1 ) % MAX;
	array[back] = item ;
	if ( front == -1 )
		front = 0 ;
}
int circular_queue :: dequeue(){
	int data ;
	if ( front == -1 )
	{
		cout << "\nQueue is empty" ;
		return NULL ;
	}

	data = array[front] ;
	array[front] = 0 ;
	if ( front == back )
	{
		front = -1 ;
		back = -1 ;
	}
	else
		front = ( front + 1 ) % MAX;
	return data ;
}
void circular_queue :: display()
{
	cout << endl ;
	for ( int i = 0 ; i < MAX ; i++ )
		cout << array[i] << "  " ;
	cout << endl ;
}
int main(){
	circular_queue cq(10) ;
	cq.enqueue(14);
	cq.enqueue(22);
	cq.enqueue(13);
	cq.enqueue(-6);
	cq.enqueue(25);
	cout << "\nElements in the circular queue: " ;
	cq.display();
	int i = cq.dequeue() ;
	cout << "Item deleted: " << i ;
	i = cq.dequeue();
	cout << "\nItem deleted: " << i ;
	cout << "\nElements in the circular queue after deletion: " ;
	cq.display();
	cq.enqueue(21);
	cq.enqueue(17);
	cq.enqueue(18);
	cq.enqueue(9);
	cq.enqueue(20);
	cout << "Elements in the circular queue after addition: " ;
	cq.display();
	cq.enqueue(32);
	cout << "Elements in the circular queue after addition: " ;
	cq.display();
	return 0;
}

