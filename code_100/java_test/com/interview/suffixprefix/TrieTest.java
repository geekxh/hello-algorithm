package com.interview.suffixprefix;

import org.junit.Assert;
import org.junit.Test;

public class TrieTest {

    @Test
    public void testDifferentCases() {
        Trie trie = new Trie();

        trie.insert("abcd");
        trie.insert("abgl");
        trie.insertRecursive("lmn");
        trie.insertRecursive("lmnpq");
        trie.insert("cdeg");
        trie.insert("ghijk");

        Assert.assertFalse(trie.search("ab"));
        Assert.assertFalse(trie.search("abc"));
        Assert.assertTrue(trie.search("abcd"));
        Assert.assertFalse(trie.search("abg"));
        Assert.assertTrue(trie.search("abgl"));
        Assert.assertFalse(trie.search("lm"));
        Assert.assertTrue(trie.search("lmn"));
        Assert.assertFalse(trie.search("lmnp"));
        Assert.assertTrue(trie.search("lmnpq"));

        trie.delete("abcd");
        Assert.assertTrue(trie.search("abgl"));
        Assert.assertFalse(trie.search("abcd"));

        trie.delete("lmn");
        Assert.assertFalse(trie.search("lmn"));
        Assert.assertTrue(trie.search("lmnpq"));

        trie.delete("lmnpq");
        Assert.assertFalse(trie.search("lmnpq"));

    }
}
