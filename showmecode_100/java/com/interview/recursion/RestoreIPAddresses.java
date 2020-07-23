package com.interview.recursion;

import java.util.*;

/**
 * Date 04/04/2016
 * @author Tushar Roy
 *
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * Reference
 * https://leetcode.com/problems/restore-ip-addresses/
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        List<String> current = new ArrayList<>();
        restoreIpAddressesUtil(s, 0, 0, result, current);
        return result;
    }

    private void restoreIpAddressesUtil(String s, int start, int count, List<String> result, List<String> current) {
        if (start == s.length() && count == 4) {
            StringBuffer stringBuffer = new StringBuffer(current.get(0));
            for (int i = 1; i < current.size(); i++) {
                stringBuffer.append(".").append(current.get(i));
            }
            result.add(stringBuffer.toString());
            return;
        } else if (start == s.length() || count == 4) {
            return;
        }
        for (int i = start; i < s.length() && i < start + 3; i++) {
            if (i != start && s.charAt(start) == '0') {
                break;
            }
            String ip = s.substring(start, i + 1);
            if (Integer.valueOf(ip) > 255) {
                continue;
            }
            current.add(ip);
            restoreIpAddressesUtil(s, i + 1, count + 1, result, current);
            current.remove(current.size() - 1);
        }
    }
}
