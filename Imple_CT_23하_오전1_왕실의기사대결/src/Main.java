import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


class Knight
{
	int r;
	int c;
	int h;
	int w;
	int k;
	boolean isAlive;
	boolean isDamaged;
	
	public Knight(int r, int c, int h, int w, int k, boolean isAlive, boolean isDamanged)
	{
		this.r = r;
		this.c = c;
		this.h = h;
		this.w = w;
		this.k = k;
		this.isAlive = isAlive;
		this.isDamaged = isDamanged;
	}
	
}

public class Main
{
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int L; // 격자판 가로/세로 크기
	static int[][] map; // 격자판 2차원 배열
	static int[][] knightBoard; // 2차원 보트판 위에 나이트 정보
	
	static int N; // 기사의 수
	
	static int Q; // 명령의 수
	
	static Knight[] knights;
	
	static int[] sum;
	static int answer;
	
	public void remove(int i)
	{
		for(int r = knights[i].r + knights[i].h-1; r >= knights[i].r; r--)
		{
			for(int c = knights[i].c; c < knights[i].c + knights[i].w; c++)
			{
				knightBoard[r][c] = 0;
			}
		}
	}

	public void getDanaged()
	{
		for(int i = 0; i < L; i++)
		{
			for(int j = 0; j < L; j++)
			{
				if(knightBoard[i][j] != 0 && knights[knightBoard[i][j]].isDamaged == true)
				{
					if(map[i][j] == 1)
					{
						sum[knightBoard[i][j]] += 1;
						knights[knightBoard[i][j]].k -= 1;
						if(knights[knightBoard[i][j]].k <= 0)
						{
							knights[knightBoard[i][j]].isAlive = false;
							remove(knightBoard[i][j]);
						}
					}
					
				}
			}
		}
	}
	
	public void moveUp(int i, int d)
	{
		for(int r = knights[i].r; r < knights[i].r + knights[i].h; r++)
		{
			for(int c = knights[i].c; c < knights[i].c + knights[i].w; c++)
			{
				int ni = r + di[d];
				int nj = c + dj[d];
				knightBoard[ni][nj] = i;
				knightBoard[r][c] = 0;
			}
		}
		knights[i].r = knights[i].r + di[d];
		knights[i].c = knights[i].c + dj[d];
	}
	public void moveDown(int i, int d)
	{
		for(int r = knights[i].r + knights[i].h-1; r >= knights[i].r; r--)
		{
			for(int c = knights[i].c; c < knights[i].c + knights[i].w; c++)
			{
				int ni = r + di[d];
				int nj = c + dj[d];
				knightBoard[ni][nj] = i;
				knightBoard[r][c] = 0;
			}
		}
		knights[i].r = knights[i].r + di[d];
		knights[i].c = knights[i].c + dj[d];
		
	}
	public void moveLeft(int i, int d)
	{
		for(int r = knights[i].r; r < knights[i].r + knights[i].h; r++)
		{
			for(int c = knights[i].c; c < knights[i].c + knights[i].w; c++)
			{
				int ni = r + di[d];
				int nj = c + dj[d];
				knightBoard[ni][nj] = i;
				knightBoard[r][c] = 0;
			}
		}
		knights[i].r = knights[i].r + di[d];
		knights[i].c = knights[i].c + dj[d];
	}
	public void moveRight(int i, int d)
	{
		for(int r = knights[i].r; r < knights[i].r + knights[i].h; r++)
		{
			for(int c = knights[i].c + knights[i].w-1; c >= knights[i].c; c--)
			{
				int ni = r + di[d];
				int nj = c + dj[d];
				knightBoard[ni][nj] = i;
				knightBoard[r][c] = 0;
			}
		}
		knights[i].r = knights[i].r + di[d];
		knights[i].c = knights[i].c + dj[d];
	}
	
	
	
	public void move(int i, int d)
	{
		for(int r = knights[i].r; r < knights[i].r + knights[i].h; r++)
		{
			for(int c = knights[i].c; c < knights[i].c + knights[i].w; c++)
			{
				int ni = r + di[d];
				int nj = c + dj[d];
				
				if(knightBoard[ni][nj] == 0 || knightBoard[ni][nj] == i)
				{
					continue;
				}
				if(knightBoard[ni][nj] != i && knightBoard[ni][nj] != 0)
				{
					move(knightBoard[ni][nj], d);
				}
			}
		}
		knights[i].isDamaged = true;
		if(d == 0)
		{
			moveUp(i, d);
		}
		else if(d == 1)
		{
			moveRight(i, d);
		}
		else if(d == 2)
		{
			moveDown(i, d);
		}
		else if(d == 3)
		{
			moveLeft(i, d);
		}
		
	}
	
	public boolean moveable(int i, int d)
	{
		for(int r = knights[i].r; r < knights[i].r + knights[i].h; r++)
		{
			for(int c = knights[i].c; c < knights[i].c + knights[i].w; c++)
			{
				int ni = r + di[d];
				int nj = c + dj[d];
				if(ni >= 0 && ni < L && nj >= 0 && nj < L)
				{
					if(map[ni][nj] == 2)
					{
						return false;
					}
					if(knightBoard[ni][nj] == 0 || knightBoard[ni][nj] == i)
					{
						continue;
					}
					if(knightBoard[ni][nj] != i)
					{
						if(!moveable(knightBoard[ni][nj], d))
						{
							return false;
						}
					}
				}
				else
				{
					return false;
				}
			}
		}
		return true;
	}

	public void process(int i, int d)
	{
		for(int dmg = 1; dmg < knights.length; dmg++)
		{
			knights[dmg].isDamaged = false;
		}
		if(moveable(i, d))
		{
			move(i, d);
			knights[i].isDamaged = false;
			getDanaged();
		}
		else
		{
			return;
		}
	}
	
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		map = new int[L][L];
		knightBoard = new int[L][L];
		
		for(int i = 0; i < L; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < L; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		knights = new Knight[N+1];
		sum = new int[N+1];
		
		for(int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			knights[i] = new Knight(r, c, h, w, k, true, false);
			for(int x = r; x < r+h; x++)
			{
				for(int y = c; y < c+w; y++)
				{
					knightBoard[x][y] = i;
				}
			}
		}
		
		for(int q = 0; q < Q; q++)
		{
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(!knights[i].isAlive)
			{
				continue;
			}
			T.process(i, d);
		}
		
//		for(int i = 0; i < L; i++)
//		{
//			System.out.println(Arrays.toString(knightBoard[i]));
//		}
		
		for(int i = 1; i < knights.length; i++)
		{
			if(knights[i].isAlive == true)
			{
				answer += sum[i];
//				System.out.println(sum[i]);
			}
		}
		System.out.println(answer);

	}
}