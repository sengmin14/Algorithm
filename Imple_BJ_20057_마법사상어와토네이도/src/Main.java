import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main
{
	static int[] di = {0, 1, 0, -1};
	static int[] dj = {-1, 0, 1, 0};
	static int[] dc = {1, 1, 2, 2};
	
	static int[] per = {1, 1, 7, 7, 2, 2, 10, 10, 5};
	static int[][] diri = {{-1, 1, -1, 1, -2, 2, -1, 1, 0},{-1, -1, 0, 0, 0, 0, 1, 1, 2},{-1, 1, -1, 1, -2, 2, -1, 1, 0},{1, 1, 0, 0, 0, 0, -1, -1, -2}};
	static int[][] dirj = {{1, 1, 0, 0, 0, 0, -1, -1, -2},{1, -1, 1, -1, 2, -2, 1, -1, 0},{-1, -1, 0, 0, 0, 0, 1, 1, 2},{-1, 1, -1, 1, -2, 2, -1, 1, 0}};
	
	static int N;
	static int[][] map;
	
	static int out;
	
	public void spread(int x, int y, int k)
	{
		if(map[x][y] > 0)
		{
			int total = map[x][y];
			int center = map[x][y];
			map[x][y] = 0;
			for(int i = 0; i < 9; i++)
			{
				int ni = x + diri[k][i];
				int nj = y + dirj[k][i];
//				System.out.println(ni + " " + nj);
				if(ni >= 0 && ni < N && nj >= 0 && nj < N)
				{
					map[ni][nj] += total * per[i]/100;
					center -= total * per[i]/100;
				}
				else
				{
					out += total * per[i]/100;
					center -= total * per[i]/100;
				}
			}
			int ni = x + di[k];
			int nj = y + dj[k];
			if(ni >= 0 && ni < N && nj >= 0 && nj < N)
			{
				map[ni][nj] += center;
			}
			else
			{
				out += center;
			}
		}
		else
		{
			return;
		}
//		System.out.println("     ");
//		for(int i = 0; i < N; i++)
//		{
//			System.out.println(Arrays.toString(map[i]));
//		}
	}
	
	public void tonado(int x, int y)
	{
		while(true)
		{
			for(int k = 0; k < 4; k++)
			{
				for(int c = 0; c < dc[k]; c++)
				{
					x = x + di[k];
					y = y + dj[k];
					if(x >= 0 && x < N && y >= 0 && y < N)
					{
						spread(x, y, k);
					}
					else
					{
						return;
					}
				}
			}
			for(int i = 0; i < 4; i++)
			{
				dc[i] += 2;
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
		map = new int[N][N];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		T.tonado(N/2, N/2);
		System.out.println(out);
	}
}