package 배열돌리기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	static int[][] map;
	static int[][] tmpMap;
	
	public void rotate(int x, int y, int len)
	{
		tmpMap = new int[len+1][len+1];
		for(int i = 0; i <= len; i++)
		{
			for(int j = 0; j <= len; j++)
			{
				
				tmpMap[i][j] = map[x+len-j][y+i];
			}
		}
		
		for(int i = 0; i <= len; i++)
		{
			System.out.println(Arrays.toString(tmpMap[i]));
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		T.rotate(1, 2, 2);
	}
}
