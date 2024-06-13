package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.



Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]


Constraints:

1 <= s.length <= 20
s consists of digits only.
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        tracking(s, 0, new ArrayList<>(), result);
        return result;
    }

    void tracking(String s, int index, List<String> current, List<String> result) {
        if (current.size() == 4 && index == s.length()) {
            result.add(String.join(".", current));
            return;
        }

        if (current.size() == 4 || index == s.length()) { return; }

        String ip1 = s.substring(index, index + 1);
        current.add(ip1);
        tracking(s, index + 1, current, result);
        current.remove(current.size() - 1);
        if (index + 2 <= s.length() && !ip1.equals("0")) {
            String ip2 = s.substring(index, index + 2);
            current.add(ip2);
            tracking(s, index + 2, current, result);
            current.remove(current.size() - 1);
        }

        if (index + 3 <= s.length() && !ip1.equals("0") && !s.startsWith("00", index)) {
            String ip3 = s.substring(index, index + 3);
            if (Integer.valueOf(ip3) > 255) { return; }
            current.add(ip3);
            tracking(s, index + 3, current, result);
            current.remove(current.size() - 1);
        }
    }
}
