import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

class Main
{
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int[][] map;
	static int[][] copyMap;
	static int[][] group;
	static boolean[][] visited;
	static boolean[][] lineVisited;
	static int[][] line;
	
	static int sum;
	
	public static void rotate()
	{ //반시계방향
		int[][] copyMap = new int[N][N];
		
    	for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                // Case 2 - 1. 세로줄에 대해서는 (i, j) -> (j, i)가 됩니다.
                if(j == N / 2)
                	copyMap[j][i] = map[i][j];
                // Case 2 - 2. 가로줄에 대해서는 (i, j) -> (n - j - 1, i)가 됩니다.
                else if(i == N / 2)
                	copyMap[N - j - 1][i] = map[i][j];
            }
    	for(int i = 0; i < N; i++)
		   {
			   for(int j = 0; j < N; j++)
			   {
				   if(copyMap[i][j] != 0)
				   {
					   map[i][j] = copyMap[i][j];
				   }
			   }
		   }
    	rotate1();
    	
    }
	
	public static void rotate1()
	{
		int[][] copyMap = new int[N][N];
		   for (int i = 0; i < N / 2; i++)
			   for (int j = 0; j < N / 2; j++)
				   copyMap[i][j] = map[N / 2 - 1 - j][i];
		 
		   //2섹션
		    for (int i = 0; i < N / 2; i++)
		    	for (int j = N / 2 + 1; j < N; j++)
		    		copyMap[i][j] = map[N - 1 - j][N / 2 + 1 + i];
		    //3섹션
		   for (int i = 0; i < N / 2; i++)
		    	for (int j = N / 2 + 1; j < N; j++)
		    		copyMap[N/ 2 + 1 + i][N / 2 * 2 - j] = map[j][i];
		    
		    //4섹션
		   for (int i = N / 2 + 1; i < N; i++)
		    	for (int j = 0; j < N / 2; j++)
		    		copyMap[i][j + N / 2 + 1] = map[N - 1 - j][i];
		
		   for(int i = 0; i < N; i++)
		   {
			   for(int j = 0; j < N; j++)
			   {
				   if(copyMap[i][j] != 0)
				   {
					   map[i][j] = copyMap[i][j];
				   }
			   }
		   }
	}
	
	public int groupCount(int groupNo)
	{
		int answer = 0;
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(group[i][j] == groupNo)
				{
					answer++;
				}
			}
		}
		return answer;
	}
	
	public int realNo(int groupNo)
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(group[i][j] == groupNo)
				{
					return map[i][j];
				}
			}
		}
		return 0;
	}
	
	public void calc(int groupNo)
	{
		for(int i = 1; i < groupNo; i++)
		{
			for(int j = i+1; j <= groupNo; j++)
			{
				if(line[i][j] != 0)
				{
//					System.out.println((groupCount(i) + groupCount(j)) * realNo(i) * realNo(j) * line[i][j]);
					sum += (groupCount(i) + groupCount(j)) * realNo(i) * realNo(j) * line[i][j];
				}
			}
		}
	}
	
	public void lineCount(int x, int y, int groupNo)
	{
		visited = new boolean[N][N];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		lineVisited[x][y] = true;
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			for(int d = 0; d < 4; d++)
			{
				int ni = tmp.x + di[d];
				int nj = tmp.y + dj[d];
				if(ni >= 0 && ni < N && nj >= 0 && nj < N && visited[ni][nj] == false)
				{
					if(group[ni][nj] == groupNo)
					{
						q.add(new Point(ni, nj));
						visited[ni][nj] = true;
						lineVisited[ni][nj] = true;
					}
					else
					{
						if(group[ni][nj] > groupNo)
						{
							line[groupNo][group[ni][nj]]++;
						}
					}
				}
			}
		}
	}
	
	public void BFS(int x, int y, int no, int groupNo)
	{
		visited = new boolean[N][N];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		group[x][y] = groupNo;
		visited[x][y] = true;
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			for(int d = 0; d < 4; d++)
			{
				int ni = tmp.x + di[d];
				int nj = tmp.y + dj[d];
				if(ni >= 0 && ni < N && nj >= 0 && nj < N && visited[ni][nj] == false)
				{
					if(map[tmp.x][tmp.y] == map[ni][nj])
					{
						group[ni][nj] = groupNo;
						q.add(new Point(ni, nj));
						visited[ni][nj] = true;
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
		map = new int[N][N];
//		group = new int[N][N];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int count = 1; count <= 4; count++)
		{
			
			
			// 그룹
			int groupNo = 0;
			group = new int[N][N];
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < N; j++)
				{
					if(group[i][j] == 0)
					{
						groupNo++;
						T.BFS(i, j, map[i][j], groupNo);
					}		
				}
			}
			// 라인 갯수
			line = new int[groupNo+1][groupNo+1];
			lineVisited = new boolean[N][N];
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < N; j++)
				{
					if(lineVisited[i][j] == false)
					{
						T.lineCount(i, j, group[i][j]);
					}
				}
			}
			
			// 예술 점수 계산
			T.calc(groupNo);
			
//			
			// 회전
			T.rotate();
			
//			for(int i = 0; i < N; i++)
//			{
//				System.out.println(Arrays.toString(map[i]));
//			}
				
			
//			System.out.println(sum);
		}
		
		
		
		System.out.println(sum);
		
	}
}

