import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, result, people[];
    static List<Integer> alist, blist;
    static boolean select[], visit[];
    static List<Integer> adjList[];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        result = Integer.MAX_VALUE;
        people = new int[N+1];

        adjList = new ArrayList[N+1];
        for(int i=1;i<=N;i++)
            adjList[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++)
            people[i] = Integer.parseInt(st.nextToken());

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            for(int j=0;j<num;j++) 
                adjList[i].add(Integer.parseInt(st.nextToken()));
        }

        select = new boolean[N+1];
        subset(1);
        if(result >= 10000000)
        {
        	System.out.println(-1);
        }
        else
        {
        	System.out.println(result);
        }
       
    }

    private static void subset(int cnt) {
        if(cnt == N+1) {
            alist = new ArrayList<>();
            blist = new ArrayList<>();

            for(int i=1;i<=N;i++) {
                if(select[i])
                    alist.add(i);
                else
                    blist.add(i);
            }

            if(alist.size() == 0 || blist.size() == 0)
                return;

//            boolean b = connect(alist);
//            boolean b2 = connect(blist);
//            System.out.println(b+" "+b2);
            
            if(connect(alist) && connect(blist)) {
                int diff = getDiff(alist, blist);
                
//                System.out.println("-----");
//                System.out.println("a"+ alist);
//                System.out.println("b"+blist);
                
                result = Math.min(result, diff);
                if(result == Integer.MAX_VALUE) 
                    result = -1;
            }
            return;
        }

        select[cnt] = true;
        subset(cnt+1);
        select[cnt] = false;
        subset(cnt+1);
    }

    private static int getDiff(List<Integer> alist, List<Integer> blist) {
        int aCnt = 0, bCnt = 0;
        for(int i=0;i<alist.size();i++) {
            int num = alist.get(i);
            aCnt += people[num]; 
        }
        for(int i=0;i<blist.size();i++) {
            int num = blist.get(i);
            bCnt += people[num]; 
        }
        return Math.abs(aCnt-bCnt);
    }

    private static boolean connect(List<Integer> list) {
        Queue<Integer> q = new ArrayDeque<>();
        visit = new boolean[N+1];
        int cnt = 1;

        visit[list.get(0)]=true;
        q.offer(list.get(0));

        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int i=0;i<adjList[cur].size();i++) {
                int tmp = adjList[cur].get(i);
                
                if(list.contains(tmp) && !visit[tmp]) {
                    cnt++;
                    q.offer(tmp);
                    visit[tmp] = true;
                }    
            }
        }
        
        if(cnt == list.size()) 
            return true;
        else 
            return false;
    }

}