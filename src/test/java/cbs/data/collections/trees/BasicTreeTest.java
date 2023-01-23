package cbs.data.collections.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BasicTreeTest {
    @Test
    void testCount() {
        BasicTree<String> tree = new BasicTree<>();

        BasicNode<String> root = new BasicNode<String>("1");
        BasicNode<String> second = new BasicNode<String>("2");
        BasicNode<String> third = new BasicNode<String>("3");
        BasicNode<String> fourth = new BasicNode<String>("4");

        root.addChild(second);
        root.addChild(third);
        third.addChild(fourth);

        tree.setRoot(root);

        final int expected = 4;
        int actual = tree.count();

        assertEquals(expected, actual);
    }

    @Test
    void testPreOrder() {
        BasicTree<String> tree = new BasicTree<>();

        BasicNode<String> root = new BasicNode<String>("1");
        BasicNode<String> second = new BasicNode<String>("2");
        BasicNode<String> third = new BasicNode<String>("3");
        BasicNode<String> fourth = new BasicNode<String>("4");

        root.addChild(second);
        root.addChild(third);
        third.addChild(fourth);

        tree.setRoot(root);

        final String expected_1 = "1pepote";
        final String expected_2 = "2pepote";
        final String expected_3 = "3pepote";
        final String expected_4 = "4pepote";

        tree.preOrder(node -> node.setData(node.getData() + "pepote"));

        assertEquals(expected_1, root.getData());
        assertEquals(expected_2, second.getData());
        assertEquals(expected_3, third.getData());
        assertEquals(expected_4, fourth.getData());
    }

    @Test
    void testGetNodeByData(){
        BasicTree<String> tree = new BasicTree<>();

        BasicNode<String> root = new BasicNode<String>("1");
        BasicNode<String> second = new BasicNode<String>("2");
        BasicNode<String> third = new BasicNode<String>("3");
        BasicNode<String> fourth = new BasicNode<String>("4");

        root.addChild(second);
        root.addChild(third);
        third.addChild(fourth);

        tree.setRoot(root);

        BasicNode<String> actual = tree.getNodeByData(second.getData());

        assertEquals(second, actual);
    }


    @Test
    void testSetRoot() {
        BasicTree<String> tree = new BasicTree<>();
        String expected = "hola";
        tree.setRoot(expected);
        String actual = tree.getRoot(); 
        assertEquals(expected, actual);
    }
}
