import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point
{
	int x;
	int y;
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

class Main
{
	static int[] count = {1, 1, 2, 2};
	static int[] di = {0, -1, 0, 1};
	static int[] dj = {-1, 0, 1, 0};
	
	static int N;
	static int[][] map;
	static Point point;
	
	static int check = 1;
	
	public void tonado()
	{
		boolean flag = true;
		while(flag)
		{
			for(int d = 0; d < 4; d++)
			{
				for(int k = 1; k <= count[d]; k++)
				{
					int ni = point.x + di[d];
					int nj = point.y + dj[d];
					if(ni >= 0 && ni < N && nj >= 0 && nj < N)
					{	
						point.x = ni;
						point.y = nj;
						check++;
						map[point.x][point.y] = check;
					}
					else
					{
						flag = false;
						return;
					}
				}
			}
			
			for(int i = 0; i < 4; i++)
			{
				count[i]+=2;
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
		map = new int[N][N];
		point = new Point(N/2, N/2);
		
		T.tonado();
		
		for(int i = 0; i < N; i++)
		{
			System.out.println(Arrays.toString(map[i]));
		}
	}
}