void buildTree (int tree[],int array[], int index, int low, int high) {
	if (low == high)
		tree[index] = array[low];
	else {
		int mid = (low + high) >>  1;
		buildTree (tree,array, index*2, low, mid);
		buildTree (tree,array, index*2+1, mid+1, high);
		tree[index] = tree[index*2] + tree[index*2+1];
	}
}
int rangeQuery (int tree[],int index, int low, int high, int l, int r) {
	if (l > r)
		return 0;
	if (l == low && r == high)
		return tree[index];
	int mid = (low + high) >>  1;
	return rangeQuery (tree,index*2, low, mid, l, min(r,mid))
		+ rangeQuery (tree,index*2+1, mid+1, high, max(l,mid+1), r);
}
void updateQuery (int tree[],int index, int low, int high, int pos, int delta) {
	if (low == high)
		tree[index] = delta;
	else {
		int mid = (low + high) >> 1;
		if (pos <= mid)
			updateQuery (tree,index*2, low, mid, pos, delta);
		else
			updateQuery (tree,index*2+1, mid+1, high, pos, delta);
		tree[index] = tree[index*2] + tree[index*2+1];
	}
}
