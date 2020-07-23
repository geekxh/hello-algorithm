struct Trie {
	Trie* child[26];
	bool isLeaf;

	Trie() {
		memset(child, 0, sizeof(child));
		isLeaf = 0;
	}

	void pushWord(char *str) {
		if(*str == '\0')
			isLeaf = 1;
		else {
			int cur = *str - 'a';
			if(child[cur] == 0 )
				child[cur] = new Trie();
			child[cur]->pushWord(str+1);
		}
	}

	bool wordExist(char* str) {
		if(*str == '\0')
			return isLeaf;

		int cur = *str - 'a';
		if(child[cur] == 0 )
			return false;

		return child[cur]->wordExist(str+1);
	}

	bool prefixExist(char* str) {
		if(*str == '\0')
			return true;

		int cur = *str - 'a';
		if(child[cur] == 0 )
			return false;

		return child[cur]->prefixExist(str+1);
	}
};
