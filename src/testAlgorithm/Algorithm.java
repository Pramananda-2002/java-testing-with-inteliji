package testAlgorithm;
import java.util.ArrayList;
import java.util.List;

class TreeNode<T> {
    T data;
    List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    // Method to add a child to the current node
    public void addChild(TreeNode<T> child) {
        this.children.add(child);
    }

    // Method to remove a child from the current node
    public void removeChild(TreeNode<T> child) {
        this.children.remove(child);
    }

    // Method to get all children of the current node
    public List<TreeNode<T>> getChildren() {
        return children;
    }

    // Method to find a node with specific data
    public TreeNode<T> findNode(T data) {
        if (this.data.equals(data)) {
            return this;
        }
        for (TreeNode<T> child : children) {
            TreeNode<T> result = child.findNode(data);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    // Method to print the tree (pre-order traversal)
    public void printTree(String prefix) {
        System.out.println(prefix + data);
        for (TreeNode<T> child : children) {
            child.printTree(prefix + "  ");
        }
    }

    
}


public class Algorithm {
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