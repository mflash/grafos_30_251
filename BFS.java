import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BFS {
    private Graph g;
    private Set<String> marked;
    private Map<String, String> edgeTo;
    private Map<String, Integer> distTo;
    private String inicial;

    public BFS(Graph g, String inicial) {
        this.inicial = inicial;
        this.g = g;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        for (String v : g.getVerts()) {
            edgeTo.put(v, null);
        }
        bfs(g, inicial);
    }

    private void bfs(Graph g, String inicial) {
        List<String> fila = new LinkedList<>();
        marked.add(inicial); // marca como visitado
        distTo.put(inicial, 0);
        fila.add(inicial);
        while (!fila.isEmpty()) {
            String v = fila.removeFirst();
            for (String w : g.getAdj(v)) {
                if (!marked.contains(w)) {
                    fila.add(w);
                    marked.add(w);
                    edgeTo.put(w, v);
                    distTo.put(w, distTo.get(v) + 1);
                }
            }
        }
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
                sb.append(pathTo(v) + " (" + distTo.get(v) + ")");

            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph g = new Graph("exemplos/tinyG.txt");
        // Graph g = new Graph("exemplos/mediumG.txt");
        long start = System.nanoTime();
        BFS bfs = new BFS(g, "0");
        long end = System.nanoTime();
        System.out.println(bfs);
        long delta = end - start;
        System.out.println("Tempo: " + delta + " ns");

        String destino = "3";
        if (bfs.hasPathTo(destino)) {
            System.out.println("Tem caminho para " + destino);
        } else {
            System.out.println("Não consigo chegar no " + destino + "!");
        }
    }
}