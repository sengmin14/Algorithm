import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    public static class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int blocklen = Integer.parseInt(st.nextToken());
        int[][] base = new int[5][5];
        int[] block = new int[blocklen];
        int blockidx = 0;

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++){
                base[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < blocklen; i++){
            block[i] = Integer.parseInt(st.nextToken());
        }

        for(int turn = 0; turn < T; turn++) {
            int num = -1;
            int angle = 5;
            int row = -1;
            int col = -1;

            int[][] nxtbase = new int[5][5];

            for(int i1 = 1; i1 <= 3; i1++){
                for(int j1 = 1; j1 <= 3; j1++){
                    int[][] tmp = new int[5][5];
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 5; j++) tmp[i][j] = base[i][j];
                    }

                    for(int rot = 0; rot < 3; rot++) { // 최대 3번 돌림
                        int x1 = i1-1;
                        int y1 = j1-1;
                        int x2 = i1+1;
                        int y2 = j1+1;

                        int[] box1 = new int[4];
                        for(int i = 0; i < 3; i++) box1[i] = tmp[x1][y1+i];
                        int[] box2 = new int[4];
                        for(int i = 0; i < 3; i++) box2[i] = tmp[x1+i][y2];
                        int[] box3 = new int[4];
                        for(int i = 0; i < 3; i++) box3[i] = tmp[x2][y1+i];
                        int[] box4 = new int[4];
                        for(int i = 0; i < 3; i++) box4[i] = tmp[x1+i][y1];

                        for(int i = 0; i < 3; i++) tmp[x1][y1+i] = box4[2-i];
                        for(int i = 0; i < 3; i++) tmp[x1+i][y2] = box1[i];
                        for(int i = 0; i < 3; i++) tmp[x2][y2-i] = box2[i];
                        for(int i = 0; i < 3; i++) tmp[x2-i][y1] = box3[2-i];

                        // 회전 완료하고 몇개 부서지는지 확인할 차례
                        int[][] tmpck = new int[5][5];
                        for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 5; j++) tmpck[i][j] = tmp[i][j];
                        }

                        boolean[][] vis = new boolean[5][5];
                        int cnt = 0;
                        for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 5; j++){
                                if(vis[i][j]) continue;
                                vis[i][j] = true;
                                Queue<Pair> q = new ArrayDeque<>();
                                q.offer(new Pair(i,j));
                                int val = tmp[i][j];
                                int subcnt = 0;
                                while(!q.isEmpty()){
                                    int x = q.peek().x;
                                    int y = q.peek().y;
                                    subcnt++;
                                    q.poll();
                                    for(int dir = 0; dir < 4; dir++){
                                        int nx = x + dx[dir];
                                        int ny = y + dy[dir];
                                        if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                                        if(vis[nx][ny]) continue;
                                        if(tmp[nx][ny] != val) continue;
                                        vis[nx][ny] = true;
                                        q.offer(new Pair(nx,ny));
                                    }
                                }
                                if(subcnt >= 3) cnt += subcnt;
                            }
                        }

                        if(cnt != 0){
                            boolean judge = false;

                            if(num < cnt){ // 유물수 큰거 우선
                                num = cnt;
                                angle = rot;
                                row = i1;
                                col = j1;
                                judge = true;
                            }
                            else if(num == cnt){
                                if(rot < angle){ // 회전한 각도 작은거 우선
                                    num = cnt;
                                    angle = rot;
                                    row = i1;
                                    col = j1;
                                    judge = true;
                                }
                                else if(rot == angle){
                                    if(j1 < col){ // 열 작은거 우선
                                        num = cnt;
                                        angle = rot;
                                        row = i1;
                                        col = j1;
                                        judge = true;
                                    }
                                    else if(j1 == col){
                                        if(i1 < row){ // 행 작은거 우선
                                            num = cnt;
                                            angle = rot;
                                            row = i1;
                                            col = j1;
                                            judge = true;
                                        }
                                    }
                                }
                            }


                            if(judge){
                                for(int i = 0; i < 5; i++) {
                                    for (int j = 0; j < 5; j++) {
                                        nxtbase[i][j] = tmp[i][j];
                                    }
                                }
                            }



                        }

                    }
                }
            }

            if(num == -1) break;

            // System.out.println(num + " " + row + " " + col + " " + angle);
            for(int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    base[i][j] = nxtbase[i][j];
                    //System.out.print(base[i][j] + " ");
                }
                //System.out.println();
            }


            int totbomb = 0;
            while(true){

                int bomb = 0;
                boolean[][] vis = new boolean[5][5];


                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        if(vis[i][j]) continue;
                        Queue<Pair> q = new ArrayDeque<>();
                        Queue<Pair> sv = new ArrayDeque<>();
                        q.offer(new Pair(i,j));
                        int val = base[i][j];
                        vis[i][j] = true;
                        int subcnt = 0;
                        while(!q.isEmpty()){
                            int x = q.peek().x;
                            int y = q.peek().y;
                            sv.offer(new Pair(x,y));
                            subcnt++;
                            q.poll();
                            for(int dir = 0; dir < 4; dir++){
                                int nx = x + dx[dir];
                                int ny = y + dy[dir];
                                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                                if(vis[nx][ny]) continue;
                                if(base[nx][ny] != val) continue;
                                vis[nx][ny] = true;
                                q.offer(new Pair(nx,ny));
                            }
                        }
                        if(subcnt >= 3){
                            bomb += subcnt;
                            while(!sv.isEmpty()){
                                int x = sv.peek().x;
                                int y = sv.peek().y;
                                sv.poll();
                                base[x][y] = 0;
                            }
                        }
                    }
                }

                if(bomb == 0) break;
                totbomb += bomb;


                for(int i = 0; i  < 5; i++){
                    for(int j = 4; j >= 0; j--){
                        if(base[j][i] == 0){
                            base[j][i] = block[blockidx];
                            blockidx++;
                        }
                    }
                }


            }

            sb.append(totbomb + " ");
        }

        System.out.println(sb);

    }
}