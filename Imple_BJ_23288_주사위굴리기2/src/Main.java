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
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

class Dice
{
	int x;
	int y;
	int dir;
	public Dice(int x, int y, int dir)
	{
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

public class Main {
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static int[] diceNo = {0, 1, 2, 3, 4, 5, 6};
	
	static Dice dice;
	
	static int sum;
	
	public void bfs(int no)
	{
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(dice.x, dice.y));
//		System.out.println(dice.x + " " + dice.y);
//		if(dice.x == 2 && dice.y == 3)
//		{
//			System.out.println("asd");
//		}
		boolean[][] isVisited = new boolean[N][M];
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			isVisited[tmp.x][tmp.y] = true;
			sum += map[tmp.x][tmp.y];
			for(int i = 0; i < 4; i++)
			{
				int ni = tmp.x + di[i];
				int nj = tmp.y + dj[i];
				if(ni >= 0 && ni < N && nj >= 0 && nj < M)
				{
					if(isVisited[ni][nj] == false && map[ni][nj] == no)
					{
						isVisited[ni][nj] = true;
						q.add(new Point(ni, nj));
					}
				}
			}
		}
	}
	
	public void up()
	{
		int tmp = diceNo[2];
		diceNo[2] = diceNo[1];
		diceNo[1] = diceNo[5];
		diceNo[5] = diceNo[6];
		diceNo[6] = tmp;
		
	}
	public void down()
	{
		int tmp = diceNo[6];
		diceNo[6] = diceNo[5];
		diceNo[5] = diceNo[1];
		diceNo[1] = diceNo[2];
		diceNo[2] = tmp;
	}
	public void right()
	{
		int tmp = diceNo[6];
		diceNo[6] = diceNo[3];
		diceNo[3] = diceNo[1];
		diceNo[1] = diceNo[4];
		diceNo[4] = tmp;
		
	}
	public void left()
	{
		int tmp = diceNo[4];
		diceNo[4] = diceNo[1];
		diceNo[1] = diceNo[3];
		diceNo[3] = diceNo[6];
		diceNo[6] = tmp;
	}
	
	public void compare()
	{
		int x = dice.x;
		int y = dice.y;
		if(map[x][y] < diceNo[6])
		{
			dice.dir = (dice.dir + 1)%4;
		}
		else if(map[x][y] > diceNo[6])
		{
			dice.dir = (dice.dir + 3)%4;
		}
		else
		{
			
		}
	}
	
	public void move()
	{
		int dir = dice.dir;
		dice.x = dice.x + di[dir];
		dice.y = dice.y + dj[dir];
		if(dice.dir == 0)
		{
			up();
		}
		else if(dice.dir == 1)
		{
			right();
		}
		else if(dice.dir == 2)
		{
			down();
		}
		else if(dice.dir == 3)
		{
			left();
		}
		compare();
	}
	
	public void check()
	{
		int dir = dice.dir;
		int ni = dice.x + di[dir];
		int nj = dice.y + dj[dir];
		if(ni >= 0 && ni < N && nj >= 0 && nj < M)
		{
			move();
		}
		else
		{
			dice.dir = (dir+2)%4;
			move();
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
		
		dice = new Dice(0, 0, 1);
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < K; i++)
		{
			T.check();
			T.bfs(map[dice.x][dice.y]);
//			System.out.println(dice.x + " " + dice.y);
		}
		System.out.println(sum);
		
	}
}
