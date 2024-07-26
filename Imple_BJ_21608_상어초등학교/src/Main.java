import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Seat implements Comparable<Seat>
{
	int x;
	int y;
	int like;
	int empty;
	public Seat(int x, int y, int like, int empty)
	{
		super();
		this.x = x;
		this.y = y;
		this.like = like;
		this.empty = empty;
	}
	@Override
	public int compareTo(Seat o)
	{
		if(this.like == o.like)
		{
			if(this.empty == o.empty)
			{
				if(this.x == o.x)
				{
					return this.y - o.y;
				}
				else
				{
					return this.x - o.x;
				}
			}
			else
			{
				return o.empty - this.empty;
			}
		}
		else
		{
			return o.like - this.like;
		}
	}
}

class Main
{
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	static int N;
	static int[][] map;
	static int[] order;
	static ArrayList[] list;
	
	int answer = 0;
	
	public int count(int x, int y)
	{
		int now = map[x][y];
		int cnt = 0;
		for(int i = 0; i < 4; i++)
		{
			int ni = x + di[i];
			int nj = y + dj[i];
			if(ni >= 1 && ni <= N && nj >= 0 && nj <= N)
			{
				if(list[now].contains(map[ni][nj]))
				{
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	public int findLike(int x, int y, int no)
	{
		int like = 0;
		for(int i = 0; i < 4; i++)
		{
			int ni = x + di[i];
			int nj = y + dj[i];
			if(ni >= 1 && ni <= N  && nj >= 1 && nj <= N)
			{
				if(list[no].contains(map[ni][nj]))
				like++;
			}
		}
		return like;
	}
	
	public int findSeat(int x, int y, int no)
	{
		int empty = 0;
		for(int i = 0; i < 4; i++)
		{
			int ni = x + di[i];
			int nj = y + dj[i];
			if(ni >= 1 && ni <= N  && nj >= 1 && nj <= N)
			{
				if(map[ni][nj] == 0)
				empty++;
			}
		}
		return empty;
	}
	
	public Seat solution(int no)
	{
		PriorityQueue<Seat> pq = new PriorityQueue<>();
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				if(map[i][j] == 0)
				{
					pq.offer(new Seat(i, j, findLike(i, j, no), findSeat(i, j, no)));
				}
			}
		}
		
		return pq.poll();
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		order = new int[N*N+1];
		list = new ArrayList[N*N+1];
		
		for(int i = 1; i < N*N+1; i++)
		{
			list[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N*N+1; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			order[i] = a;
			for(int j = 1; j < 5; j++)
			{
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
			}			
		}
		for(int i = 1; i < N*N+1; i++)
		{
			// System.out.println(order[i]);
			Seat now = T.solution(order[i]);
			map[now.x][now.y] = order[i];
		}
//		for(int i = 1; i <= N; i++)
//		{
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		int sum = 0;
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				int count = T.count(i, j);
				sum += Math.pow(10, count-1);
			}
		}
		System.out.println(sum);
		
		
		
	}
}