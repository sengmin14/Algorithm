import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;


class Knight
{
	int r;
	int c;
	int h;
	int w;
	int k;
	public Knight(int r, int c, int h, int w, int k)
	{
		this.r = r;
		this.c = c;
		this.h = h;
		this.w = w;
		this.k = k;
	}
}

public class Main {
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int L;
	static int N;
	static int Q;
	
	static int[][] map;
	static int[][] knightMap;
	
	static Knight[] knights;
	
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		knights = new Knight[N];
		
		for(int i = 0; i < L; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < L; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			knights[i] = new Knight(r, c, h, w, k);
		}
		
		
	}
	
}
