package com.interview.multiarray;

/**
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
 * The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally
 * and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest
 * (axis-aligned) rectangle that encloses all black pixels.
 * https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
 */
public class SmallestRectangleBlackPixel {
    public int minArea(char[][] image, int x, int y) {

        int m = image.length;
        int n = image[0].length;

        int left = searchColumns(image, 0, y, 0, m -  1, true);
        int right = searchColumns(image, y, n - 1, 0, m - 1, false);

        int top = searchRows(image, 0, x, left, right, true);
        int bottom = searchRows(image, x, m - 1, left, right, false);

        return (right - left + 1)*(bottom - top + 1);
    }

    private int searchColumns(char[][] image, int i, int j, int top, int bottom, boolean opt) {
        int result = 0;
        while (i <= j) {
            int k = top;
            int mid = (i + j)/2;
            while (k <= bottom && image[k][mid] == '0') {
                k++;
            }
            if (k != bottom + 1) {
                result = mid;
            }
            if ((k == bottom + 1) == opt) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return result;
    }

    private int searchRows(char[][] image, int i, int j, int left, int right, boolean opt) {
        int result = 0;
        while (i <= j) {
            int k = left;
            int mid = (i + j)/2;
            while (k <= right && image[mid][k] == '0') {
                k++;
            }
            if (k != right + 1) {
                result = mid;
            }
            if ((k == right + 1) == opt) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        char[][] image1 = {{'1'},{'1'}};
        char[][] image = {{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}};
        SmallestRectangleBlackPixel sbp = new SmallestRectangleBlackPixel();
        System.out.print(sbp.minArea(image, 0, 2));
    }
}
