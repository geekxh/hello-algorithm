/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2019/01/24
 * @description
 */
public class Solution {
    
    public int FirstNotRepeatingChar(String str) {

        if (str == null || str.length() == 0) {
            return -1;
        }

        Map<Character, Integer> characterMap = new HashMap<>();
        // hashMap is HashTable，search cost O(1)
        for (int i = 0; i < str.length(); i++) {
            characterMap.put(str.charAt(i), characterMap.getOrDefault(str.charAt(i), 0) + 1);
        }

        for (int i = 0; i < str.length(); i++) {
            if (characterMap.get(str.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }
}