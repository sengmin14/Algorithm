import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long[][] point;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		point = new long[N+1][2];
		
		for(int i= 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			point[i][0] = x;
			point[i][1] = y;
		}
		point[N][0] = point[0][0];
		point[N][1] = point[0][1];
		
		double sumX = 0;
		double sumY = 0;
		double area = 0;
		
		for(int i = 0; i < N; i++)
		{
			area += point[i][0] * point[i+1][1] - point[i][1] * point[i+1][0];
			sumX += point[i][0] * point[i+1][1];
			sumY += point[i][1] * point[i+1][0];
		}
		
		
		System.out.printf("%.1f", Math.abs(sumX-sumY)/2.0);
		// System.out.printf("%.1f", Math.abs(area)/2.0);
		
	}
}
