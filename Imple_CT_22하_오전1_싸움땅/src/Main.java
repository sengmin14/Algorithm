import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


class Player
{
	int x;
	int y;
	int dir;
	int power;
	int gun;
	int point;
	public Player(int x, int y, int dir, int power, int gun, int point)
	{
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.power = power;
		this.gun = gun;
		this.point = point;
	}
}

public class Main {
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static int K;
	static ArrayList<Integer>[][] map;
	static int[][] playerMap;
	static Player[] player;
	
	
	public void remove(int no, Player player)
	{
		int x = player.x;
		int y = player.y;
		int dir = player.dir;
		for(int d = 0; d < 4; d++)
		{
			int tmpDir = (dir + d)%4;
			int ni = x + di[tmpDir];
			int nj = y + dj[tmpDir];
			if(ni >= 0 && ni < N && nj >= 0 && nj < N && playerMap[ni][nj] == 0)
			{
				player.x = ni;
				player.y = nj;
				player.dir = tmpDir;
				playerMap[player.x][player.y] = no;
				getGun(player);
				return;
			}
		}
	}
	
	
	public void fight(int enemy, int me)
	{
		int enemySum = player[enemy].power + player[enemy].gun;
		int meSum = player[me].power + player[me].gun;
		int win = 0;
		int lose = 0;
		
		if(enemySum > meSum)
		{
			win = enemy;
			lose = me;
		}
		else
		{
			if(enemySum < meSum)
			{
				win = me;
				lose = enemy;
			}
			else
			{
				if(player[enemy].power > player[me].power)
				{
					win = enemy;
					lose = me;
				}
				else
				{
					win = me;
					lose = enemy;
				}
			}
		}
		
//		System.out.println(lose);
		playerMap[player[win].x][player[win].y] = win;
		player[win].point += (player[win].power + player[win].gun) - (player[lose].power + player[lose].gun);
		dropGun(player[lose]);
		remove(lose, player[lose]);
		getGun(player[win]);
//		System.out.println(player[win].power + player[win].gun + " " + player[lose].power + player[lose].gun);
//		player[win].point += (player[win].power + player[win].gun) - (player[lose].power + player[lose].gun);
	}
	
	public void dropGun(Player player)
	{
		int x = player.x;
		int y = player.y;
		int gun = player.gun;
		if(gun > 0)
		{
			map[x][y].add(gun);
			player.gun = 0;
		}
		
	}
	
	public void getGun(Player player)
	{
		int x = player.x;
		int y = player.y;
		int gun = player.gun;
		Collections.sort(map[x][y], Collections.reverseOrder());
		int nowGun = map[x][y].get(0);
		if(gun < nowGun)
		{
			map[x][y].remove(0);
			map[x][y].add(gun);
			gun = nowGun;
		}
		player.gun = gun;
//		for(int i = map[x][y].size()-1; i >= 0; i--)
//		{
//			int nowGun = map[x][y].get(i);
//			if(gun < nowGun)
//			{
//				map[x][y].remove(i);
//				map[x][y].add(gun);
//				gun = nowGun;
//			}
//		}
		player.gun = gun;
	}
	
	public void move(int no, Player player)
	{
		int x = player.x;
		int y = player.y;
		int dir = player.dir;
		int power = player.power;
		int gun = player.gun;
		
		int ni = x + di[dir];
		int nj = y + dj[dir];
		if(ni >= 0 && ni < N && nj >= 0 && nj < N)
		{
			if(playerMap[ni][nj] != 0)
			{
				player.x = ni;
				player.y = nj;
				playerMap[x][y] = 0;
				fight(playerMap[ni][nj], no);
			}
			else
			{
				playerMap[x][y] = 0;
				playerMap[ni][nj] = no;
				player.x = ni;
				player.y = nj;
				getGun(player);
			}
//			getGun(player);
		}
		else
		{
			dir = (dir+2)%4;
			ni = x + di[dir];
			nj = y + dj[dir];
			if(ni >= 0 && ni < N && nj >= 0 && nj < N)
			{
				if(playerMap[ni][nj] != 0)
				{
					player.x = ni;
					player.y = nj;
					player.dir = dir;
					playerMap[x][y] = 0;
					fight(playerMap[ni][nj], no);
				}
				else
				{
					playerMap[x][y] = 0;
					playerMap[ni][nj] = no;
					player.dir = dir;
					player.x = ni;
					player.y = nj;
					getGun(player);
				}
			}
//			getGun(player);
		}
		
	}
	
	public void solve()
	{
		for(int i = 1; i <= M; i++)
		{
			move(i, player[i]);
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
		map = new ArrayList[N][N];
		playerMap = new int[N][N];
		player = new Player[M+1];
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
			{
				int gun = Integer.parseInt(st.nextToken());
				map[i][j].add(gun);
			}
		}
		
		for(int i = 1; i <= M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			playerMap[x][y] = i;
			player[i] = new Player(x, y, d, s, 0, 0);
		}
		
		for(int i = 0; i < K; i++)
		{
			T.solve();
			
//			System.out.println();
		}
		
		for(int j = 1; j <= M; j++)
		{
			System.out.print(player[j].point + " ");
		}
		
		
	}
}
