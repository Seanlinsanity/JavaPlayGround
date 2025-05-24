package com.seanlindev.algorithms;

/*
You are given an m x n matrix grid consisting of characters and a string pattern.

A horizontal substring is a contiguous sequence of characters read from left to right. If the end of a row is reached before the substring is complete, it wraps to the first column of the next row and continues as needed. You do not wrap from the bottom row back to the top.

A vertical substring is a contiguous sequence of characters read from top to bottom. If the bottom of a column is reached before the substring is complete, it wraps to the first row of the next column and continues as needed. You do not wrap from the last column back to the first.

Count the number of cells in the matrix that satisfy the following condition:

The cell must be part of at least one horizontal substring and at least one vertical substring, where both substrings are equal to the given pattern.
Return the count of these cells.



Example 1:


Input: grid = [["a","a","c","c"],["b","b","b","c"],["a","a","b","a"],["c","a","a","c"],["a","a","b","a"]], pattern = "abaca"

Output: 1

Explanation:

The pattern "abaca" appears once as a horizontal substring (colored blue) and once as a vertical substring (colored red), intersecting at one cell (colored purple).

Example 2:


Input: grid = [["c","a","a","a"],["a","a","b","a"],["b","b","a","a"],["a","a","b","a"]], pattern = "aba"

Output: 4

Explanation:

The cells colored above are all part of at least one horizontal and one vertical substring matching the pattern "aba".

Example 3:

Input: grid = [["a"]], pattern = "a"

Output: 1



Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
1 <= pattern.length <= m * n
grid and pattern consist of only lowercase English letters.
 */
public class CountCellsInOverlappingHorizontalVerticalSubstrings {
    int M = (int)(1e9 + 7);
    int prime = 67;

    private void solve(StringBuilder t, int k, long a, long b, boolean[] m) {
        int n = t.length();
        int[] diff = new int[n + 1];
        long h = 0;
        for (int i = 0; i < k; i++)
            h = (h * prime + t.charAt(i)) % M;

        if (h == a) {
            diff[0]++;
            diff[k]--;
        }
        for (int i = k; i < n; i++) {
            h = (h * prime - (long)t.charAt(i - k) * b % M + M) % M;
            h = (h + t.charAt(i)) % M;
            if (h == a) {
                int start = i - k + 1;
                diff[start]++;
                diff[start + k]--;
            }
        }
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur += diff[i];
            m[i] = cur > 0;
        }
    }

    private StringBuilder horizontal(char[][] g, int r, int c) {
        StringBuilder sb = new StringBuilder(r * c);
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                sb.append(g[i][j]);
        return sb;
    }

    private StringBuilder vertical(char[][] g, int r, int c) {
        StringBuilder sb = new StringBuilder(r * c);
        for (int j = 0; j < c; j++)
            for (int i = 0; i < r; i++)
                sb.append(g[i][j]);
        return sb;
    }

    long hash(String s, int L) {
        long h = 0;
        for (int i = 0; i < L; i++)
            h = (h * prime + s.charAt(i)) % M;
        return h;
    }

    long pow(long b, int e) {
        long res = 1;
        while (e > 0) {
            if ((e & 1) == 1) res = (res * b) % M;
            b = (b * b) % M;
            e >>= 1;
        }
        return res;
    }

    public int countCells(char[][] g, String p) {
        int n = g.length, m = g[0].length, k = p.length();
        if (k > n * m) return 0;
        StringBuilder sr = horizontal(g, n, m);
        StringBuilder sc = vertical(g, n, m);
        long a = hash(p, k);
        long b = pow(prime, k);
        boolean[] h = new boolean[n * m], v = new boolean[n * m];
        solve(sr, k, a, b, h);
        solve(sc, k, a, b, v);
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (h[i * m + j] && v[j * n + i])
                    ans++;
        return ans;
    }
}
