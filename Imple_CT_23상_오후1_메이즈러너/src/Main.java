import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point
{
	int x;
	int y;
	int no;
	public Point(int x, int y, int no)
	{
		this.x = x;
		this.y = y;
		this.no = no;
	}
}

class Exit
{
	int x;
	int y;
	public Exit(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
}

class Main
{
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static int[][] tmpMap;
	
	static ArrayList<Integer>[][] runnerMap;
	static ArrayList<Integer>[][] tmpRunnerMap;
	static Queue<Point> q;
	static Exit exit;
	
	public int length(int x, int y)
	{
		return Math.abs(x - exit.x) + Math.abs(y - exit.y);
	}
	
	public void runnerMove(int i, int j, int k)
	{
		int len = length(i, j);
		for(int d = 0; d < 4; d++)
		{
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni >= 0 && ni < N && nj >= 0 && nj < N && map[ni][nj] <= 0)
			{
				if(map[ni][nj] == 0)
				{
					int compareLen = length(ni, nj);
					if(compareLen < len)
					{
						runnerMap[ni][nj].add(k);
						q.add(new Point(ni, nj, k));
						return;
					}
				}
				else
				{
					return;
				}
			}
		}
		runnerMap[i][j].add(k);
		q.add(new Point(i, j, k));
	}
	
	public void move()
	{
		q = new LinkedList<>();
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				for(int k = runnerMap[i][j].size()-1; k >= 0; k--)
				{
					runnerMove(i, j, runnerMap[i][j].get(k));
					runnerMap[i][j].remove(k);
				}
			}
		}
		
		
	}
	
	public int checkLen()
	{
		int len = Integer.MAX_VALUE;
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			int comLen = length(tmp.x, tmp.y);
			if(len > comLen)
			{
				len = comLen;
			}
		}
		
		return len;
	}
	
	public void copyRunnerMap(int x, int y, int len)
	{
		tmpRunnerMap = new ArrayList[len+1][len+1];
		for(int i = 0; i <= len; i++)
		{
			for(int j = 0; j <= len; j++)
			{
				tmpRunnerMap[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i <= len; i++)
		{
			for(int j = 0; j <= len; j++)
			{
				for(int k = runnerMap[x+len-j][y+i].size()-1; k >= 0; k--)
				{
					tmpRunnerMap[i][j].add(runnerMap[x+len-j][y+i].get(k));
				}
			}
		}
		
		for(int i = 0; i <= len; i++)
		{
//			System.out.println(Arrays.toString(tmpRunnerMap[i]));
		}
	}
	
	public void copyMap(int x, int y, int len)
	{
		tmpMap = new int[len+1][len+1];
		for(int i = 0; i <= len; i++)
		{
			for(int j = 0; j <= len; j++)
			{
				if(map[x+len-j][y+i] > 0)
				{
					map[x+len-j][y+i] -= 1;
				}
				tmpMap[i][j] = map[x+len-j][y+i];
			}
		}
	}
	
	public void rotate(int x, int y, int len)
	{
		copyMap(x, y, len);
		copyRunnerMap(x, y, len);
		
		for(int i = 0; i <= len; i++)
		{
			for(int j = 0; j <= len; j++)
			{
				map[i+x][j+y] = tmpMap[i][j];
				if(map[i+x][i+y] == -1)
				{
					exit = new Exit(i+1, i+y);
				}
			}
		}
		
		for(int i = 0; i <= len; i++)
		{
			for(int j = 0; j <= len; j++)
			{
				runnerMap[i+x][j+y] = tmpRunnerMap[i][j];
			}
		}
	}
	
	public void findIdx(int len)
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				int x = i;
				int y = j;
				int nx = i + len;
				int ny = j + len;
				boolean ex = false;
				boolean hu = false;
				for(int a = i; a <= nx; a++)
				{
					for(int b = j; b <= ny; b++)
					{
//						System.out.println(a + " " + b + " " + len);
						if(map[a][b] == -1)
						{
							ex = true;
						}
						if(runnerMap[a][b].size() > 0)
						{
							hu = true;
						}
						if(ex == true && hu == true)
						{
							// System.out.println(x + " " + y);
							rotate(x, y, len);
							return;
						}	
					}
				}
			}
		}
		
		
	}
	
	public void solve()
	{		
		move();
		int len = checkLen();
		findIdx(len); // 길이구하고 회전까지 포함
		
		for(int i = 0; i < N; i++)
		{
			
		}
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N x N 격자판
		M = Integer.parseInt(st.nextToken()); // runner 수
		K = Integer.parseInt(st.nextToken()); // 게임 시간
		
		map = new int[N][N];
		tmpMap = new int[N][N];
		runnerMap = new ArrayList[N][N];
		tmpRunnerMap = new ArrayList[N][N];
		q = new LinkedList<>();
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				runnerMap[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 1; i <= M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			runnerMap[x][y].add(i);
		}
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken())-1;
		int y = Integer.parseInt(st.nextToken())-1;
		exit = new Exit(x, y);
		map[x][y] = -1;
		
		for(int count = 0; count < 2; count++)
		{
			T.solve();
			System.out.println();
		}
//		T.solve();
	}
}