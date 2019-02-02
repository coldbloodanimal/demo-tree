package com.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TreeSimulator {
    public static int NUM=10;
    public static void main(String[] args){
       Tree tree= generatorTree();
       System.out.print(tree);
        List<TreeEntity> list= treeToTreeEntity(tree);
        System.out.print(list);
    }

    public static Tree generatorTree(){
        Tree tree=new Tree(UUID.randomUUID().toString());
        return generatorTree(tree,0);
    };

    /**
     * 根据树生成实体列表
     * */

    public static Tree generatorTree(Tree tree,int deepth){
        deepth=deepth+1;
        if(deepth<4){
            List<Tree> children=new ArrayList<Tree>();
            int number=(int)(Math.random()*30);
            for(int i=1;i<number;i++){
                Tree son=new Tree(UUID.randomUUID().toString());
                generatorTree(son,deepth);
                children.add(son);
                tree.children=children;
            }
        }
        return tree;
    }


    public static List<TreeEntity> treeToTreeEntity(Tree tree){
        List<TreeEntity> list=new ArrayList<>();
        list.add(new TreeEntity(null,tree.id));
        return treeToTreeEntity(tree,list);
    };

    /**
    * 根据树生成线性列表
    * */
    public static List<TreeEntity> treeToTreeEntity(Tree tree,List<TreeEntity> list){
        if(tree.children!=null){
            for(Tree t:tree.children){
                list.add(new TreeEntity(tree.id,t.id));
                treeToTreeEntity(t,list);
            }
        }
        return list;
    };

    /**
     * 根据线性列表生成树,ok，重点来了，这是比较麻烦的地方
     * */
    public static List<TreeEntity> treeEntityToTree(List<TreeEntity> list,Tree tree){
        if(tree.children!=null){
            for(Tree t:tree.children){
                list.add(new TreeEntity(tree.id,t.id));
                treeToTreeEntity(t,list);
            }
        }
        return list;
    };

    /**
     * 这样写有些麻烦所以 二胎计划generatorTree
     * */
    public static List<TreeEntity> generator2(){
        List<TreeEntity> list=new ArrayList<TreeEntity>();
        for(int i=1;i<NUM;i++){
          TreeEntity tree=new TreeEntity();
          String iString=String.valueOf(i);
          tree.id=iString;
            list.add(tree);
          for(int j=1;j<NUM;j++){
              TreeEntity tree2=new TreeEntity();
              String iString2=String.valueOf(j);
              tree2.id=iString+iString2;
              tree2.pid=iString;
              list.add(tree2);
              for(int k=1;k<NUM;k++){
                  TreeEntity tree3=new TreeEntity();
                  String iString3=String.valueOf(k);
                  tree3.id=iString+iString2+iString3;
                  tree3.pid=iString+iString2;
                  list.add(tree3);
              }
          }
        }
        return list;
    }
}
