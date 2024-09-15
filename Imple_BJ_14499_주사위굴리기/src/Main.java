import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] di = {0, 0, -1, 1};
	static int[] dj = {1, -1, 0, 0};
	
	static int N;
	static int M;
	static int X;
	static int Y;
	static int K;
	static int[][] map;
	static int[] dice = new int[7];
	
	public void up()
	{
		int tmp = dice[2];
		dice[2] = dice[1];
		dice[1] = dice[5];
		dice[5] = dice[6];
		dice[6] = tmp;
		System.out.println(dice[1]);
		if(map[X][Y] == 0)
		{
			map[X][Y] = dice[6];
		}
		else
		{
			dice[6] = map[X][Y];
			map[X][Y] = 0;
		}
	}
	public void down()
	{
		int tmp = dice[6];
		dice[6] = dice[5];
		dice[5] = dice[1];
		dice[1] = dice[2];
		dice[2] = tmp;
		System.out.println(dice[1]);
		if(map[X][Y] == 0)
		{
			map[X][Y] = dice[6];
		}
		else
		{
			dice[6] = map[X][Y];
			map[X][Y] = 0;
		}
	}
	public void left()
	{
		int tmp = dice[6];
		dice[6] = dice[3];
		dice[3] = dice[1];
		dice[1] = dice[4];
		dice[4] = tmp;
		System.out.println(dice[1]);
		if(map[X][Y] == 0)
		{
			map[X][Y] = dice[6];
		}
		else
		{
			dice[6] = map[X][Y];
			map[X][Y] = 0;
		}
	}
	public void right()
	{
		int tmp = dice[4];
		dice[4] = dice[1];
		dice[1] = dice[3];
		dice[3] = dice[6];
		dice[6] = tmp;
		System.out.println(dice[1]);
		if(map[X][Y] == 0)
		{
			map[X][Y] = dice[6];
		}
		else
		{
			dice[6] = map[X][Y];
			map[X][Y] = 0;
		}
	}
	
	public void start(int k)
	{
		int ni = X + di[k];
		int nj = Y + dj[k];
//		System.out.println(ni + " " + nj);
		if(ni >= 0 && ni < N && nj >= 0 && nj < M)
		{
			X = ni;
			Y = nj;
			if(k == 0)
			{
				left();
			}
			else if(k == 1)
			{
				right();
			}
			else if(k == 2)
			{
				up();
			}
			else if(k == 3)
			{
				down();
			}
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
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++)
		{
			int k = Integer.parseInt(st.nextToken())-1;
			T.start(k);
		}
		
//		dice = new int[7];
		
	}
}
