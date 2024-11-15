import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Person
{
	int x;
	int y;
	int cnt;
	public Person(int x, int y, int cnt)
	{
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

class Point
{
	int x;
	int y;
	int num;
	public Point(int x, int y, int num)
	{
		this.x = x;
		this.y = y;
		this.num = num;
	}
}


public class Main {
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static int[][] map;
	static Person person;
	static Queue<Point> q = new LinkedList<>();
	static int[][] fireMap;
	
	public void FireBFS()
	{
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			for(int d = 0; d < 4; d++)
			{
				int ni = tmp.x + di[d];
				int nj = tmp.y + dj[d];
				if(ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] != 1 && fireMap[ni][nj] > tmp.num+1)
				{
					fireMap[ni][nj] = tmp.num + 1;
					q.add(new Point(ni, nj , tmp.num+1));
				}
			}
		}
	}
	
	public int BFS()
	{
		Queue<Person> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new Person(person.x, person.y, person.cnt));
		while(!q.isEmpty())
		{
			Person tmp = q.poll();
			visited[tmp.x][tmp.y] = true;
			for(int d = 0; d < 4; d++)
			{
				int ni = tmp.x + di[d];
				int nj = tmp.y + dj[d];
				if(ni >= 0 && ni < N && nj >= 0 && nj < M )
				{
					if(map[ni][nj] != 1 && fireMap[ni][nj] > tmp.cnt+1 && visited[ni][nj] == false)
					{
						q.add(new Person(ni, nj, tmp.cnt + 1));
					}
				}
				else
				{
					return tmp.cnt;
				}
			}
		}
		return 10000001;
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int time = Integer.parseInt(st.nextToken());
		for(int count = 0; count < time; count++)
		{
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			fireMap = new int[N][M];
			
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < M; j++)
				{
					fireMap[i][j] = Integer.MAX_VALUE;
				}
			}
			
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				String  str = st.nextToken();
				for(int j = 0; j < M; j++)
				{
					if(str.charAt(j) == '#')
					{
						map[i][j] = 1;
					}
					else if(str.charAt(j) == '*')
					{
						map[i][j] = 2;
						fireMap[i][j] = 0;
						q.add(new Point(i, j, 0));
					}
					else if(str.charAt(j) == '.')
					{
						map[i][j] = 0;
					}
					else if(str.charAt(j) == '@')
					{
						person = new Person(i, j, 0);
					}
				}
			}
			
			T.FireBFS();
//			T.BFS();
//			for(int i = 0; i < N; i++)
//			{
//				System.out.println(Arrays.toString(fireMap[i]));
//			}
			int answer = T.BFS();
			if(answer > 1000000)
			{
				System.out.println("IMPOSSIBLE");
			}
			else 
			{
				System.out.println(answer+1);
			}
//			System.out.println(T.BFS()+1);
		}
		
		
	}
}
