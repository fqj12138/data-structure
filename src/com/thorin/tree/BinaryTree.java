package com.thorin.tree;

/**
 * 链表实现二叉树
 */
public class BinaryTree<E> {

    private Node<E> root;

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    private static class Node<E> {
        private E item;
        private Node<E> left;
        private Node<E> right;

        public Node(Node<E> left, E item, Node<E> right) {
            this.left = left;
            this.item = item;
            this.right = right;
        }
    }

    // Preorder traversal
    public void before(Node<E> root){
        System.out.println(root.item);
        if (root.left != null) {
            before(root.left);
        }
        if (root.right != null) {
            before(root.right);
        }
    }

    // Middle order traversal
    public void middle(Node<E> root){
        if (root.left != null) {
            before(root.left);
        }
        System.out.println(root.item);
        if (root.right != null) {
            before(root.right);
        }
    }

    // Postorder traversal
    public void after(Node<E> root){
        if (root.left != null) {
            before(root.left);
        }
        if (root.right != null) {
            before(root.right);
        }
        System.out.println(root.item);
    }

    public static void main(String[] args) {
        Node<String> node = new Node<>(null, "8", null);
        Node<String> node1 = new Node<>(null, "9", null);
        Node<String> node2 = new Node<>(node, "4", null);
        Node<String> node3 = new Node<>(null, "5", null);
        Node<String> node4 = new Node<>(null, "6", node1);
        Node<String> node5 = new Node<>(null, "7", null);
        Node<String> node6 = new Node<>(node2, "2", node3);
        Node<String> node7 = new Node<>(node4, "3", node5);
        Node<String> root = new Node<>(node6, "1", node7);
        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.before(root);
    }
}
