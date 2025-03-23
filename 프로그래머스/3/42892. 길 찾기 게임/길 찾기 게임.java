import java.util.*;

class Solution {
    
    Node[] binaryTree; // 1차원 배열의 이진 트리
    List<Integer> preOrderResult = new ArrayList<>(); // 전위 순회 결과
    List<Integer> postOrderResult = new ArrayList<>(); // 후위 순회 결과
    
    public int[][] solution(int[][] nodeinfo) {
        int nodeCount = nodeinfo.length; // 노드의 개수
        binaryTree = new Node[nodeCount];
        
        // 노드 추가하기
        for (int i = 0; i < nodeCount; i++) {
            int[] node = nodeinfo[i];
            binaryTree[i] = new Node(i + 1, node[0], node[1], null, null);
        }
        
        // 각 노드 연결하기
        Arrays.sort(binaryTree);
        Node root = binaryTree[0];
        for (int i = 1; i < nodeCount; i++) {
            linkChild(root, binaryTree[i]);
        }
        
        // 각각 순회하기
        preOrder(root);
        postOrder(root);
        
        // 결과 반환
        int[][] answer = new int[2][nodeCount];
        answer[0] = preOrderResult.stream()
            .mapToInt(Integer::intValue).toArray();
        answer[1] = postOrderResult.stream()
            .mapToInt(Integer::intValue).toArray();
        return answer;
    }
    
    // 자식 노드 연결
    private void linkChild(Node parent, Node child) {
        // 오른쪽 자식 연결
        if (parent.x < child.x) {
            if (parent.rightChild == null) {
                parent.setRightChild(child);
                return;
            }
            // 이미 자식 노드가 있는 경우 그 하위 자식에 배치
            linkChild(parent.rightChild, child);
        } else {
            // 왼쪽 자식 연결
            if (parent.leftChild == null) {
                parent.setLeftChild(child);
                return;
            }
            // 이미 자식 노드가 있는 경우 그 하위 자식에 배치
            linkChild(parent.leftChild, child);
        }
    }
    
    // 전위 순회
    private void preOrder(Node node) {
        if (node != null) {
            preOrderResult.add(node.num);
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }
    
    // 후위 순회
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            postOrderResult.add(node.num);
        }
    }
}

class Node implements Comparable<Node> {
    
    int num;    // 노드 번호
    int x;
    int y;
    Node leftChild;
    Node rightChild;
    
    public Node(int num, int x, int y, Node leftChild, Node rightChild) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
    
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
    
    // y를 기준으로 내림차순 정렬
    // 만약 같으면 x를 기준으로 오름차순 정렬
    @Override
    public int compareTo(Node other) {
        if (this.y == other.y) {
            return Integer.compare(this.x, other.x);
        }
        return Integer.compare(other.y, this.y);
    }
}