import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    public static class Pair{
        int x;
        int y;
        int x2;
        int y2;

        Pair(int x, int y, int x2, int y2){
            this.x = x;
            this.y = y;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][][] board = new int[4][n][n];
        Pair[][][] sv = new Pair[4][n][n];
        int sx = 0;
        int sy = 0;
        for(int i = 0; i < n; i++){
            String sam = br.readLine();
            for(int j = 0; j < n; j++){
                sv[0][i][j] = new Pair(0,0,0,0);
                if(sam.charAt(j) == '.'){
                    board[0][i][j] = 0;
                }
                else if(sam.charAt(j) == 'X'){
                    board[0][i][j] = -1;
                }
                else{
                    board[0][i][j] = 0;
                    sx = i;
                    sy = j;
                }
            }
        }


        int state = 0;
        for(int rot = 1; rot <= 3; rot++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    board[rot][i][j] = board[rot-1][n-1-j][i];
                    sv[rot][i][j] = new Pair(0,0,0,0);
                }
            }
        }
        for(int rot = 0; rot < 4; rot++){
            for(int i = 0; i < n; i++){
                int ey1 = 0;
                int ey2 = n-1;

                for(int j = 0; j < n; j++){
                    if(board[rot][i][j] == 0 || board[rot][i][j] == 1){
                        sv[rot][i][j].x = n - 1 - ey1;
                        sv[rot][i][j].y = i;
                    }
                    else{
                        ey1 = j+1;
                    }
                }

                for(int j = n-1; j >= 0; j--){
                    if(board[rot][i][j] == 0 || board[rot][i][j] == 1){
                        sv[rot][i][j].x2 = ey2;
                        sv[rot][i][j].y2 = n - 1 - i;
                    }
                    else{
                        ey2 = j-1;
                    }
                }
            }
        }


        int spin = 0;
        for(int turn = 0; turn < k; turn ++){
            String nxt = br.readLine();
            if(nxt.equals("L")){
                int nx = sv[spin][sx][sy].x;
                int ny = sv[spin][sx][sy].y;
                spin--;
                if(spin < 0) spin = 3;
                sx = nx;
                sy = ny;
            }
            else{
                int nx = sv[spin][sx][sy].x2;
                int ny = sv[spin][sx][sy].y2;
                spin++;
                if(spin >= 4) spin = 0;
                sx = nx;
                sy = ny;
            }

        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[spin][i][j] == 0){
                    if(sx == i && sy == j){
                        sb.append("L");
                    }
                    else{
                        sb.append(".");
                    }
                }
                else if(board[spin][i][j] == -1) sb.append("X");
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }
}
