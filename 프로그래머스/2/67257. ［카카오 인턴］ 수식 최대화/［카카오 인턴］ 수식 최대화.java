import java.util.*;

class Solution {
    
    boolean[] visited;
    ArrayList<String> operators = new ArrayList();
    ArrayList<Long> numbers = new ArrayList();
    long answer = 0;
    ArrayList<String> origin = new ArrayList();
    
    public long solution(String expression) {
        
        // 연산자와 숫자 구분
        String[] arr = expression.split("((?<=\\d)(?=\\D))|((?<=\\D)(?=\\d))");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("+") || arr[i].equals("-") || arr[i].equals("*")) {
                operators.add(arr[i]);
            } else {
                numbers.add(Long.parseLong(arr[i]));
            }
        }
        
        // 우선순위 정하고 계산
        origin.add("+");
        origin.add("-");
        origin.add("*");
        visited = new boolean[3];
        dfs(0, new String[3]);
        
        return answer;
    }
    
    public void dfs(int depth, String[] priority) {
        if (depth == 3) {
            // 우선순위대로 계산하기
            answer = Math.max(answer, calculate(priority, (ArrayList<String>) operators.clone(), (ArrayList<Long>)numbers.clone()));
            return;
        }
        
        for (int i = 0; i < origin.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                priority[depth] = origin.get(i);
                
                dfs(depth + 1, priority);
                
                visited[i] = false;
            }
        }
    }
    
    public long calculate(String[] priority, ArrayList<String> operators, ArrayList<Long> numbers) {
        for (int i = 0; i < priority.length; i++) {
            System.out.print(priority[i] + " ");
        }
        System.out.println();
    
        for (int i = 0; i < priority.length; i++) {
            System.out.println(priority[i]);
            for (int j = 0; j < operators.size(); j++) {
                if (operators.get(j).equals(priority[i])) {
                    long result;
                    System.out.println(numbers.get(j)  +  priority[i]  +numbers.get(j + 1));
                    if (priority[i].equals("+")) {
                        result = numbers.get(j) + numbers.get(j + 1);
                    } else if (priority[i].equals("-")) {
                        result = numbers.get(j) - numbers.get(j + 1);
                    } else {
                        result = numbers.get(j) * numbers.get(j + 1);
                    }
                    
                    numbers.remove(j + 1);
                    numbers.remove(j);
                    numbers.add(j, result);
                    
                    operators.remove(j--);
                    
                    System.out.println("result = " + result);
                }
            }
        }
        System.out.println(numbers.get(0));
        
        return Math.abs(numbers.get(0));
    }
}