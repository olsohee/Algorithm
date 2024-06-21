import java.util.*;

class Solution {
    
    int n, m;
    int r, c;
    int k;
    int[] dx = {0, -1, 1, 0};
    int[] dy = {1, 0, 0, -1};
    List<String> list = new ArrayList<>();
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n; this.m = m;
        this.r = r; this.c = c;
        this.k = k;
        
        dfs(0, x, y, "");
        
        if (list.size() == 0) {
            return "impossible";
        } 
        // Collections.sort(list);
        return list.get(0);
    }
    
    private void dfs(int cnt, int y, int x, String route) {
        if (list.size() >= 1) return;
        if (cnt == k) {
            if (y == r && x == c) {
                list.add(route);
            }
            return;
        }
        
       
        
        int distance = getDistance(y, x);
        // 도착점까지의 거리, 남은 이동 가능 거리 "거리 비교"
        if (distance > k - cnt) {
            return;
        }
        
        // 도착점까지의 거리, 남은 이동 가능 거리 "짝/홀 비교"
        if (!canGo(distance, k - cnt)) {
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if (ny <= 0 || ny > n || nx <= 0 || nx > m) {
                continue;
            }
            
            String newRoute = "";
            if (i == 0) newRoute = route + "d";
            if (i == 1) newRoute = route + "l";
            if (i == 2) newRoute = route + "r";
            if (i == 3) newRoute = route + "u";
            dfs(cnt + 1, ny, nx, newRoute);
        }
    }
    
    public int getDistance(int y, int x) {
        return Math.abs((y - r)) + Math.abs(x - c);
    }
    
    public boolean canGo(int distance, int k) {
        // if (k - distance % 2 != 0) {
        //     return false;
        // }
        // return true;
        if (distance % 2 == 0 && k % 2 == 0) {
            return true;
        }
        if (distance % 2 == 1 && k % 2 == 1) {
            return true;
        }
        return false;
    }
}