import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * �̸� �ڵ�� �����ϱ� ���� �Ʒ��� ���� �Լ��� ������ �����մϴ�.
1. int dfs(int A, bool isRoot) : A�� �ڽ� ��尡 A�� ��ġ�� �ʰ� ������ �� �ִ� ���� �� ���� ���� dfs�Լ��� �湮�� ������ ��ȯ�Ѵ�.
isRoot�� ���� A�� ��Ʈ��������� ��Ÿ���ϴ�.
2. ���� i�� DFSŽ������ �߰ߵ� ���� : discovered[i]
3. ���� i�� ���������� ���� : isCutVertex[i]
���� �¶��� ���� 11266�� ������ ������ ���� �ڵ带 �ۼ��� ���ڽ��ϴ�.
��ó: https://bowbowbow.tistory.com/3 [�۸۸�]
 * */
public class ������ {
	static int T, V, E; //V(1��V��10,000), E(1��E��100,000)
	static int[] visited;
	static int[] cut;
	static ArrayList<Integer> G[];
	static int seq;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// System.setIn(new FileInputStream("sample/shortest_path.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		visited = new int[V+1];
		cut = new int[V+1];
		G = new ArrayList[V+1];

		for(int v=1; v<=V;v++ ) {
			visited[v] = 0;
			G[v] = new ArrayList<>();
		}
		
		int a, b;
		for(int e=0; e<E;e++ ) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			G[a].add(b);
			G[b].add(a);			
		}
		

		seq=1;
		for(int v=1;v<=V ;v++) {
			if(visited[v]==0) dfs(v,true);
		}
		
		
		for(int v=1;v<=V ;v++) {
			if(cut[v]==1)System.out.println(v);
		}
	}

	// �ڽ��� ���� �ִ� ���� �� ��ȣ�� �����Ѵ�.
	private static int dfs(int parent, boolean isroot) {
		
		int ret = seq; 

		int cCnt = 0;
		// �湮����
		visited[parent] = seq++;
		
		// ���� ���� �湮
		for(int child : G[parent]) {

			// ���� ���� �̹湮 ���̸鼭 ��Ʈ�̸� count up 
			if(isroot & visited[child]==0) cCnt++;
			
			if(visited[child]==0) {
				int low = dfs(child,false);
				if(!isroot && low >= visited[parent]) {
					cut[parent]=1;
				}
				ret = Math.min(ret, low);
			}else {
				ret = Math.min(ret, visited[child]);
			}
			
		}
		
		if(isroot && cCnt>1) cut[parent]=1; 
		
		return ret;
		
	}
}

