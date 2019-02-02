package com.tree;

import java.util.*;

public class TreeSimulator {
    public static int NUM=10;
    public static void main(String[] args){
       Tree tree= generatorTree();
       System.out.print(tree);
        List<TreeEntity> list= treeToTreeEntity(tree);
      //  System.out.print(list);

        long startT=System.currentTimeMillis();
        Tree resultTree=treeEntityToTree(list);
        long endT=System.currentTimeMillis();
        System.out.println(resultTree);
        System.out.println("方法1耗时"+(endT-startT));
        long startT2=System.currentTimeMillis();
        Tree resultTree2=treeEntityToTree2(list);
        long endT2=System.currentTimeMillis();
        System.out.println(resultTree2);
        System.out.println("方法2耗时"+(endT2-startT2));
        System.out.println(resultTree);
        System.out.println(resultTree2);
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
            int number=20+(int)(Math.random()*30);
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
    public static Tree treeEntityToTree(List<TreeEntity> list){
        Tree tree=new Tree();
        for(TreeEntity t:list){
            if(t.pid==null){
                tree.id=t.id;
            }

            break;
        }
  //      list.remove(t);
        treeEntityToTree(list,tree);
        return tree;
    };
    /**
     * 根据线性列表生成树,ok，重点来了，这是比较麻烦的地方
     * 最直观的方法,实际运行证明，这个慢的可怕
     * */
    public static Tree treeEntityToTree(List<TreeEntity> list,Tree tree){
        for(TreeEntity t:list){
            if(t.pid==tree.id){
                if(tree.children==null){
                    tree.children=new ArrayList<Tree>();
                }
                Tree son=new Tree(t.id);
                tree.children.add(son);
                treeEntityToTree(list,son);
            }
        }
        return tree;
    };

    public static Tree treeEntityToTree3(List<TreeEntity> list){
        Tree root=null;
        List<Tree> treeList=new ArrayList<>();
        Map<String,Tree> treeMap=new HashMap<>();
        for(TreeEntity t:list){
            Tree tree=new Tree(t.pid,t.id);
            treeMap.put(t.id,tree);
            treeList.add(tree);
            if(t.pid==null){
                root=tree;
            }
        }

        Tree tree=new Tree();
        for(TreeEntity t:list){
            if(t.pid==null){
                tree.id=t.id;
            }

            break;
        }
        //      list.remove(t);
        treeEntityToTree(list,tree);
        return tree;
    };
    /**
     * 根据线性列表生成树,ok，重点来了，这是比较麻烦的地方
     * 最直观的方法,实际运行证明，这个慢的可怕
     * */
    public static Tree treeEntityToTree3(List<TreeEntity> list,Tree tree){
        for(TreeEntity t:list){
            if(t.pid==tree.id){
                if(tree.children==null){
                    tree.children=new ArrayList<Tree>();
                }
                Tree son=new Tree(t.id);
                tree.children.add(son);
                treeEntityToTree(list,son);
            }
        }
        return tree;
    };




    /**
     *
     * */
    public static Tree treeEntityToTree2(List<TreeEntity> list){
        Tree root=null;
        List<Tree> treeList=new ArrayList<>();
        Map<String,Tree> treeMap=new HashMap<>();
        for(TreeEntity t:list){
            Tree tree=new Tree(t.pid,t.id);
            treeMap.put(t.id,tree);
            treeList.add(tree);
            if(t.pid==null){
                root=tree;
            }
        }
        for(Tree t:treeList){
            if(t.pid!=null){
                Tree parent=treeMap.get(t.pid);
                if(parent.children==null){
                    parent.children= new ArrayList<Tree>();
                }
                parent.children.add(t);
            }

        }
        return root;
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
