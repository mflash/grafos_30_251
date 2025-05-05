import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFS
{
    private Set<String> marked;
    private Map<String, String> edgeTo;
    private String inicial;

    public DFS(Graph g, String inicial)
    {
        this.inicial = inicial;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        for(String v: g.getVerts()) {
            edgeTo.put(v, null);
        }
        dfs(g, inicial);
    }

    private void dfs(Graph g, String inicial)
    {
        System.out.println("visitando: "+inicial);        
    }

    public static void main(String[] args) {
        Graph g = new Graph("exemplos/tinyG.txt");
        DFS dfs = new DFS(g, "0");        
    }
}