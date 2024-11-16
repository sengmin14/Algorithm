import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Robot
{
	int no;
	int x;
	int y;
	boolean isAlive;
	public Robot(int no, int x, int y, boolean isAlive)
	{
		this.no = no;
		this.x = x;
		this.y = y;
		this.isAlive = isAlive;
	}
}

class Junsu
{
	int x;
	int y;
	public Junsu(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int[] di = {1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dj = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
	
	static int N;
	static int M;
	static int[][] map;
	static ArrayList[][] robotMap;
	static ArrayList<Robot> robot;
	static int robotCnt = 1;
	static Junsu junsu;
	
	public boolean RobotMove()
	{
		robotMap = new ArrayList[N][M];
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				robotMap[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < robot.size(); i++)
		{
			if(robot.get(i).isAlive == false)
			{
				continue;
			}
			int len = Integer.MAX_VALUE;
			int dir = 0;
			for(int d = 0; d < 9; d++)
			{
				int ni = robot.get(i).x + di[d];
				int nj = robot.get(i).y + dj[d];
				if(ni >= 0 && ni < N && nj >= 0 && nj < M)
				{
					int distance = Math.abs(junsu.x - ni) + Math.abs(junsu.y - nj);
					if(distance < len)
					{
						len = distance;
						dir = d;
					}
				}
			}
			int moveX = robot.get(i).x + di[dir];
			int moveY = robot.get(i).y + dj[dir];
			robotMap[moveX][moveY].add(i+1);
			robot.get(i).x = moveX;
			robot.get(i).y = moveY;
			
			if(map[moveX][moveY] == 1)
			{
				return false;
			}
		}
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				if(robotMap[i][j].size() > 1)
				{
					for(int k = robotMap[i][j].size()-1; k >= 0; k--)
					{
						int no = (int) robotMap[i][j].get(k);
//						System.out.println(no);
						robot.get(no-1).isAlive = false;
					}
				}
			}
		}
		
		
		return true;
	}
	
	public void junSuMove(int d)
	{
		int ni = junsu.x + di[d];
		int nj = junsu.y + dj[d];
		map[junsu.x][junsu.y] = 0;
		map[ni][nj] = 1;
		junsu.x = ni;
		junsu.y = nj;
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
		robotMap = new ArrayList[N][M];
		robot = new ArrayList<>();
		int robotNo = 1;
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				robotMap[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for(int j = 0; j < M; j++)
			{
				if(str.charAt(j) == '.')
				{
					map[i][j] = 0;
				}
				else if(str.charAt(j) == 'I')
				{
					junsu = new Junsu(i, j);
					map[i][j] = 1;
				}
				else
				{
					robot.add(new Robot(robotNo, i, j, true));
					robotNo++;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		boolean flag = true;
		for(int i = 0; i < str.length(); i++)
		{
			T.junSuMove(str.charAt(i) - '0' -1);
			if(T.RobotMove() == false)
			{
				System.out.println("kraj" + " " + (i+1));
				flag = false;
				break;
			}
		}
		
		
		if(flag == true)
		{
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < M; j++)
				{
					if(robotMap[i][j].size() == 1)
					{
						System.out.print("R");
					}
					else
					{
						if(map[i][j] == 1)
						{
							System.out.print("I");
						}
						else
						{
							System.out.print(".");
						}
					}
				}
				System.out.println();
			}
		}
		
		
	}
}
