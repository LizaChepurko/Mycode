package BinaryTrees;

import LinkedList.MyList;

import java.util.ArrayList;

public class MainOfBT {
    public static <T> void main(String[] args) throws Exception {

        BinaryTree1<Integer> bt1 = new BinaryTree1<Integer>();
        bt1.add(1);
        bt1.add(2);
        bt1.add(3);
        bt1.add(4);
        bt1.add(5);
        bt1.add(6);
        bt1.add(7);
        bt1.add(8);
        bt1.add(9);
        TreePrinter(bt1);
        System.out.println("-------");
        System.out.println(ArrayofEvenLeaves(bt1));

    }
    public static void printArr(int[] arr) {
        if (arr != null) {
            System.out.print("[");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + ",");
            }
            System.out.print("]");
        }

    }

    //Exam B
    public static int minLevel(BinaryTree bt) {
        if(bt == null){return -1;}
        int l = minLevel(bt.getLeft());
        int r = minLevel(bt.getRight());
        return 1+ Math.min(l,r);

    }
    // set == add
    public static <T> void addClosest(BinaryTree<T> bt, T c){
        addClosestH(bt,c,minLevel(bt));
    }

    private static <T> void addClosestH(BinaryTree<T> bt, T c, int j) {
        if(j == 0){
            bt.add(c);
        }
        else{
            if(minLevel(bt.getLeft()) <= minLevel(bt.getRight())){
                addClosestH(bt.getLeft(), c , j-1);
            }
            else{
                addClosestH(bt.getRight(), c , j-1);
            }
        }
    }
    public static int minLevelLeaf(BinaryTree bt) {
        int ans = -1;
        if (bt != null) {
            if (bt.size() == 1) {
                ans = 0;
            } else {
                if (bt.getLeft() == null && bt.getRight() != null) {
                    return 1+ minLevelLeaf(bt.getRight());
                }
                if (bt.getLeft() != null && bt.getRight() == null) {
                    return 1+ minLevelLeaf(bt.getLeft());
                }
                if (bt.getLeft() != null && bt.getRight() != null) {
                    return 1 + Math.min(minLevelLeaf(bt.getLeft()),minLevelLeaf(bt.getRight()));
                }
            }
        }
        return ans;
    }
    public static <T> int count(BinaryTree bt, T c){
        if(bt == null){return 0;}
        if(bt.getRoot().equals(c)){return 1;}
        return 1+ count(bt.getLeft(), c) + count(bt.getRight(),c);
    }
    public static <T> int LeftSon(BinaryTree bt){
        if(bt == null ||bt.size() == 1){return 0;}
        if(bt.getLeft() != null && bt.getRight() == null ){return 1;}
        else {
            return LeftSon(bt.getLeft()) + LeftSon(bt.getRight());
        }
    }
    public static <T> int EvenLevels(BinaryTree bt){
        return EvenLevelsH(bt,0);
    }

    private static int EvenLevelsH(BinaryTree bt, int n) {
        if(bt == null){return 0;}
        if(bt.getRight() == null && bt.getLeft() == null && n%2 == 0){return 1;}
        else{
            int l = EvenLevelsH(bt.getLeft(), n+1);
            int r = EvenLevelsH(bt.getRight(), n+1);
            return l+r;
            }

    }
    public static <T> ArrayList<T> ArrayofEvenLeaves(BinaryTree<T> bt){
        ArrayList<T> ans = new ArrayList<>();
        return ArrayofEvenLeavesH(bt,ans,0);

    }

    private static <T> ArrayList<T> ArrayofEvenLeavesH(BinaryTree<T> bt, ArrayList<T> ans, int n) {
        if(bt == null){return ans;}
        if(bt.getLeft() == null && bt.getRight() == null && n%2 == 0){
            ans.add(bt.getRoot());
        }
        else{
            ArrayofEvenLeavesH(bt.getLeft(),ans,n+1);
            ArrayofEvenLeavesH(bt.getRight(),ans,n+1);
        }
        return ans;
    }


    public static <T> ArrayList<T> sortedArray(BinaryTree<T> bt, int s) throws Exception {
        ArrayList<T> a = new ArrayList<>();
        if(s == 1){
            return InorderSort(bt,a);
        }
        if(s == 2){
            return PreorderSort(bt,a);
        }
        if(s == 3){
            return PostorderSort(bt,a);
        }
        else{
            throw new Exception("You should enter number between 1-3");
        }
    }


    private static <T> ArrayList<T> InorderSort(BinaryTree<T> bt, ArrayList<T> ans) {
        if(bt == null){return ans;}
        else{
            InorderSort(bt.getLeft(),ans);
            ans.add(bt.getRoot());
            InorderSort(bt.getRight(),ans);
        }
        return ans;
    }

    private static <T> ArrayList<T> PreorderSort(BinaryTree<T> bt, ArrayList<T> ans) {
        if(bt == null){return ans;}
        else{
            ans.add(bt.getRoot());
            PreorderSort(bt.getLeft(),ans);
            PreorderSort(bt.getRight(),ans);

        }
        return ans;
    }

    private static <T> ArrayList<T> PostorderSort(BinaryTree<T> bt, ArrayList<T> ans) {
        if(bt == null){return ans;}
        else{
            PostorderSort(bt.getLeft(),ans);
            PostorderSort(bt.getRight(),ans);
            ans.add(bt.getRoot());
        }
        return ans;
    }

    public static <T> void Inorder(BinaryTree<T> bt){
       if(bt == null){return;}
       else{
           Inorder(bt.getLeft());
           System.out.println(bt.getRoot());
           Inorder(bt.getRight());
       }
   }

    public static <T> void PreOrder(BinaryTree<T> bt){
        if(bt == null){return;}
        else{
            System.out.println(bt.getRoot());
            PreOrder(bt.getLeft());
            PreOrder(bt.getRight());
        }
    }

    public static <T> void PostOrder(BinaryTree<T> bt){
        if(bt == null){return;}
        else{
            PostOrder(bt.getLeft());
            PostOrder(bt.getRight());
            System.out.println(bt.getRoot());
        }
    }

    public static int[] numberOfLeafsByLevel(BinaryTree bt) {
        int[] ans = null;
        if (bt != null && bt.size() > 0) {
            ans = new int[height(bt)];
            numberOfLeafsByLevelH(bt, ans, 0);
        }
        return ans;
    }
        private static void numberOfLeafsByLevelH(BinaryTree bt,int[] ans, int d){
            if (bt != null && bt.size() > 0) {
                if (bt.size() == 1) {
                    ans[d]++;
                } else {
                    numberOfLeafsByLevelH(bt.getLeft(), ans, d + 1);
                    numberOfLeafsByLevelH(bt.getRight(), ans, d + 1);
                }
            }
        }

        public static<T> void TreePrinter(BinaryTree<T> tree) {
        int h = height(tree);
        int col = getcol(h);
        int[][] M = new int[h][col];
        printTree(M, tree, col / 2, 0, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < col; j++) {
                if (M[i][j] == 0)
                    System.out.print("  ");
                else
                    System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int getHeight(BinaryTree bt){
        if (bt == null) {
            return -1;
        }
        return 1+Math.max(getHeight(bt.getLeft()),getHeight(bt.getRight()));

    }

    public static ArrayList<Integer> arrayListOfLeaves(BinaryTree<Integer> bt){
        ArrayList<Integer> ans = new ArrayList<>();
        if(bt != null && bt.size()>0) {
            Helper(bt,ans);
        }
        return ans;
    }
    public static <T> ArrayList<Integer> Helper(BinaryTree<Integer> bt,ArrayList<Integer> ans){
        if(bt!=null && bt.size()>0) {
            if(bt.size() == 1) {ans.add(bt.getRoot());}
            else{
                Helper(bt.getLeft(),ans);
                Helper(bt.getRight(),ans);
            }
        }
        return ans;

    }
    //q8
    public static boolean isOfTheSameStructure(BinaryTree bt1, BinaryTree bt2){
        if(bt1 == null && bt2 == null){
            return true;
        }
        else if(bt1!=null && bt2!=null){
            return isOfTheSameStructure(bt1.getRight(),bt2.getRight())
                    && isOfTheSameStructure(bt1.getLeft(),bt2.getLeft());
        }
        return false;
    }
    public static <T> int numOfleavs(BinaryTree<T> bt) {
        if(bt == null) {return 0;}
        if(bt.getLeft()==null && bt.getRight() == null) {return 1;}
        return numOfleavs(bt.getLeft())+ numOfleavs(bt.getRight());
    }





    public static int getcol(int h) {
        if (h == 1)
            return 1;
        return getcol(h - 1) + getcol(h - 1) + 1;
    }

    public static <T> void printTree(int[][] M, BinaryTree<T> root, int col, int row, int height) {
        if (root == null)
            return;
        M[row][col] = (int)root.getRoot();
        printTree(M, root.getLeft(), col - (int)Math.pow(2, height - 2), row + 1, height - 1);
        printTree(M, root.getRight(), col + (int)Math.pow(2, height - 2), row + 1, height - 1);
    }

    public static<T> int height(BinaryTree<T> root) {
        if (root == null)
            return 0;
        return 1+ Math.max(height(root.getLeft()), height(root.getRight()));
    }

}
