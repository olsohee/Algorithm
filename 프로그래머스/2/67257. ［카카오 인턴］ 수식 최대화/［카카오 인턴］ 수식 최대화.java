import java.util.*;

class Solution {

    ArrayList<Long> numList = new ArrayList<>();
    ArrayList<Character> operationList = new ArrayList<>();
    char[] operations = new char[]{'+', '-', '*'};
    boolean[] visited = new boolean[3];
    long answer = 0;

    public long solution(String expression) {

        // 숫자, 연산자 분리
        String[] arr = expression.split("((?<=\\d)(?=\\D))|((?<=\\D)(?=\\d))");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("+") || arr[i].equals("-") || arr[i].equals("*")) {
                operationList.add(arr[i].charAt(0));
            } else {
                numList.add(Long.parseLong(arr[i]));
            }
        }

        // 연산자 우선순위 정하기 (dfs로 조합 만들기)
        dfs(0, new ArrayList<>());

        return answer;
    }

    private long getResult(List<Character> operationsOrder) {

        List<Character> operationList = (List<Character>)this.operationList.clone();
        List<Long> numList = (List<Long>)this.numList.clone();

        for (int i = 0; i < operationsOrder.size(); i++) {
            char nowOrderOperation = operationsOrder.get(i);
            for (int j = 0; j < operationList.size(); j++) {
                if (operationList.get(j) == nowOrderOperation) {
                    long result = 0;
                    if (nowOrderOperation == '+') {
                        result = numList.get(j) + numList.get(j + 1);
                    }
                    if (nowOrderOperation == '-') {
                        result = numList.get(j) - numList.get(j + 1);
                    }
                    if (nowOrderOperation == '*') {
                        result = numList.get(j) * numList.get(j + 1);
                    }

                    numList.remove(j + 1);
                    numList.remove(j);
                    numList.add(j, result);

                    operationList.remove(j);
                    j--;
                }
            }
        }

        return Math.abs(numList.get(0));
    }

    private void dfs(int depth, List<Character> operationList) {
        if (depth == 3) {
            long result = getResult(operationList);
            answer = Math.max(answer, result);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (visited[i]) continue;

            operationList.add(operations[i]);
            visited[i] = true;

            dfs(depth + 1, operationList);

            visited[i] = false;
            operationList.remove((Object)operations[i]);
        }
    }
}
