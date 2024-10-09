import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int[] arr;
	static boolean[] visited;
	
	public void DFS(int depth)
	{
		if(depth == M)
		{
			System.out.println(Arrays.toString(arr));
		}
		else
		{
			for(int i = 0; i < N; i++)
			{
				if(visited[i] == false)
				{
					arr[depth] = i+1;
					visited[i] = true;
					DFS(depth+1);
					visited[i] = false;
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
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		visited = new boolean[N];
		
		T.DFS(0);
	}
}
