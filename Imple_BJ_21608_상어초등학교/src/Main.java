import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Student implements Comparable<Student>
{
	int x;
	int y;
	int like;
	int empty;
	public Student(int x, int y, int like, int empty)
	{
		this.x = x;
		this.y = y;
		this.like = like;
		this.empty = empty;
	}
	@Override
	public int compareTo(Student o) {
		
		if(this.like == o.like)
		{
			if(this.empty == o.empty)
			{
				if(this.x == o.x)
				{
					return this.y - o.y;
				}
				else
				{
					return this.x - o.x;
				}
			}
			else
			{
				return o.empty - this.empty;
			}
		}
		else
		{
			return o.like - this.like;
		}
		
	}
	
	
}

class Main
{
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int[][] map;
	static ArrayList<Integer>[] list;
	
	static int[] sList;
	
	static int answer;
	
	public int likeNo(int s, int x, int y)
	{	
		int sum = 0;
		for(int k = 0; k < 4; k++)
		{
			int ni = x + di[k];
			int nj = y + dj[k];
			if(ni >= 0 && ni < N && nj >= 0 && nj < N)
			{
				int check = map[ni][nj];
				for(int i = 0; i < 4; i++)
				{
					if(list[s].get(i) == check)
					{
						sum++;
					}
				}
			}
		}
		if(sum == 0)
		{
			return 0;
		}
		if(sum == 1)
		{
			return 1;
		}
		if(sum == 2)
		{
			return 10;
		}
		if(sum == 3)
		{
			return 100;
		}
		else
		{
			return 1000;
		}
	}
	
	public int countEmpty(int x, int y)
	{
		int sum = 0;
		for(int k = 0; k < 4; k++)
		{
			int ni = x + di[k];
			int nj = y + dj[k];
			if(ni >= 0 && ni < N && nj >= 0 && nj < N)
			{
				if(map[ni][nj] == 0)
				{
					sum++;
				}
			}
		}
		
		return sum;
	}
	
	public int countLike(int start, int x, int y)
	{
		int sum = 0;
		for(int k = 0; k < 4; k++)
		{
			int ni = x + di[k];
			int nj = y + dj[k];
			if(ni >= 0 && ni < N && nj >= 0 && nj < N)
			{
				int check = map[ni][nj];
				for(int i = 0; i < 4; i++)
				{
					if(list[start].get(i) == check)
					{
						sum++;
					}
				}
			}
		}
		
		return sum;
	}
	
	public Student start(int s)
	{
		PriorityQueue<Student> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(map[i][j] == 0)
				{
					int like = countLike(s, i, j);
					int empty = countEmpty(i, j);
					pq.add(new Student(i, j, like, empty));
				}
			}
		}
		
//		while(!pq.isEmpty())
//		{
//			Student tmp = pq.poll();
//			System.out.println(tmp.x + " " + tmp.y + " " + tmp.like + " " + tmp.empty);
//		}
		
		return pq.poll();
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		list = new ArrayList[(N*N)+1];
		sList = new int[(N*N)+1];
		
		for(int i = 0; i <= N*N; i++)
		{
			list[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= N*N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			sList[i] = N;
			for(int k = 0; k < 4; k++)
			{
				
				int like = Integer.parseInt(st.nextToken());
				list[N].add(like);
			}
		}
		
		for(int i = 1; i <= N*N; i++)
		{
			Student tmp = T.start(sList[i]);
			map[tmp.x][tmp.y] = sList[i];
		}
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				answer += T.likeNo(map[i][j], i, j);
			}
		}
		
		System.out.println(answer);
	}
}