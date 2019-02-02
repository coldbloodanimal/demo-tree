package com.tree;

import java.util.List;

public class Tree {
    public String id;
    public String pid;
    public String name;
    public List<Tree> children;
    Tree(){
    }

    Tree(String id){
        this.id=id;
    }
    Tree(String pid,String id){
        this.pid=pid;
        this.id=id;
    }
    Tree(String id,List<Tree> children){
        this.id=id;
        this.children=children;
    }

}
