import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main
{
	static int N;
	static int M;
	static int[][] map;
	static boolean[] visited;
	
	static int answer;
	
	public void BFS()
	{
		Stack<Integer> q = new Stack<>();
		q.add(0);
		visited[0] = true;
		
		while(!q.isEmpty())
		{
			int tmp = q.pop();
			for(int i = 0; i < N; i++)
			{
				if(map[tmp][i]== 1)
				{
					if(visited[i] == false)
					{
						answer++;
						q.add(i);
						visited[i] = true;
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
		map = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			map[a][b] = 1;
			map[b][a] = 1;
		}
		T.BFS();
		System.out.println(answer);
		
	}
}