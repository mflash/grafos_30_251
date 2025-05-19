import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class OrdemTopologica {
    private Graph g;
    private Set<String> marked;
    private List<String> ordemTopo;    

    public OrdemTopologica(Graph g) {    
        this.g = g;
        marked = new HashSet<>();
        ordemTopo = new LinkedList<>();
        for(String v: g.getVerts()) {
            if(!marked.contains(v))
                dfs(g,v);
        }
    }

    private void dfs(Graph g, String inicial) {
        // System.out.println("entrando: " + inicial);
        marked.add(inicial); // marca como visitado
        for (String w : g.getAdj(inicial)) {
            if (!marked.contains(w)) {
                dfs(g, w);
            }
        }
        // Inclui o vértice no INÍCIO da lista
        // de ordem topológica (pós-ordem REVERSA)
        ordemTopo.add(0, inicial);
        // System.out.println("saindo  c: " + inicial);
    }

    public Iterable<String> getOrdemTopologica()
    {
        return ordemTopo;
    }

    public static void main(String[] args) {
        Digraph g = new Digraph("exemplos/tinyG.txt");
        // Graph g = new Graph("exemplos/mediumG.txt");
        OrdemTopologica ot = new OrdemTopologica(g);
        System.out.println("Ordem topológica:");
        for(String v: ot.getOrdemTopologica()) {
            System.out.println("-> "+v);
        }        
        System.out.println(g.toDot());
    }
}