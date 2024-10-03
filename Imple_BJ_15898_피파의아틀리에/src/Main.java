import java.io.*;
import java.util.*;
 
public class Main {
 
    static int n, ans = -1000000;
    static Status[][][] ingridients;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ingridients = new Status[n][4][4];
        visited = new boolean[n];
        Status[][] map = new Status[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new Status(0, 'W');
            }
        }
        
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    ingridients[i][j][k] = new Status(0, 'W');
                    ingridients[i][j][k].quality = Integer.parseInt(st.nextToken());
                }
            }
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    ingridients[i][j][k].color = st.nextToken().charAt(0);
                }
            }
        }
        perm(0, map);
        System.out.println(ans);
    }
    
    // 10P3의 경우를 모두 세는 method
    static void perm(int cnt, Status[][] map) {
        if (cnt == 3) {
            ans = Math.max(ans, calc(map));
            return;
        }
        
        for (int idx = 0; idx < n; idx++) {
            if (visited[idx]) continue;
            visited[idx] = true;
            outer: for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (cnt == 0 && j > 0) break outer;
                    for (int dir = 0; dir < 4; dir++) {
                        Status[][] tempMap = copyMap(map);
                        put(tempMap, idx, i, j, dir);
                        perm(cnt+1, tempMap);
                    }
                }
            }
            visited[idx] = false;
        }
    }
    
    // 가마의 (r, c)부터 (r+3, c+3) 까지 90*dir도만큼 시계 반대방향으로 회전시킨 재료를 넣는 method
    static void put(Status[][] map, int idx, int r, int c, int dir) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (dir == 0) {
                    map[i+r][j+c].quality += ingridients[idx][i][j].quality;
                    if (map[i+r][j+c].quality < 0) map[i+r][j+c].quality = 0;
                    else if (map[i+r][j+c].quality > 9) map[i+r][j+c].quality = 9;
                    if (ingridients[idx][i][j].color == 'W') continue;
                    map[i+r][j+c].color = ingridients[idx][i][j].color;
                }
                else if (dir == 1) {
                    map[i+r][j+c].quality += ingridients[idx][j][3-i].quality;
                    if (map[i+r][j+c].quality < 0) map[i+r][j+c].quality = 0;
                    else if (map[i+r][j+c].quality > 9) map[i+r][j+c].quality = 9;
                    if (ingridients[idx][j][3-i].color == 'W') continue;
                    map[i+r][j+c].color = ingridients[idx][j][3-i].color;
                }
                else if (dir == 2) {
                    map[i+r][j+c].quality += ingridients[idx][3-i][3-j].quality;
                    if (map[i+r][j+c].quality < 0) map[i+r][j+c].quality = 0;
                    else if (map[i+r][j+c].quality > 9) map[i+r][j+c].quality = 9;
                    if (ingridients[idx][3-i][3-j].color == 'W') continue;
                    map[i+r][j+c].color = ingridients[idx][3-i][3-j].color;
                }
                else {
                    map[i+r][j+c].quality += ingridients[idx][3-j][i].quality;
                    if (map[i+r][j+c].quality < 0) map[i+r][j+c].quality = 0;
                    else if (map[i+r][j+c].quality > 9) map[i+r][j+c].quality = 9;
                    if (ingridients[idx][3-j][i].color == 'W') continue;
                    map[i+r][j+c].color = ingridients[idx][3-j][i].color;
                }
            }
        }
    }
    
    // 품질을 계산하는 method
    static int calc(Status[][] map) {
        int ret = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++ ) {
                int c = 0;
                switch (map[i][j].color) {
                case 'R':
                    c = 7;
                    break;
                case 'B':
                    c = 5;
                    break;
                case 'G':
                    c = 3;
                    break;
                case 'Y':
                    c = 2;
                    break;
                }
                ret += c*map[i][j].quality;
            }
        }
        return ret;
    }
    
    static Status[][] copyMap(Status[][] map) {
        Status[][] ret = new Status[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++ ) {
                ret[i][j] = new Status(map[i][j].quality, map[i][j].color);
            }
        }
        return ret;
    }
    
    static class Status {
        int quality;
        char color;
        
        public Status(int quality, char color) {
            this.quality = quality;
            this.color = color;
        }
    }
}