import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 이를 코드로 구현하기 위해 아래와 같이 함수와 변수를 정의합니다.
1. int dfs(int A, bool isRoot) : A의 자식 노드가 A를 거치지 않고 도달할 수 있는 정점 중 가장 먼저 dfs함수가 방문한 정점을 반환한다.
isRoot는 정점 A가 루트노드인지를 나타냅니다.
2. 정점 i가 DFS탐색에서 발견된 순서 : discovered[i]
3. 정점 i가 절단점인지 여부 : isCutVertex[i]
백준 온라인 저지 11266번 단절점 문제에 대한 코드를 작성해 보겠습니다.
출처: https://bowbowbow.tistory.com/3 [멍멍멍]
 * */
public class 단절점 {
	static int T, V, E; //V(1≤V≤10,000), E(1≤E≤100,000)
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

	// 자신이 갈수 있는 가장 앞 번호를 리턴한다.
	private static int dfs(int parent, boolean isroot) {
		
		int ret = seq; 

		int cCnt = 0;
		// 방문순서
		visited[parent] = seq++;
		
		// 하위 점들 방문
		for(int child : G[parent]) {

			// 하위 점이 미방문 점이면서 루트이면 count up 
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

