package testAlgorithm;

public class Main {
    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<>("Root");

        TreeNode<String> child1 = new TreeNode<>("Child 1");
        TreeNode<String> child2 = new TreeNode<>("Child 2");

        root.addChild(child1);
        root.addChild(child2);

        TreeNode<String> grandchild1 = new TreeNode<>("Grandchild 1");
        TreeNode<String> grandchild2 = new TreeNode<>("Grandchild 2");

        child1.addChild(grandchild1);
        child1.addChild(grandchild2);

        // Print the tree
        root.printTree("");

        // Find a node
        TreeNode<String> foundNode = root.findNode("Grandchild 2");
        if (foundNode != null) {
            System.out.println("Found node with data: " + foundNode.data);
        } else {
            System.out.println("Node not found.");
        }

        // Remove a node
        child1.removeChild(grandchild1);
        root.printTree("");
    }
    
}
