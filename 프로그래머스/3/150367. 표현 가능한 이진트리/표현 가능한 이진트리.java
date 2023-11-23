import java.util.ArrayList;
import java.util.List;

/*
<number를 이진트리로 표현 가능? -> 가능하면 1, 불가능하면 0>
- 해당 숫자를 이진수로 변경
- 만약 이진수의 자리수가 짝수이면 제일 앞에 0 붙여주기
- 1. 포화이진트리인지 확인 (완전 대칭인가?)
    -
- 2. 가운데 숫자가 1인지 확인 (루트 노드가 올바른가?)
- 3. 숫자 0이 더미 노드가 올 수 있는 자리에만 있는지 (더미 노드의 위치가 올바른가?)
 */
class Solution {

    List<Integer> answerList = new ArrayList<>();
    public int[] solution(long[] numbers) {

        for (int i = 0; i < numbers.length; i++) {

            // 10진수로 2진수로 변환
            String binary = Long.toBinaryString(numbers[i]);

            // 이진수의 자리수가 짝수이면 포화이진트리가 될 때까지 앞에 0 붙여주기
            while(!isCollectLength(binary)){
                binary = "0" + binary;
            }

            boolean result = dfs(binary);
            if(result) {
                answerList.add(1);
            } else {
                answerList.add(0);
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    private boolean isCollectLength(String binary) {
        // 포화이진트리 판단 = (자릿수 + 1)이 2의 n승이면 됨 = (자릿수 + 1 - 1)의 이진수가 모두 1로 이뤄지면 됨
        String binaryString = Long.toBinaryString(binary.length());
        for(int i = 0; i < binaryString.length(); i++) {
            if(binaryString.split("")[i].equals("0")) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(String binary) {

//        System.out.println("==========");
//        System.out.println(binary);
        // 사이즈가 1이 되면 오류 없는 것! 포화이진트리인것!
        if(binary.length() == 1) {
//            System.out.println("OK");
            return true;
        }

        int middleIdx = binary.length() / 2;
        String leftTree = binary.substring(0, middleIdx);
        String rightTree = binary.substring(middleIdx + 1, binary.length());
//
//        System.out.println(leftTree);
//        System.out.println(rightTree);
        // 루트 노드가 0인 경우, 오른쪽, 왼쪽 트리의 모든 노드가 0이어야 함
        if(binary.charAt(middleIdx) == '0'){
//            System.out.println("X");
            if(leftTree.contains("1") || rightTree.contains("1")) {
                return false;
            }
        }

        // 왼쪽 트리와 오른쪽 트리의 검사 결과가 둘 다 true이면 올바른 포화이진트리!
        return dfs(leftTree) && dfs(rightTree);
    }
}