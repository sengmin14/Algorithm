import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main
{
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static int[][] sticker;
	static int stickX;
	static int stickY;
	
	static int answer;
	
	public void rotate(int d)
	{
		int tempStickX = stickY;
		int tempStickY = stickX;
		stickX = tempStickX;
		stickY = tempStickY;
		int[][] tempStick = new int[stickX][stickY];
		for(int i = 0; i < stickX; i++)
		{
			for(int j = 0; j < stickY; j++)
			{
				tempStick[i][j] = sticker[stickY - 1 - j][i];
			}
		}
		
		sticker = new int[stickX][stickY];
		for(int i = 0; i < stickX; i++)
		{
			for(int j = 0; j < stickY; j++)
			{
				sticker[i][j] = tempStick[i][j];
			}
		}
		
		for(int i = 0; i < stickX; i++)
		{
//			System.out.println(Arrays.toString(sticker[i]));
		}
	}
	
	public boolean canAttach(int i, int j)
	{
		int maxI = i + stickX-1;
		int maxJ = j + stickY-1;
		if(maxI >= 0 && maxI < N && maxJ >= 0 && maxJ < M)
		{
			for(int x = i; x <= maxI; x++)
			{
				for(int y = j; y <= maxJ; y++)
				{
					if(map[x][y] == 1 && sticker[x-i][y-j] == 1)
					{
						return false;
					}
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void attach(int i, int j)
	{	
		int maxI = i + stickX-1;
		int maxJ = j + stickY-1;

		for(int x = i; x <= maxI; x++)
		{
			for(int y = j; y <= maxJ; y++)
			{
				if(sticker[x-i][y-j] == 1)
				{
					map[x][y] = 1;
				}
			}
		}	
		
	}
	
	public void start()
	{
		for(int d = 0; d < 4; d++)
		{
			if(d > 0)
			{
				rotate(d);
			}
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < M; j++)
				{
					if(canAttach(i, j))
					{
						attach(i, j);
						return;
					}
					else
					{
						
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
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < K; i++)
		{
			st = new StringTokenizer(br.readLine());
			stickX = Integer.parseInt(st.nextToken());
			stickY = Integer.parseInt(st.nextToken());
			sticker = new int[stickX][stickY];
			for(int x = 0; x < stickX; x++)
			{
				st = new StringTokenizer(br.readLine());
				for(int y = 0; y < stickY; y++)
				{
					sticker[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			
			T.start();
		}
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++) 
			{
				if(map[i][j] == 1)
				{
					answer++;
				}
			}
//			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println(answer);
	}
}