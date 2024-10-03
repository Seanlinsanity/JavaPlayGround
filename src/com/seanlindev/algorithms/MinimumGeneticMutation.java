package com.seanlindev.algorithms;

import java.util.*;

/*
A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.

Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.

For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.

Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.

Note that the starting point is assumed to be valid, so it might not be included in the bank.



Example 1:

Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1
Example 2:

Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
Output: 2


Constraints:

0 <= bank.length <= 10
startGene.length == endGene.length == bank[i].length == 8
startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 */
public class MinimumGeneticMutation {
    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)) { return 0; }
        if (bank.length == 0) { return -1; }

        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        if (!bankSet.contains(endGene)) { return -1; }

        Set<String> visited = new HashSet<>();
        Queue<char[]> queue = new LinkedList<>();
        queue.add(startGene.toCharArray());
        visited.add(startGene);
        char[] geneChars = new char[]{ 'A', 'C', 'G', 'T' };
        int count = 0; //count=0
        while (queue.size() > 0) { //queue=[AAAAACCC]
            int size = queue.size();
            count += 1; //count=1
            for (int i = 0; i < size; i++) {
                char[] node = queue.remove();
                for (int j = 0; j < node.length; j++) { //j=0
                    char current = node[j]; //current=A
                    for (char gene: geneChars) {
                        if (node[j] == gene) { continue; }

                        node[j] = gene;
                        String nextGene = String.valueOf(node);
                        if (nextGene.equals(endGene)) {
                            return count;
                        }
                        if (bankSet.contains(nextGene) && !visited.contains(nextGene)) {
                            visited.add(nextGene);
                            queue.add(Arrays.copyOfRange(node, 0, node.length));
                        }

                        node[j] = current;
                    }
                }
            }

        }

        return -1;
    }
}
