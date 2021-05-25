package com.thorin.tree;

import java.util.*;

public class HuffmenTree {

    HuffmenNode root;
    List<HuffmenNode> leafs;
    Map<Character,Integer> weights;

    private static class HuffmenNode implements Comparable<HuffmenNode> {

        private String code;
        private int fre;
        HuffmenNode left;
        HuffmenNode right;
        HuffmenNode parent;

        @Override
        public int compareTo(HuffmenNode o) {
            return this.fre - o.fre;
        }
    }

    public HuffmenTree(Map<Character, Integer> weights){
        this.weights = weights;
        leafs = new ArrayList<HuffmenNode>();
    }

    public void create(){
        Character keys[] = weights.keySet().toArray(new Character[0]);
        PriorityQueue<HuffmenNode> priorityQueue = new PriorityQueue<>();
        for (Character key : keys) {
            HuffmenNode huffmenNode = new HuffmenNode();
            huffmenNode.code = key.toString();
            huffmenNode.fre = weights.get(key);
            priorityQueue.add(huffmenNode);
            leafs.add(huffmenNode);
        }
        int len = priorityQueue.size();
        for (int i = 1; i <= len - 1; i++) {
            HuffmenNode n1 = priorityQueue.poll();
            HuffmenNode n2 = priorityQueue.poll();
            HuffmenNode newNode = new HuffmenNode();
            newNode.code = n1.code + n2.code;
            newNode.fre = n1.fre + n2.fre;
            newNode.left = n1;
            newNode.right = n2;
            n1.parent = newNode;
            n2.parent = newNode;

            priorityQueue.add(newNode);
        }
        root = priorityQueue.poll();
        System.out.println("构建完成");
    }

    public Map<Character,String> codeChar(){
        Map<Character,String> map = new HashMap<>();
        for (HuffmenNode leaf : leafs) {
            String code = "";
            Character c = new Character(leaf.code.charAt(0));
            HuffmenNode current = leaf;
            do {
                if (current.parent != null && current == current.parent.left) {
                    code = "0" + code;
                } else {
                    code = "1" + code;
                }
                current = current.parent;
            } while (current.parent != null);
            map.put(c,code);
//            System.out.println(c + ":" + code);
        }
        return map;
    }
    public String decode(String str){
        if (str == null) {
            throw new IllegalArgumentException();
        }
        String code = "";
        Map<Character,String> map = codeChar();
        char[] array = str.toCharArray();
        for (char c : array) {
            if (map.containsKey(c)) {
                code = code + map.get(c);
            }
        }
        return code;
    }

    public String encode(String code){
        if (code == null) {
            throw new IllegalArgumentException();
        }
        String str = "";
        Map<Character,String> map = codeChar();
        while (code != null && !"".equals(code)) {
            for (Map.Entry<Character, String> characterStringEntry : map.entrySet()) {
                if (code.startsWith(characterStringEntry.getValue())) {
                    str = str + characterStringEntry.getKey();
                    int len = code.length();
                    code = code.substring(characterStringEntry.getValue().length(),len);
                    break;
                }
            }
        }
        return str;
    }
    public static void main(String[] args) {
        // a:3 b:24 c:6 d:20 e:34 f:4 g:12
        Map<Character, Integer> weights = new HashMap<Character, Integer>();
        //一般来说：动态的加密，最开始是不知道里面有什么内容的。我们需要一个密码本，往往就是某个字典。如果是英文就用英文字典，统计次数。
        //换密码本
        //静态的文件。针对性的做编码.图像加密,没有特性的。hash加密（MD5）
        weights.put('a', 3);
        weights.put('b', 24);
        weights.put('c', 6);
        weights.put('d', 1);
        weights.put('e', 34);
        weights.put('f', 4);
        weights.put('g', 12);

        HuffmenTree huffmenTree = new HuffmenTree(weights);
        huffmenTree.create();
        Map<Character, String> code = huffmenTree.codeChar();
        String str = "aceg";
        String afterCode = huffmenTree.decode(str);
        System.out.println("编码后的：" + afterCode);
        String afterEncode = huffmenTree.encode(afterCode);
        System.out.println("编码后 ：" + afterEncode);
    }
}
