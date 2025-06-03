import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>
{
	int end;
	int weight;
	public Point(int end, int weight)
	{
		this.end = end;
		this.weight = weight;
	}
	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}
}

class Main
{
	
	static int N;
	static int M;
	static ArrayList<Point>[] list;
	static int dist[];
	
	static int start;
	static int end;
	
	static boolean[] visited;
	
	public void dijk()
	{
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.add(new Point(start, 0));
		dist[start] = 0;
		while(!pq.isEmpty())
		{
			Point tmp = pq.poll();
			if(visited[tmp.end] == false)
			{
				visited[tmp.end] = true;
				for(Point now : list[tmp.end])
				{
					if(dist[now.end] > dist[tmp.end] + now.weight)
					{
						dist[now.end] = dist[tmp.end] + now.weight;
						pq.add(new Point(now.end, dist[now.end]));
					}
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
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		dist = new int[N+1];
		visited = new boolean[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i = 0; i < N+1; i++)
		{
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Point(b, c));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		T.dijk();
		
		System.out.println(dist[end]);
		
	}
}