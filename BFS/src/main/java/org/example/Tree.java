package org.example;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private int value;
    private List<Tree> children;

    public Tree(int value){
        this.value = value;
        this.children = new ArrayList<>();
    }

    public int getValue(){
        return this.value;
    }
    public List<Tree> getChildren(){
        return this.children;
    }

    public Tree addChild(int value){
        Tree newChild = new Tree(value);
        children.add(newChild);
        return newChild;
    }


}
