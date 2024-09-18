import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static int[][] copyMap;
	
	static int solution;
	
	public void calc()
	{
		int sum = 0;
		int count = 0;
		for(int i = 1; i <= N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				sum += map[i][j];
				if(map[i][j] > 0)
				{
					count++;
				}
			}
		}
		double ans = (double)sum / count;
//		System.out.println(ans);
		for(int i = 1; i <= N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				if(map[i][j] > 0)
				{
					if(map[i][j] == ans)
					{
						continue;
					}
					if(map[i][j] > ans)
					{
						map[i][j] -= 1;
					}
					else
					{
						map[i][j] += 1;
					}
				}
			}
		}
	}
	
	public void copy()
	{
		for(int i = 0; i <= N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				map[i][j] = copyMap[i][j];
			}
		}
	}
	
	
	
	public void remove()
	{
		boolean isCheck = false;
		for(int i = 0; i <= N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				copyMap[i][j] = map[i][j];
			}
		}
		for(int i = 1; i <= N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				for(int k = 0; k < 4; k++)
				{
					int ni = i + di[k];
					int nj = j + dj[k];
					if(nj == -1)
					{
						nj = M-1;
					}
					if(ni >= 0 && ni <= N && nj >= 0 && nj < M)
					{
						if(map[ni][nj] > 0)
						{
							if(map[ni][nj] == map[i][j])
							{
								copyMap[ni][nj] = 0;
								copyMap[i][j] = 0;
								isCheck = true;
							}
						}
						
					}
				}
			}
		}
		copy();
		if(!isCheck)
		{
			calc();
		}
	}
	
	public void rotate(int no, int dir, int k)
	{
		for(int c = 0; c < k; c++)
		{
			for(int i = no; i <= N; i+=no)
			{
				
				if(dir == 1)
				{
					int tmp = map[i][0];
					for(int j = 0; j < M-1; j++)
					{
						map[i][j] = map[i][j+1];
					}
					map[i][M-1] = tmp;
				}
				else
				{
					int tmp = map[i][M-1];
					for(int j = M-1; j > 0; j--)
					{
						map[i][j] = map[i][j-1];
					}
					map[i][0] = tmp;
				}
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
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M];
		copyMap = new int[N+1][M];
		
		for(int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < K; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			k = k%M;
			T.rotate(x, d, k);
			T.remove();
		}
		
//		for(int i = 1; i <= N; i++)
//		{
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				solution += map[i][j];
			}
		}
		
		System.out.println(solution);
	}
}
