/*
 * Homework 7.2 재균형 기능을 가지는 이진탐색트리
 * 이름: 박다원
 * 학번: 21912154
 */

package HW07_2_Binary_Search_Tree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App_BalancedBinarySearchTree {
    public static void main(String[] args) throws IOException {
        final String fout_name = "/Users/daone/IdeaProjects/java-homework/src/HW07_2_Binary_Search_Tree/BST_test.txt";
        Scanner cin = new Scanner(System.in);
        FileWriter fout = new FileWriter(fout_name);
        int[] intArray = {0, 1, 2, 3, 4, 5};
        String BST_name = "BST_IntegerEntry";
        BalancedBinarySearchTree<Integer> bstIntEntry = new BalancedBinarySearchTree<Integer>(BST_name);
        for (int int_entry : intArray) {
            bstIntEntry.insert_withRebalancing(int_entry);
            System.out.println(bstIntEntry);
        }
        bstIntEntry.fprintBST_withDepth(fout);
        BinarySearchTreeNode<Integer> bstn_int;
        int int_searchKey = 1, searchResult;
        while (true) {
            System.out.print("Input an int_searchKey (-1 to quit) : ");
            int_searchKey = cin.nextInt();
            if (int_searchKey == -1)
                break;
            bstn_int = bstIntEntry.search(int_searchKey);
            if ((bstn_int != null) && (int_searchKey == bstn_int.getEntry())) {
                System.out.printf("int_searchKey(%d) is found in the %s\n", int_searchKey, BST_name);
            } else {
                System.out.printf("int_searchKey(%d) is not found in the %s\n", int_searchKey, BST_name);
            }
        }
        for (int i = 0; i < intArray.length; i++) {
            BinarySearchTreeNode<Integer> bstn, BST_root = bstIntEntry.getRoot();
            bstn = bstIntEntry._deleteBSTN(BST_root);
            bstIntEntry.setRoot(bstn);
            bstIntEntry.fprintBST_withDepth(fout);
        }
        String[] strArray =
                {"A", "B", "C", "D", "E", "F", "G"};
        BST_name = "BST_StringEntry";
        BalancedBinarySearchTree<String> bstStringEntry = new BalancedBinarySearchTree<String>(BST_name);
        for (String str_entry : strArray) {
            bstStringEntry.insert_withRebalancing(str_entry);
            System.out.println(bstStringEntry);
        }
        bstStringEntry.fprintBST_withDepth(fout);
        BinarySearchTreeNode<String> bstn_str;
        String str_searchKey, str_searchResult;
        while (true) {
            System.out.print("Input a strt_searchKey (. to quit) : ");
            str_searchKey = cin.next();
            if (str_searchKey.equals("."))
                break;
            bstn_str = bstStringEntry.search(str_searchKey);
            if ((bstn_str != null) && str_searchKey.equals(bstn_str.getEntry())) {
                System.out.printf("str_searchKey(%s) is found in the %s\n", str_searchKey, BST_name);
            } else {
                System.out.printf("str_searchKey(%s) is not found in the %s\n", str_searchKey, BST_name);
            }
        }
        BinarySearchTreeNode<String> bstn, BST_root;
        for (int i = 0; i < strArray.length; i++) {
            BST_root = bstStringEntry.getRoot();
            bstn = bstStringEntry._deleteBSTN(BST_root);
            bstStringEntry.setRoot(bstn);
            bstStringEntry.fprintBST_withDepth(fout);
        }
        cin.close();
        fout.close();

    }
}