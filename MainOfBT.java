package BinaryTrees;

import LinkedList.MyList;

import java.util.ArrayList;

public class MainOfBT {
    public static <T> void main(String[] args) throws Exception {


    }
    
    /**
     * This function recursively traverses the binary tree and counts the nodes that have a left child but no right child.
     */
    public static <T> int LeftSon(BinaryTree bt){
        if(bt == null ||bt.size() == 1){return 0;}
        if(bt.getLeft() != null && bt.getRight() == null ){return 1;}
        else {
            return LeftSon(bt.getLeft()) + LeftSon(bt.getRight());
        }
    }
    
    /**
     * This function counts the number of leaves in a binary tree at even levels.
     * It uses a helper function, EvenLevelsH, to recursively traverse the binary tree and determine
     * the number of leaves at even levels.
     * NOTE: the lower level is zero.
     */
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
    /**
     *This function returns an ArrayList of all the leaves in even levels. 
     * NOTE: the lower level is zero.
     */
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

    /**
     *This function returns an ArrayList of all the leaves in a specific order. 
     */

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
    
    /**
     * bt: The binary tree to be traversed.
     * Inorder, PreOrder, and PostOrder, for traversing a binary tree.
     * Each method performs a different type of traversal and helps you
     * visit the nodes of the tree in a specific order.
     */

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

   /**
     *This function returns an ArrayList of all the leaves with no specific order. 
     */
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
     /**
     * Returns an ArrayList of the left leaves only
     */
    public static <T> ArrayList<T> arrOfLeftLeaves(BinaryTree bt){
        ArrayList<T> ans = new ArrayList<>();
        return arrOfLeftLeavesH(bt,ans);
    }

    private static <T> ArrayList<T> arrOfLeftLeavesH(BinaryTree<T> bt, ArrayList<T> ans) {
        if(bt == null) {return ans;}
        if(bt.getLeft()!=null && bt.getLeft().isLeaf()) {
                ans.add(bt.getLeft().getRoot());
                if(bt.getRight()!=null){
                    return arrOfLeftLeavesH(bt.getRight(), ans);
                }
        }
        else{
           arrOfLeftLeavesH(bt.getLeft(), ans);
           arrOfLeftLeavesH(bt.getRight(), ans);
        }
        return ans;
    }

     /**
     * Returns an ArrayList of the left sons only
     * inOrder sortes
     */
    public static <T> ArrayList<T> arrOfLeftSons(BinaryTree<T> bt){
        ArrayList<T> ans = new ArrayList<>();
        return arrOfLeftSonsH(bt,ans);
    }

    private static <T> ArrayList<T> arrOfLeftSonsH(BinaryTree<T> bt, ArrayList<T> ans) {
        if(bt == null){return ans;}
        else{
            arrOfLeftSonsH(bt.getLeft(),ans);
             if(bt.getLeft() != null){
                ans.add(bt.getLeft().getRoot());
            }
            arrOfLeftSonsH(bt.getRight(),ans);

        }
        return ans;
    }

    /**
     * How many leaves are there in the tree between the min level (not inclusive) and the max level (inclusive). Note that the root is at level 0.
     */
     public static <T> int q9(BinaryTree<T> bt, int min, int max){
        if(height(bt) < max){max = height(bt);}
        return LeavesInLevel(bt,0,max) - LeavesInLevel(bt,0,min);
    }
    
    // calculate how many leaves are exist up to given level
    public static int LeavesInLevel(BinaryTree bt, int n, int m) {
      if(bt == null ){return 0;}
      if(bt.getRight() == null && bt.getLeft() == null && n<=m){return 1;}
      else{
          return LeavesInLevel(bt.getLeft(), n+1,m) + LeavesInLevel(bt.getRight(), n+1,m);
      }
    }
    
}
