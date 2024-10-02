import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Point
{
	int x;
	int y;
	int weapon;
	int count;
	public Point(int x, int y, int weapon, int count)
	{
		this.x = x;
		this.y = y;
		this.weapon = weapon;
		this.count = count;
	}
}

public class Main {
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static boolean[][][] visited;
	
	public int BFS()
	{
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			for(int i = 0; i < 4; i++)
			{
				int ni = tmp.x + di[i];
				int nj = tmp.y + dj[i];
				if(ni == N-1 && nj == M-1)
				{
//					System.out.println("리턴찍힘");
//					System.out.println("리턴 찍히는 곳" + tmp.count);
					return tmp.count;
				}
				if(ni >= 0 && ni < N && nj >= 0 && nj < M && visited[tmp.weapon][ni][nj] == false)
				{
					if(tmp.weapon == 0 || map[ni][nj] == 2)	
					{
						if(map[ni][nj] == 0)
						{
							visited[0][ni][nj] = true;
							q.add(new Point(ni, nj, 0, tmp.count+1));
						}
						else if(map[ni][nj] == 2)
						{
							visited[0][ni][nj] = true;
							visited[1][ni][nj] = true;
							q.add(new Point(ni, nj, 1, tmp.count+1));
						}
					}
					else if(tmp.weapon == 1)
					{
						visited[1][ni][nj] = true;
						q.add(new Point(ni, nj, 1, tmp.count+1));
					}
				}
			}
		}
		return 10000;
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
		map = new int[N][M];
		visited = new boolean[2][N][M];
		
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
//		System.out.println(T.BFS());
		
		int answer = T.BFS()+1;
		
		if(answer > K)
		{
			System.out.println("Fail");
		}
		else
		{
			System.out.println(answer);
		}
//		
		T.BFS();
//		
//		if(T.BFS() > K)
//		{
//			System.out.println("Fail");
//		}
//		else
//		{
//			System.out.println(T.BFS());
//		}
//		System.out.println(T.BFS());
	}
}
