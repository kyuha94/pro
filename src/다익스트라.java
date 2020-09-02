import java.io.*;
import java.util.*;

// ���ͽ�Ʈ�� �˰��� ���
// 1. �Ÿ� �迭 ���� �������� 0, �������� max�� �ִ´�
// 2. ó������ ���� ���� ��, �Ÿ����� ���� ���� ���� ����
// 3. �� ������ ����� �����鿡 ���� ������ �ٸ� ������ ���� �����Ѵ�
// 4. 2,3�� ������ ��� ������ ���� ó���Ҷ����� �ݺ�

public class ���ͽ�Ʈ�� {

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
