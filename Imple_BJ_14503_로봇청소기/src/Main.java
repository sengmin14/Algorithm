import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Robot
{
	int x;
	int y;
	int dir;
	public Robot(int x, int y, int dir)
	{
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

public class Main
{
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static Robot robot;
	
	static int[][] map;
	
	static int answer = 0;
	
	public boolean move()
	{
		if(map[robot.x][robot.y] == 0)
		{
			map[robot.x][robot.y] = 2;
			answer++;
		}
		
		for(int i = 3; i >= 0; i--)
		{
			int dir = (robot.dir + i)%4;
			int ni = robot.x + di[dir];
			int nj = robot.y + dj[dir];
			if(map[ni][nj] == 0)
			{
				robot.dir = dir;
				robot.x = ni;
				robot.y = nj;
				return true;
			}
		}
		int dir = (robot.dir + 2)%4;
		int ni = robot.x + di[dir];
		int nj = robot.y + dj[dir];
		if(map[ni][nj] != 1)
		{
//			robot.dir = dir;
			robot.x = ni;
			robot.y = nj;
			return true;
		}
		
		
		return false;
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		robot = new Robot(x, y, dir);
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true)
		{
			boolean check = T.move();
			if(!check)
			{
				break;
			}
		}
		
		System.out.println(answer);
		
	}
}
