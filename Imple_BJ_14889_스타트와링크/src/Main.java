import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static boolean[] visited;
	static int answer;
	
	public void calc()
	{
		int start = 0;
		int link = 0;
		
		for(int i = 0; i < N-1; i++)
		{
			for(int j = i+1; j < N; j++)
			{
				if(visited[i] == true && visited[j] == true)
				{
					start += map[i][j];
					start += map[j][i];
				}
				else if(visited[i] == false && visited[j] == false)
				{
					link += map[i][j];
					link += map[j][i];
				}
			}
		}
		
		answer = Integer.min(answer, Math.abs(start-link));
		
	}
	
	public void DFS(int depth, int at)
	{
		if(depth == N/2)
		{
			calc();
//			System.out.println(Arrays.toString(visited));
		}
		else
		{
			for(int i = at; i < N; i++)
			{
				visited[i] = true;
				DFS(depth+1, i+1);
				visited[i] = false;
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
		answer = Integer.MAX_VALUE;
		
		map = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		T.DFS(0, 0);
		System.out.println(answer);
	}
}
