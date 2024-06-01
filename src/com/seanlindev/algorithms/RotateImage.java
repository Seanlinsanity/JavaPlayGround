package com.seanlindev.algorithms;

/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]


Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
 */
public class RotateImage {
     public void rotate(int[][] matrix) {
         int l = 0;
         int r = matrix.length - 1;

         while ( l < r ) {
             for(int i = 0; i < r - l; i++) {
                 int top = l;
                 int bottom = r;
                  //save the topleft
                 int topLeft = matrix[top][l + i];

                 //move bottom left into top left
                 matrix[top][l + i] = matrix[bottom - i][l];

                 // move bottom right into bottom left
                 matrix[bottom - i][l] = matrix[bottom][r - i];

                 // move top right into bottom right
                 matrix[bottom][r - i] = matrix[top + i][r];

                 // move top left into top right
                 matrix[top + i][r] = topLeft;

             }

             r -= 1;
             l += 1;
         }
     }

    public void rotate2(int[][] matrix) {
        int length = matrix.length;
        if (length == 1) { return; }

        int indexLength = length - 1;
        int size = length; //4
        int row = 0;
        while (size > 1) {
            //r=0,size=4
            //r=1,size=2
            for (int column = row; column < row + size - 1; column++) {
                int[] topLeft = {row, column}; //{1,1}
                int[] topRight = {column, indexLength - row}; //{1,2}
                int[] bottomRight = {indexLength - row, indexLength - column}; //{2,2}
                int[] bottomLeft = {indexLength - column, row}; //{2,1}
                int value1 = matrix[topLeft[0]][topLeft[1]];
                int value2 = matrix[topRight[0]][topRight[1]];
                int value3 = matrix[bottomRight[0]][bottomRight[1]];
                int value4 = matrix[bottomLeft[0]][bottomLeft[1]];
                matrix[topLeft[0]][topLeft[1]] = value4;
                matrix[topRight[0]][topRight[1]] = value1;
                matrix[bottomRight[0]][bottomRight[1]] = value2;
                matrix[bottomLeft[0]][bottomLeft[1]] = value3;
            }
            size -= 2; //2
            row += 1; //1
        }
    }
}
