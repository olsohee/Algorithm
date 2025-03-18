class Solution {
    public int solution(int n, int w, int num) {
        int h = n / w;
        if (n % w > 0) h++;
        
        int[][] map = new int[h][w];
        int value = 1;
        for (int i = 0; i < h; i++) {
            // 오른쪽으로 채우기
            if (i == 0 || i % 2 == 0) {
                for (int j = 0; j < w; j++) {
                    map[i][j] = value++;
                }
            } 
            // 왼쪽으로 채우기
            else {
                for (int j = w - 1; j >= 0; j--) {
                    map[i][j] = value++;
                }
            }
        }
        
        // num의 위치 찾기
        int y = 0;
        int x = 0;
        outer: for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == num) {
                    y = i;
                    x = j;
                    break outer;
                }
            }
        }
        
        int answer = 0;
        while (y < h) {
            if (map[y][x] > n) break;
            answer++;
            y++;
        }
        
        return answer;
    }
}