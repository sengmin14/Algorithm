import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>
{
	int x;
	int y;
	public Point() {}
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Point o) {
		if(this.y != o.y)
		{
			return this.y - o.y;
		}
		else
		{
			return o.x - this.x;
		}
	}
}

public class Main {
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int K;
	static int M;
	static int list;
	static int[] arr;
	
	static int[][] map;
	static int[][] copyMap;
	static boolean[][] visited;
	static boolean[][] removeVisited;
	
	static PriorityQueue<Point> pq;
	
	public void pqInput()
	{
		System.out.println(pq.size());
		while(!pq.isEmpty())
		{
			Point tmp = pq.poll();
			copyMap[tmp.x][tmp.y] = arr[list];
			list++;
		}
	}
	
	public void reomveBFS(int x, int y, int num)
	{
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		pq.add(new Point(x, y));
		copyMap[x][y] = 0;
		removeVisited[x][y] = true;
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			for(int d = 0; d < 4; d++)
			{
				int ni = tmp.x + di[d];
				int nj = tmp.y + dj[d];				
				if(ni >= 0 && ni < 5 && nj >= 0 && nj < 5 && removeVisited[ni][nj] == false)
				{
					if(copyMap[ni][nj] == num)
					{
						q.add(new Point(ni, nj));
						copyMap[ni][nj] = 0;
						pq.add(new Point(ni, nj));
						removeVisited[ni][nj] = true;
					}
				}
			}
		}
	}
	
	public int BFS(int x, int y, int num)
	{
		int cnt = 1;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			for(int d = 0; d < 4; d++)
			{
				int ni = tmp.x + di[d];
				int nj = tmp.y + dj[d];				
				if(ni >= 0 && ni < 5 && nj >= 0 && nj < 5 && visited[ni][nj] == false)
				{
					if(map[ni][nj] == num)
					{
						q.add(new Point(ni, nj));
						visited[ni][nj] = true;
						cnt++;
					}
				}
			}
		}
		return cnt;
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[5][5];
		copyMap = new int[5][5];
		arr = new int[M];
		
		for(int i = 0; i < 5; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
				copyMap[i][j] = map[i][j];
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		visited = new boolean[5][5];
		removeVisited = new boolean[5][5];
		pq = new PriorityQueue<>();
		for(int i = 1; i < 4; i++)
		{
			for(int j = 1; j < 4; j++)
			{
				int count = T.BFS(i, j, map[i][j]);
				if(count >= 3)
				{
					T.reomveBFS(i, j, map[i][j]);
				}
			}
		}
		
		T.pqInput();
		
		for(int i = 0; i < 5; i++)
		{
			System.out.println(Arrays.toString(copyMap[i]));
		}
	}
}
