package com.tree;

import java.util.List;

public class Tree {
    public String id;
    public String name;
    public List<Tree> children;
    Tree(String id){
        this.id=id;
    }
    Tree(String id,List<Tree> children){
        this.id=id;
        this.children=children;
    }

}
