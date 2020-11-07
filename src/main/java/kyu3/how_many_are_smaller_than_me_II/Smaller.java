package kyu3.how_many_are_smaller_than_me_II;

public class Smaller {

    public static class Node {
        private int dup;
        private int val;
        private int sum;
        private Node right;
        private Node left;

        private Node (int v, int s) {
            this.dup = 1;
            this.val = v;
            this.sum = s;
            this.right = null;
            this.left = null;
        }
    }

    private static Node insert(int num, Node node, int[] ans, int i, int preSum) {
        if(node == null){
            node = new Node(num, 0);
            ans[i] = preSum;
        }
        else if(node.val == num){
            node.dup++;
            ans[i] = preSum + node.sum;
        }
        else if(node.val > num){
            node.sum++;
            node.left = insert(num, node.left, ans, i, preSum);
        }
        else {
            node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);
        }
        return node;
    }

    public static int[] smaller(int[] unsorted) {
        if (unsorted.length == 0) return unsorted;
        final int[] result = new int[unsorted.length];
        Node root = null;
        for (int i = unsorted.length - 1; i >= 0; i--) {
            root = insert(unsorted[i], root, result, i , 0);
        }
        return result;
    }
}
