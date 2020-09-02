import java.io.*;
import java.util.*;

// 다익스트라 알고리즘 사용
// 1. 거리 배열 만들어서 시작점에 0, 나머지는 max값 넣는다
// 2. 처리하지 않은 정점 중, 거리값이 가장 작은 정점 고른다
// 3. 그 정점에 연결된 간선들에 대해 인접한 다른 정점의 값을 갱신한다
// 4. 2,3번 과정을 모든 정점에 대해 처리할때까지 반복

public class 다익스트라 {

	static int T, N, M, ans;
	static ArrayList<Integer> list[];
	static ArrayList<Integer> time[];
	static long dist[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("sample/shortest_path.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());

			list = new ArrayList[N + 1];
			time = new ArrayList[N + 1];

			
			for (int nc = 0; nc <= N; nc++) {
				list[nc] = new ArrayList<>();
			}
			
			int a,b;
			for (int nc = 0; nc < N; nc++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				list[b].add(a);
				
				
			}
			
			for (int nc = 1; nc <= N; nc++) {
				System.out.println(list[nc].size());
				if(list[nc].size()>1) ans =nc;
			}
			
			System.out.println("#"+tc+" "+list[ans].get(1)+ " " +ans);
		}
	}

}
