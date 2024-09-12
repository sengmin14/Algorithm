import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main
{
	static int N;
	static int M;
	static int R;
	
	static int[][] map;
	
	public void rotate(int j)
	{
//		for(int i = 0; i < R; i++)
//		{
//			for(int j = 0; j < Math.min(N, M)/2; j++)
//			{
				int temp = map[j][j];
				
				for(int k = j; k < M-j-1; k++)
				{
					map[j][k] = map[j][k+1];
				}
				
				for(int k = j; k < N-1-j; k++)
				{
					map[k][M-j-1] = map[k+1][M-j-1];
				}
				
				for(int k = M-1-j; k > j; k--)
				{
					map[N-j-1][k] = map[N-j-1][k-1];
				}
				
				for(int k = N-1-j; k > j; k--)
				{
					map[k][j] = map[k-1][j];
				}
				map[j+1][j] = temp;
//			}
//		}
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int nN = N;
		int mM = M;
		
		for(int i = 0; i < Math.min(N, M)/2; i++)
		{
			int cnt = (nN * 2) + (mM * 2) - 4;
			nN -= 2;
			mM -= 2;
			int rR = R % cnt;
			for(int k = 0; k < rR; k++)
			{
//				System.out.println(k);
				T.rotate(i);
			}
		}
		
//		for(int i = 0; i < R; i++)
//		{
//			T.rotate();
//		}
		
		
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
	}
}