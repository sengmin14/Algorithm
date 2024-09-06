import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Shark
{
	int r;
	int c;
	int s;
	int d;
	int z;
	boolean alive;
	public Shark(int r, int c, int s, int d, int z, boolean alive)
	{
		this.r = r;
		this.c = c;
		this.s = s; // 속력 
		this.d = d; // 이동 방향
		this.z = z; // 크기
		this.alive = alive;
	}
}


class Main
{
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	static int R;
	static int C;
	static int M;
	static int[][] map;
	static ArrayList<Shark> shark;
	
	static int sum;
	
	public void getShark(int c)
	{
		for(int i = 0; i < R; i++)
		{
			if(map[i][c] != 0)
			{
				sum += shark.get(map[i][c]-1).z;
				shark.get(map[i][c]-1).alive = false;
				return;
			}
		}
	}
	
	public void moveShark()
	{
		map = new int[R][C];
		for(int i = 1; i <= M; i++)
		{
			Shark tmp = shark.get(i-1);
			if(tmp.alive)
			{
				int Rspeed = Math.abs(tmp.s % ((R-1)*2));
				int Cspeed = Math.abs(tmp.s % ((C-1)*2));
				
				int ni = tmp.r;
				int nj = tmp.c;
			
				int Rcnt = Math.abs(di[tmp.d] * Rspeed);
				int Ccnt = Math.abs(dj[tmp.d] * Cspeed);
				
				
				for(int r = 0; r < Rcnt; r++)
				{
					if(ni + di[tmp.d] < 0 || ni + di[tmp.d] >= R)
					{
						if(tmp.d == 0)
						{
							tmp.d = 1;
						}
						else
						{
							tmp.d = 0;
						}
					}
					
					ni += di[tmp.d];
					
					if(ni == 0 || ni == R-1)
					{
						if(tmp.d == 0)
						{
							tmp.d = 1;
						}
						else
						{
							tmp.d = 0;
						}
					}
				}
				for(int c = 0; c < Ccnt; c++)
				{
					if(nj + dj[tmp.d] < 0 || nj + dj[tmp.d] >= C)
					{
						if(tmp.d == 2)
						{
							tmp.d = 3;
						}
						else
						{
							tmp.d = 2;
						}
					}
					
					nj += dj[tmp.d];
					
					if(nj == 0 || nj == C-1)
					{
						if(tmp.d == 2)
						{
							tmp.d = 3;
						}
						else
						{
							tmp.d = 2;
						}
					}
				}
				shark.get(i-1).r = ni;
				shark.get(i-1).c = nj;
				shark.get(i-1).d = tmp.d;
				if(map[ni][nj] == 0)
				{
					map[ni][nj] = i;
				}
				else
				{
					if(shark.get(i-1).z > shark.get(map[ni][nj]-1).z)
					{
						shark.get(map[ni][nj]-1).alive = false;
						map[ni][nj] = i;
					}
					else
					{
						shark.get(i-1).alive = false;
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
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		shark = new ArrayList<>();
		
		for(int i = 1; i <= M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			shark.add(new Shark(r, c, s, d, z, true));
			map[r][c] = i;
		}
		
		for(int i = 0; i < C; i++)
		{
			T.getShark(i);
			T.moveShark();
		}
		System.out.println(sum);
		
	}
}