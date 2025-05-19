import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrdemTopologica {
    private Graph g;
    private Set<String> marked;
    private Map<String, String> edgeTo;
    private String inicial;

    public OrdemTopologica(Graph g, String inicial) {
        this.inicial = inicial;
        this.g = g;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        for (String v : g.getVerts()) {
            edgeTo.put(v, null);
        }
        dfs(g, inicial);
    }

    private void dfs(Graph g, String inicial) {
        System.out.println("entrando: " + inicial);
        marked.add(inicial); // marca como visitado
        for (String w : g.getAdj(inicial)) {
            if (!marked.contains(w)) {
                edgeTo.put(w, inicial);
                dfs(g, w);
            }
        }
        System.out.println("saindo  c: " + inicial);
    }

    public boolean hasPathTo(String v) {
        return marked.contains(v);
    }

    public List<String> pathTo(String v) {
        if (!marked.contains(v))
            return new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(v);
        String atual = edgeTo.get(v);
        while (atual != null) {
            path.add(0, atual);
            atual = edgeTo.get(atual);
        }
        return path;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String v : g.vertices) {
            sb.append(v + ": ");
            if (!marked.contains(v)) {
                sb.append(" sem caminho");
            } else {
                sb.append(pathTo(v));

            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph g = new Graph("exemplos/tinyG.txt");
        // Graph g = new Graph("exemplos/mediumG.txt");
        long start = System.nanoTime();
        DFS dfs = new DFS(g, "0");
        long end = System.nanoTime();
        System.out.println(dfs);
        long delta = end - start;
        System.out.println("Tempo: " + delta + " ns");

        if (dfs.hasPathTo("7")) {
            System.out.println("Tem caminho para 7");
        } else {
            System.out.println("Não consigo chegar no 7!");
        }
    }
}