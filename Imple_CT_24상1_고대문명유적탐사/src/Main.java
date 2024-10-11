import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Point
{
	int x;
	int y;
	int cnt;
	public Point(int x, int y, int cnt)
	{
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}	
}

class Prior implements Comparable<Prior>
{
	int x;
	int y;
	int dir;
	int cnt;
	public Prior(int x, int y, int dir, int cnt)
	{
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(Prior o)
	{
		if(this.cnt == o.cnt)
		{
			if(this.dir == o.dir)
			{
				if(this.x == o.x)
				{
					return this.x - o.x;
				}
				else
				{
					return this.y - o.y;
				}
			}
			else
			{
				return this.dir - o.dir;
			}
		}
		else
		{
			return o.cnt - this.cnt;
		}
	}
}

class Main
{
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N = 5;
	static int K;
	static int M;
	
	static int[][] map = new int[N][N];
	static int[][] tmpMap = new int[N][N];
	
	static int[] arr;
	static int arrIdx;
	
	static boolean boom;
	
	static int solution;
	
	public void fill()
	{
		for(int j = 0; j < 5; j++)
		{
			for(int i = 4; i >= 0; i--)
			{
				if(map[i][j] == 0)
				{
					map[i][j] = arr[arrIdx];
					arrIdx++;
				}
			}
		}
	}
	
	public void removeBFS()
	{
		Queue<Point> q = new LinkedList<>();
		
		boolean[][] visited = new boolean[N][N];
		
		boom = false;
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				Queue<Point> count = new LinkedList<>();
				if(visited[i][j] == false)
				{
					visited[i][j] = true;
					int answer = 1;
					q.add(new Point(i, j, 1));
					count.add(new Point(i, j, 0));
					while(!q.isEmpty())
					{
						Point tmp = q.poll();
						for(int d = 0; d < 4; d++)
						{
							int ni = tmp.x + di[d];
							int nj = tmp.y + dj[d];
							if(ni >= 0 && ni < N && nj >= 0 && nj < N && visited[ni][nj] == false && tmpMap[i][j] == tmpMap[ni][nj])
							{
								q.add(new Point(ni, nj, tmp.cnt+1));
								count.add(new Point(ni, nj, tmp.cnt));
								visited[ni][nj] = true;
								answer++;
							}
						}
					}
					if(answer >= 3)
					{
						solution+=answer;
						while(!count.isEmpty())
						{
							Point tmp = count.poll();
							tmpMap[tmp.x][tmp.y] = 0;
							boom = true;
						}
					}
					
				}
			}
		}
	}
	
	public int BFS()
	{
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		int returnAnswer = 0;
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				if(visited[i][j] == false)
				{
					visited[i][j] = true;
					int answer = 1;
					q.add(new Point(i, j, 1));
					while(!q.isEmpty())
					{
						Point tmp = q.poll();
						for(int d = 0; d < 4; d++)
						{
							int ni = tmp.x + di[d];
							int nj = tmp.y + dj[d];
							if(ni >= 0 && ni < N && nj >= 0 && nj < N && visited[ni][nj] == false && tmpMap[i][j] == tmpMap[ni][nj])
							{
								q.add(new Point(ni, nj, tmp.cnt+1));
								visited[ni][nj] = true;
								answer++;
							}
						}
					}
					if(answer >= 3)
					{
						returnAnswer += answer;
					}
					
				}
			}
		}
		return returnAnswer;
	}
	
	public void rotate(int x, int y, int cnt)
	{
		int[][] tmp = new int[3][3];
		
		// 배열돌리기
		for(int k = 0; k < cnt; k++)
		{
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					tmp[i][j] = tmpMap[x+3-1-j][y+i];
				}
			}
			
			// 3x3배열 tmpMap배열에 저장
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					tmpMap[i+x][j+y] = tmp[i][j];
				}
			}
		}
	}
	
	public void realMap()
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				map[i][j] = tmpMap[i][j];
			}
		}
	}
	
	public void copyMap()
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				tmpMap[i][j] = map[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
				tmpMap[i][j] = map[i][j];
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int count = 0; count < K; count++)
		{
			solution = 0;
			PriorityQueue<Prior> pq = new PriorityQueue<>();
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					for(int k = 1; k < 4; k++)
					{
						T.rotate(i, j, k);
						int score = T.BFS();
						pq.add(new Prior(i, j, k, score));
						T.copyMap();
					}
				}
			}
			
			Prior tmp = pq.poll();
			T.rotate(tmp.x, tmp.y, tmp.dir);
			
			while(true)
			{
				T.removeBFS();
				if(boom == false)
				{
					break;
				}
				else
				{
					T.realMap();
				}
				T.fill();
				T.copyMap();
				
			}
			if(solution > 0)
			{
				System.out.print(solution + " ");
			}
		}
	}
}