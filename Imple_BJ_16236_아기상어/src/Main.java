import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark
{
	int len;
	int x;
	int y;
	int sum;
	int size;
	Shark(int len, int x, int y, int sum, int size)
	{
		this.len = len;
		this.x = x;
		this.y = y;
		this.sum = sum;
		this.size = size;
	}
}

class Fish implements Comparable<Fish>
{
	int len;
	int x;
	int y;
	public Fish(int len, int x, int y)
	{
		this.len = len;
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Fish o)
	{
		if(this.len == o.len)
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
			return this.len - o.len;
		}
	}
	
}


class Main
{
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int[][] map;
	static Queue<Shark> sharkInfo;
	static Queue<Shark> shark;
	static PriorityQueue<Fish> fish;
	static boolean[][] visited;
	
	public Fish BFS()
	{
		fish = new PriorityQueue<>();
		visited = new boolean[N][N];
		while(!shark.isEmpty())
		{
			Shark tmp = shark.poll();
			visited[tmp.x][tmp.y] = true;
			for(int i = 0; i < 4; i++)
			{
				int ni = tmp.x + di[i];
				int nj = tmp.y + dj[i];
				if(ni >= 0 && ni < N && nj >= 0 && nj < N && visited[ni][nj] == false)
				{
					if(map[ni][nj] == 0)
					{
						shark.offer(new Shark(tmp.len+1, ni, nj, tmp.sum, tmp.size));
						visited[ni][nj] = true;
					}
					else if(map[ni][nj] <= tmp.size)
					{
						shark.offer(new Shark(tmp.len+1, ni, nj, tmp.sum, tmp.size));
						visited[ni][nj] = true;
						if(map[ni][nj] < tmp.size)
						{
							fish.offer(new Fish(tmp.len+1, ni, nj));
						}
					}
				}
			}
		}
		return fish.poll();
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		sharkInfo = new LinkedList<>();
		shark = new LinkedList<>();
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9)
				{
					sharkInfo.add(new Shark(0, i, j, 0, 2));
					shark.add(new Shark(0, i, j, 0, 2));
				}
			}
		}
		
		
		int cnt = 0;
		while(true)
		{
			Fish tmp = T.BFS();
			
			if(tmp == null)
			{
				break;
			}
			Shark now = sharkInfo.poll();
			// System.out.println(tmp.x + " " + tmp.y);
			map[now.x][now.y] = 0;
			now.sum++;
			if(now.sum == now.size)
			{
				now.sum = 0;
				now.size++;
			}
			sharkInfo.add(new Shark(0, tmp.x, tmp.y, now.sum, now.size));
			shark.add(new Shark(0, tmp.x, tmp.y, now.sum, now.size));
			cnt+=tmp.len;
			// System.out.println(cnt);
		}
		System.out.println(cnt);
	}
}