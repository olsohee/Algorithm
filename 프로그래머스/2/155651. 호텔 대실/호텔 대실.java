import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        
        List<Book> list = new ArrayList<>();
        for (String[] time : book_time) {
            int start = convertToTime(time[0], false);
            int end = convertToTime(time[1], true); 
            // System.out.println(start + " ~ " + end); 
            list.add(new Book(start, end));
        }
        Collections.sort(list);
        
        
        Queue<Integer> que = new PriorityQueue<>((o1, o2) -> o1 - o2);
        for (Book book : list) {
            if (que.isEmpty()) {
                que.add(book.end);
                continue;
            }
            if (que.peek() <= book.start) {
                que.poll();
            } 
            que.add(book.end);
        }
        
        return que.size(); // 필요한 최소 객실 수
    }
    
    private int convertToTime(String str, boolean endTime) { 
        int hour = Integer.parseInt(str.split(":")[0]);
        int minute = Integer.parseInt(str.split(":")[1]);
        if (endTime) { // 종료시간이면 10분 추가하기(청소 시간 반영)
            minute += 10; 
            if (minute >= 60) {
                minute -= 60;
                hour++;
            }
        }
        return hour*100 + minute;
    }
    
    private class Book implements Comparable<Book> {
        int start;
        int end;
        public Book(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Book o) {
            return this.start - o.start;
        }
    }
}