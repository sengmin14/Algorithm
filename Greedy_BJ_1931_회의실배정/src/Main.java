import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long[][] meeting;
	static long start;
	static long answer;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		meeting = new long[N][2];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			meeting[i][0] = Long.parseLong(st.nextToken());
			meeting[i][1] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(meeting, new Comparator<long[]>() {
		    public int compare(long[] o1, long[] o2) {
		    	if(o1[1] == o2[1]) {
		    		return (int) (o1[0] - o2[0]);
		    	}
		    	else {
		    		return (int) (o1[1] - o2[1]);
		    	}
		        // return Long.compare(o1[1], o2[1]);
		    }
		});
		
		
		for(int i = 0; i < N; i++)
		{
			if(start <= meeting[i][0])
			{
				start = meeting[i][1];
				answer++;
			}
		}
		System.out.println(answer);
	}
}
