import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.SortedMap;
import java.util.TreeMap;

public class BinaryTree<T> {
    private static class Node<T> {
        T data;
        Node<T> right;
        Node<T> left;

        Node(T data) {
            this.setData(data);
            setRight(null);
            setLeft(null);
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getRight() {
            return right;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public String toString() {
            return String.valueOf(data);
        }
    }

    private static class Pair<T> {
        Node<T> node;
        int distanceFromRoot;

        Pair(Node<T> data, int distance) {
            node = data;
            distanceFromRoot = distance;
        }
    }

    Node<T> root;

    BinaryTree() {
        root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public static BinaryTree<Integer> populatedTree() {
        Scanner input = new Scanner(System.in);
        BinaryTree<Integer> tree = new BinaryTree<>();
        int numberOfNodes = Integer.parseInt(input.nextLine());
        String address = "";
        try {
            tree.add(0, address);
            for (int i = 1; i < numberOfNodes; i++) {
                address = input.nextLine();
                tree.add(i, address);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return tree;
    }

    public static BinaryTree<Integer> populatedTreeWithCustomNodes() {
        Scanner input = new Scanner(System.in);
        BinaryTree<Integer> tree = new BinaryTree<>();
        int numberOfNodes = Integer.parseInt(input.nextLine());
        String rawInputString;
        String[] splitInputString;
        String address = "";
        int nodeWeight = Integer.parseInt(input.nextLine());
        try {
            tree.add(nodeWeight, address);
            for (int i = 1; i < numberOfNodes; i++) {
                rawInputString = input.nextLine();
                splitInputString = rawInputString.split(" ");
                address = splitInputString[0];
                nodeWeight = Integer.parseInt(splitInputString[1]);
                tree.add(nodeWeight, address);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return tree;
    }

    public void add(T data, String address) throws NoSuchFieldException {
        if (root == null && !address.equals("")) {
            throw new NoSuchFieldException("The tree doesn't have a root node.");
        } else if (root == null) {
            root = new Node<>(data);
        } else {
            Node<T> currentNode = root;
            for (int i = 0; i < address.length() - 1; i++) {
                switch (address.charAt(i)) {
                    case 'l' -> currentNode = currentNode.getLeft();
                    case 'r' -> currentNode = currentNode.getRight();
                }
                if (currentNode == null) {
                    throw new NoSuchFieldException("The path specified is unreachable.");
                }
            }
            Node<T> newNode = new Node<>(data);
            switch (address.charAt(address.length() - 1)) {
                case 'l' -> currentNode.setLeft(newNode);
                case 'r' -> currentNode.setRight(newNode);
            }
        }
    }

    private Node<T> preOrderSearch(T key, Node<T> node) {
        if (node == null) {
            return null;
        }
        if (node.getData() == key) {
            return node;
        }
        Node<T> leftSubtree = preOrderSearch(key, node.getLeft());
        if (leftSubtree != null) {
            return leftSubtree;
        }
        return preOrderSearch(key, node.getRight());
    }

    public Node<T> search(T key) {
        return preOrderSearch(key, root);
    }

    public Node<T> traverse(String address) throws NoSuchFieldException {
        Node<T> currentNode = root;
        for (char ch : address.toCharArray()) {
            switch (ch) {
                case 'l' -> currentNode = currentNode.getLeft();
                case 'r' -> currentNode = currentNode.getRight();
            }
            if (currentNode == null) {
                throw new NoSuchFieldException("The path specified is unreachable.");
            }
        }
        return currentNode;
    }

    private Node<T>[] deepestNodeAndParent() {
        if (root == null) {
            return null;
        }
        Node<T> deepest, deepestParent;
        deepest = deepestParent = null;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            deepest = queue.poll();
            if (deepest.getLeft() != null) {
                deepestParent = deepest;
                queue.offer(deepest.getLeft());
            }
            if (deepest.getRight() != null) {
                deepestParent = deepest;
                queue.offer(deepest.getRight());
            }
        }
        Node<T>[] deepestAndParent = new Node[2];
        deepestAndParent[0] = deepestParent;
        deepestAndParent[1] = deepest;
        return deepestAndParent;
    }

    private void remove(Node<T> nodeToBeRemoved) {
        Node<T>[] deepestAndParent = deepestNodeAndParent();
        if (deepestAndParent != null) {
            nodeToBeRemoved.setData(deepestAndParent[1].getData());
            if (deepestAndParent[0].getRight() == deepestAndParent[1]) {
                deepestAndParent[0].setRight(null);
            } else {
                deepestAndParent[0].setLeft(null);
            }
        }
    }

    public void remove(T key) throws NoSuchFieldException {
        Node<T> nodeToBeRemoved = search(key);
        if (nodeToBeRemoved == null) {
            throw new NoSuchFieldException("The node with key specified doesn't exist.");
        }
        remove(nodeToBeRemoved);
    }

    public void remove(String address) throws NoSuchFieldException {
        Node<T> nodeToBeRemoved = traverse(address);
        remove(nodeToBeRemoved);
    }

    public void diagonalTraversal() throws NoSuchFieldException {
        if (root == null) {
            throw new NoSuchFieldException("The tree doesn't have a root node.");
        }
        Node<T> currentNode;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            if (currentNode == null) {
                if (queue.isEmpty()) {
                    return;
                }
                System.out.println();
                queue.offer(null);
            } else {
                while (currentNode != null) {
                    System.out.print(currentNode + " ");
                    if (currentNode.getLeft() != null) {
                        queue.offer(currentNode.getLeft());
                    }
                    currentNode = currentNode.getRight();
                }
            }
        }
    }

    public void verticalTraversal() throws NoSuchFieldException {
        if (root == null) {
            throw new NoSuchFieldException("The tree doesn't have a root node.");
        }
        SortedMap<Integer, LinkedList<Node<T>>> nodesMap = new TreeMap<>();
        Queue<Pair<T>> nodesQueue = new LinkedList<>();
        nodesQueue.offer(new Pair<>(root, 0));
        Pair<T> currentNodePair;
        LinkedList<Node<T>> currentLevelList;
        while (!nodesQueue.isEmpty()) {
            currentNodePair = nodesQueue.poll();
            currentLevelList = nodesMap.get(currentNodePair.distanceFromRoot);
            if (currentLevelList == null) {
                currentLevelList = new LinkedList<>();
            }
            currentLevelList.add(currentNodePair.node);
            nodesMap.put(currentNodePair.distanceFromRoot, currentLevelList);
            if (currentNodePair.node.getLeft() != null) {
                nodesQueue.offer(new Pair<>(currentNodePair.node.getLeft(), currentNodePair.distanceFromRoot - 1));
            }
            if (currentNodePair.node.getRight() != null) {
                nodesQueue.offer(new Pair<>(currentNodePair.node.getRight(), currentNodePair.distanceFromRoot + 1));
            }
        }
        for (Integer i : nodesMap.keySet()) {
            currentLevelList = nodesMap.get(i);
            for (Node<T> node : currentLevelList) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }

    public static int getMaximumSum(Node<Integer> node) {
        AtomicInteger maxUntilHere = new AtomicInteger(Integer.MIN_VALUE);
        getMaximumSumHelper(node, maxUntilHere);
        return maxUntilHere.get();
    }

    public static int getMaximumSumHelper(Node<Integer> node, AtomicInteger maxUntilHere) {
        if (node == null) {
            return 0;
        }
        int left = getMaximumSumHelper(node.getLeft(), maxUntilHere);
        int right = getMaximumSumHelper(node.getRight(), maxUntilHere);
        int returnMax = Integer.max(Math.max(left, right) + node.getData(), node.getData());
        int max = Integer.max(returnMax, node.getData() + left + right);
        int maxUntilHereInt = maxUntilHere.get();
        if (max > maxUntilHereInt) {
            maxUntilHere.set(max);
        }
        return returnMax;
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = BinaryTree.populatedTree();
        try {
            tree.verticalTraversal();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
