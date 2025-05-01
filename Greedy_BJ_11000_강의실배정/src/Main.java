import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main
{
	static int N;
	static int[][] lecture;
	static PriorityQueue<Integer> pq;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		lecture = new int[N][2];
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			lecture[i][0] = Integer.parseInt(st.nextToken());
			lecture[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(lecture, new Comparator<int[]>() {
			
			public int compare(int[] o1, int[] o2)
			{
				if(o1[0] == o2[0])
				{
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});
		
		pq = new PriorityQueue<Integer>();
		pq.offer(0);
		
		for(int i = 0; i < N; i++)
		{
			int tmp = lecture[i][0];
			if(pq.peek() <= tmp)
			{
				pq.poll();
			}
			pq.offer(lecture[i][1]);
		}
		
		System.out.println(pq.size());
	}
}