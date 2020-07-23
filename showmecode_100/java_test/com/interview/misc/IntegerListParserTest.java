package com.interview.misc;

import org.junit.Assert;
import org.junit.Test;

public class IntegerListParserTest {

    @Test
    public void testDifferentCases() {
        IntegerListParser integerListParser = new IntegerListParser();
        IntegerListParser.NestedInteger nestedInteger = integerListParser.deserialize("123");
        String result = integerListParser.serialize(nestedInteger);
        Assert.assertEquals("123", result);
        nestedInteger = integerListParser.deserialize("[]");
        result = integerListParser.serialize(nestedInteger);
        Assert.assertEquals("[]", result);
        nestedInteger = integerListParser.deserialize("[123]");
        result = integerListParser.serialize(nestedInteger);
        Assert.assertEquals("[123]", result);
        nestedInteger = integerListParser.deserialize("[123,41]");
        result = integerListParser.serialize(nestedInteger);
        Assert.assertEquals("[123,41]", result);
        nestedInteger = integerListParser.deserialize("[123,41,[1]]");
        result = integerListParser.serialize(nestedInteger);
        Assert.assertEquals("[123,41,[1]]", result);
        nestedInteger = integerListParser.deserialize("[123,41,[[[]]]]");
        result = integerListParser.serialize(nestedInteger);
        Assert.assertEquals("[123,41,[[[]]]]", result);
        nestedInteger = integerListParser.deserialize("[123,41,[[[],[]]],[],[]]");
        result = integerListParser.serialize(nestedInteger);
        Assert.assertEquals("[123,41,[[[],[]]],[],[]]", result);
        nestedInteger = integerListParser.deserialize("[123,41,[[[121,41,[1]],[2]]],[2],[4]]");
        result = integerListParser.serialize(nestedInteger);
        Assert.assertEquals("[123,41,[[[121,41,[1]],[2]]],[2],[4]]", result);
        nestedInteger = integerListParser.deserialize("[123,41,[[1,2,[],[]]],[],[],[[1],[3]]]");
        result = integerListParser.serialize(nestedInteger);
        Assert.assertEquals("[123,41,[[1,2,[],[]]],[],[],[[1],[3]]]", result);
    }
}
