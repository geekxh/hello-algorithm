package com.interview.string;

import org.junit.Assert;
import org.junit.Test;

public class ValidWordAbbreviationTest {

    @Test
    public void testDifferentCases() {
        String[] dict =  {"eersynoiyqkqubhdd","ylz","yldongowlrnsafafcgmz","rxcjc","dvgdtnknareecongydc","ixiwz","erjfyctsla","xovvrnbbvivbsuhb","bpslbpzbwphhwvhtcr","wptflnqkvekpkt","hwoiniqvfe","dgidjd","ecgsghxuesvqmhxe","kfgppbbvwfyp","qntzitqjuazrqwz","yjbycoyngfrreiyibdsk","nguqbtdistlgicmjfrs","yjqrqibgp","avulamdverhdpkpqjtae","cbwvtowh","yhgvjnvo","rihywmuspvzvp","rbkpetmovkwj","jtsehilffmfkicusup","ficdolmdtvk","qwldknj","hseogl","pdyxrfdxekk","xnrliooqnsqfzwgd","utnumabyrkasiizsjx","urygh","odj","nttcedxgpkjqzwfb","gxs","rkizfdlfmm","pjlz","fvjm","edkozzeuhxp","pbjcmakwqkdm","ppuhqdpaokitaowrzkfh","yimgbxllumgkbqadjjqf","sqsytssfjbaldz","llkw","aliw","hagvoxjnuuhsafkmxww","hahndehh","fqmrjyuogqpxyv","gnzbcrikf","auopjpwsepqwmend","xfgsbfafytrrkyevtz","acrtfozvjdvg","hspkwabiheogyk","uvlcpqien","eaamufqeal","wsvuyeysox","oywhf","kasdlmnj","fjpryefc","ftdqq","ftfqzqqig","dloh","tleszaz","yajyoyaxmmos","zbobgedfdpacbkzmxt","lmcj","dtjonkbwsg","xeiqjxvsfjdfddclnso","gpeutivfqwzfyrtax","wjoo","pptzwdcynnfpnirfkfo","wsudzgwuouof","ykjmbtoafrjjsehckh","oqhamskusmqofrsgkqfv","yifmkodzvk","vmufzdavpwjagmlcv","dtamuegujvtzdxui","nxdyotptdjdhst","rsthatscf","kdpwhmjtnkunabzaxv","ggzytma","sdypdz","xncvkmcddpkhkoalh","qnjndoizypqqqxewgla","czc","ojhzmxxceltwzg","hmwvynbzpuebokl","weuuontazzovia","ohwqtugyirw","lrtftghr","fngstcishaseslmb","athpsapnlyx","tcdnqc","fjfyvg","fneurgd","xddiwjfbshgfbbejmpe","ynscraxwlwsqhsioe","eaderhxrlwrjpp","wpnlrfxgnbfpuuiggsvo","ogqmzw","xai","fdtbvhaosybjczyfcsdx","abbcbqhcuoiaggs","qtdwhsqqjt","dqdvabloavvjhunafwhw","gcpqevfuos","hipvttjbniv","acheeyf","seqrnvez","hszzzvbvmhjg","kkwpshwuvsrbjqm","jqxo","sukagbkkrvbquzkfsj","axbrmcroycbyykkdhl","zrtshq","cgwssttvz","nbwccbisxtkccgmkmped","ivplojduvs","wmblfkhtnj","jeoodscttkmjrszzjgh","qmadddn","ssdauwepilwi","wghuntzaltedkacttafj","rxojnfrleq","qzkuejnvhncjzc","cromyllbcleqipqaitzd","yjdzifptqtcmrfyjrfj","svinvs","uftn"};
        ValidWordAbbreviation validWordAbbreviation = new ValidWordAbbreviation(dict);

        Assert.assertTrue(validWordAbbreviation.isUnique("hello"));
    }
}
