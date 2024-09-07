package com.seanlindev.algorithms;

import java.util.Stack;

/*
You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.

The rules of a Unix-style file system are as follows:

A single period '.' represents the current directory.
A double period '..' represents the previous/parent directory.
Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
The simplified canonical path should follow these rules:

The path must start with a single slash '/'.
Directories within the path must be separated by exactly one slash '/'.
The path must not end with a slash '/', unless it is the root directory.
The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
Return the simplified canonical path.



Example 1:

Input: path = "/home/"

Output: "/home"

Explanation:

The trailing slash should be removed.

Example 2:

Input: path = "/home//foo/"

Output: "/home/foo"

Explanation:

Multiple consecutive slashes are replaced by a single one.

Example 3:

Input: path = "/home/user/Documents/../Pictures"

Output: "/home/user/Pictures"

Explanation:

A double period ".." refers to the directory up a level (the parent directory).

Example 4:

Input: path = "/../"

Output: "/"

Explanation:

Going one level up from the root directory is not possible.

Example 5:

Input: path = "/.../a/../b/c/../d/./"

Output: "/.../b/d"

Explanation:

"..." is a valid name for a directory in this problem.
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        if (path.length() == 1) { return path; }

        Stack<String> stack = new Stack<>();

        // Split the input path string using '/' as a delimiter
        for (String item: path.split("/")) {
            if (item.equals("..")) {
                // If the component is '..', pop the last component from the stack
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!item.equals("") && !item.equals(".")) {
                // If the component is not empty and not '.', push it onto the stack
                stack.push(item);
            }
        }

        // Create a StringBuilder to build the simplified path
        StringBuilder result = new StringBuilder();

        for (String dir : stack) {
            // Insert '/' before each component to ensure correct path format
            result.append("/");
            result.append(dir);
        }

        return result.length() == 0 ? "/" : result.toString();
    }

    public String simplifyPath2(String path) {
        if (path.length() == 1) { return path; }

         Stack<String> stack = new Stack<>();
         for (char character: path.toCharArray()) {
             if (stack.isEmpty()) {
                 stack.push(String.valueOf(character));
                 continue;
             }

             if (character == '/') {
                 if (stack.peek().equals("..")) {
                     int remove = 0;
                     while (remove < 4 && stack.size() > 0) {
                         stack.pop();
                         remove += 1;
                     }
                 } else if (stack.peek().equals(".")) {
                     int remove = 0;
                     while (remove < 2 && stack.size() > 0) {
                         stack.pop();
                         remove += 1;
                     }
                 }

                 while (stack.size() > 0 && stack.peek().equals("/")) {
                     stack.pop();
                 }

                 stack.push("/");
             } else {
                 if (stack.peek().equals("/")) {
                     stack.push(String.valueOf(character));
                 } else {
                     String popValue = stack.pop();
                     stack.push(popValue + String.valueOf(character));
                 }
             }
         }

         if (stack.size() > 0) {
             if (stack.peek().equals("..")) {
                 int remove = 0;
                 while (remove < 4 && stack.size() > 0) {
                     stack.pop();
                     remove += 1;
                 }
             } else if (stack.peek().equals(".")) {
                 int remove = 0;
                 while (remove < 2 && stack.size() > 0) {
                     stack.pop();
                     remove += 1;
                 }
             }
         }

         if (stack.size() > 0 && stack.peek().equals("/")) { stack.pop(); }
         if (stack.isEmpty()) { return "/"; }

         StringBuilder builder = new StringBuilder();
         for (String str: stack) {
             builder.append(str);
         }

         return builder.toString();
    }
}
