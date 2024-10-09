import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Person
{
	int x;
	int y;
	int moveCnt;
	boolean alive;
	public Person(int x, int y, int moveCnt, boolean alive)
	{
		this.x = x;
		this.y = y;
		this.moveCnt = moveCnt;
		this.alive = alive;
	}
	
}

class Exit
{
	int x;
	int y;
	public Exit(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
}


public class Main {
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static Person[] person;
	static ArrayList[][] personMap;
	static Exit exit;
	
	static int moveCnt;
	
	public int abs(int x, int y)
	{
		return Math.abs(exit.x - x) + Math.abs(exit.y - y);
	}
	
	public void move(int i)
	{
		if(person[i].alive == true)
		{
			int nowLength = abs(person[i].x, person[i].y);
			for(int d = 0; d < 4; d++)
			{
				int ni = person[i].x + di[d];
				int nj = person[i].y + dj[d];
				if(ni >= 0 && ni < N && nj >= 0 && nj < N)
				{
					if(map[ni][nj] == 0)
					{
						int length = abs(ni, nj);
						if(length < nowLength)
						{
							if(ni == exit.x && nj == exit.y)
							{
								person[i].alive = false;
							}
							person[i].x = ni;
							person[i].y = nj;
							moveCnt++;
							return;
						}
					}
				}
			}
		}
	}
	
	public void rotate(int fx, int fy, int len)
	{
        for(int i = fx; i < fx+len; i++)
        {
        	
        }
	}
	
	public void spin(int len)
	{
		boolean stop = false;
        int fx = -1;
        int fy = -1;
		
		 // 모든 경우의 수 for문으로 브루트 포스 (10^2 * 10^4 + @ )
        for(int i = 0; i < N; i++){
            if(i + len >= N) continue;
            for(int j = 0; j < N; j++){
                if(j + len >=N) continue;
                boolean judge1 = false;
                boolean judge2 = false;
                for(int i2 = i; i2 <= i + len; i2++){
                    for(int j2 = j; j2 <= j + len; j2++){
                        if(i2 == exit.x && j2 == exit.y) judge1 = true;
                        else if(personMap[i2][j2].size() > 0) judge2 = true;
                        
                    }
                }

                if(judge1 && judge2){
                    fx = i;
                    fy = j;
                    stop = true;
                }

                if(stop) break;
            }
            if(stop) break;
        }
        
        rotate(fx, fy, len);
	}
	
	public void findLength()
	{
		int tmp = Integer.MAX_VALUE;
		for(int i = 1; i <= M; i++)
		{
			if(person[i].alive == false)
			{
				continue;
			}
			int nowTmp = Math.abs(exit.x - person[i].x) + Math.abs(exit.y - person[i].y);
			if(tmp > nowTmp)
			{
				tmp = nowTmp;
			}
		}
//		System.out.println(tmp);
		spin(tmp);
	}
	
	
	public void start()
	{
		for(int i = 1; i <= M; i++)
		{
			move(i);
		}
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				personMap[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 1; i <= M; i++)
		{
			if(person[i].alive == true)
			{
				personMap[person[i].x][person[i].y].add(i);
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
		map = new int[N][N];
		person = new Person[M+1];
		personMap = new ArrayList[N][N];
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				personMap[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			person[i] = new Person(x, y, 0, true);
		}
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken())-1;
		int y = Integer.parseInt(st.nextToken())-1;
		exit = new Exit(x, y);
		
		T.start();
		T.findLength();
//		System.out.println(moveCnt);
	}
}
