import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>
{
	int x;
	int y;
	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Node o) {
		if(this.y == o.y)
		{
			return this.x - o.x;
		}
		else
		{
			return this.y - o.y;
		}
	}
}

class Main
{
	static int r;
	static int c;
	static int k;
	
	static int[][] map;
	static int[][] copyMap;
	
	static int row = 3;
	static int col = 3;
	
	static HashMap<Integer, Integer> Hmap;
	
	public void copy()
	{
		for(int i = 0; i < 101; i++)
		{
			for(int j = 0; j < 101; j++)
			{
				map[i][j] = copyMap[i][j];
			}
		}
	}
	
	public void colCal()
	{
		copyMap = new int[101][101];
		
		for(int j = 1; j < 101; j++)
		{
			PriorityQueue<Node> pq = new PriorityQueue<>();
			Hmap = new HashMap<>();
			for(int i = 1; i < 101; i++)
			{
				if(map[i][j] == 0)
				{
					continue;
				}
				else
				{
					if(Hmap.containsKey(map[i][j]))
					{
						Hmap.put(map[i][j], Hmap.get(map[i][j])+1);
					}
					else
					{
						Hmap.put(map[i][j], 1);
					}
				}
			}
			row = Integer.max(row, Hmap.size()*2);
			int idx = 1;
			for(int t : Hmap.keySet())
			{
				pq.add(new Node(t, Hmap.get(t)));
			}
			
			while(!pq.isEmpty())
			{
				Node tmp = pq.poll();
				copyMap[idx][j] = tmp.x;
				idx++;
				copyMap[idx][j] = tmp.y;
				idx++;
//				System.out.print(tmp.x + " " + tmp.y + " ");
			}
//			System.out.println();
		}
	}
	
	public void rowCal()
	{
		copyMap = new int[101][101];
		
		for(int i = 1; i < 101; i++)
		{
			PriorityQueue<Node> pq = new PriorityQueue<>();
			Hmap = new HashMap<>();
			for(int j = 1; j < 101; j++)
			{
				if(map[i][j] == 0)
				{
					continue;
				}
				else
				{
					if(Hmap.containsKey(map[i][j]))
					{
						Hmap.put(map[i][j], Hmap.get(map[i][j])+1);
					}
					else
					{
						Hmap.put(map[i][j], 1);
					}
				}
			}
			col = Integer.max(col, Hmap.size()*2);
			int idx = 1;
			for(int t : Hmap.keySet())
			{
				pq.add(new Node(t, Hmap.get(t)));
			}
			
			while(!pq.isEmpty())
			{
				Node tmp = pq.poll();
				copyMap[i][idx] = tmp.x;
				idx++;
				copyMap[i][idx] = tmp.y;
				idx++;
//				System.out.print(tmp.x + " " + tmp.y + " ");
			}
//			System.out.println();
		}
		
	}
	
	public static void main(String[] args) throws IOException
	{	
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[101][101];
		
		for(int i = 1; i <= row; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= col; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		
		while(true)
		{
			if(answer > 100)
			{
				answer = -1;
				break;
			}
			if(map[r][c] == k)
			{
				break;
			}
			if(row >= col)
			{
				T.rowCal();
				T.copy();
				answer++;
			}
			else
			{
				T.colCal();
				T.copy();
				answer++;
			}
		}
		
		System.out.println(answer);
	}
}