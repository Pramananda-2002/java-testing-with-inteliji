package extra;

import java.util.HashMap;
import java.util.Map;

class CustomTreeNode {
    Map<Character, CustomTreeNode> children;
    boolean isEndOfWord;

    public CustomTreeNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }

    public void addChild(char key, CustomTreeNode child) {
        children.put(key, child);
    }

    public CustomTreeNode getChild(char key) {
        return children.get(key);
    }

    public boolean hasChild(char key) {
        return children.containsKey(key);
    }
}
public class MultiLayerTree {
    private CustomTreeNode root;
    private int layers;
    private int childrenPerNode;

    public MultiLayerTree(int layers, int childrenPerNode) {
        this.root = new CustomTreeNode();
        this.layers = layers;
        this.childrenPerNode = childrenPerNode;
        buildTree(root, layers);
    }

    private void buildTree(CustomTreeNode node, int remainingLayers) {
        if (remainingLayers == 0) {
            return;
        }

        for (int i = 0; i < childrenPerNode; i++) {
            char key = (char) ('a' + i); // Example: Use characters 'a' to 'c' for children
            CustomTreeNode child = new CustomTreeNode();
            node.addChild(key, child);
            buildTree(child, remainingLayers - 1);
        }
    }

    public CustomTreeNode getRoot() {
        return root;
    }

    // Example method to print tree structure (for debugging)
    public void printTree(CustomTreeNode node, String indent) {
        if (node == null) return;

        System.out.println(indent + (node.isEndOfWord ? "*" : "Node"));
        for (Map.Entry<Character, CustomTreeNode> entry : node.children.entrySet()) {
            System.out.println(indent + " " + entry.getKey());
            printTree(entry.getValue(), indent + "  ");
        }
    }

    public static void main(String[] args) {
        MultiLayerTree tree = new MultiLayerTree(4, 3); // 4 layers, 3 children per node
        tree.printTree(tree.getRoot(), "");
    }
}

