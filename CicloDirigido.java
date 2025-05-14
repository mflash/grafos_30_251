import java.util.HashMap;
import java.util.Map;

public class CicloDirigido {
    private enum Status {
        WHITE, GREY, BLACK
    }

    private Graph g;
    private Map<String, Status> marked;
    private boolean cycle;

    public CicloDirigido(Graph g) {
        this.g = g;
        marked = new HashMap<>();
        for (String v : g.getVerts()) {
            marked.put(v, Status.WHITE);
        }
        cycle = false; // a princípio não tem ciclo...
        for (String v : g.getVerts()) {
            if (marked.get(v).equals(Status.WHITE))
                cycle = dfs(v);
            if (cycle)
                break;
        }
    }

    private boolean dfs(String v) {
        // System.out.println("entrando: " + inicial);
        marked.put(v, Status.GREY); // entrei no vértice
        for (String u : g.getAdj(v)) {
            if (marked.get(u).equals(Status.GREY)) {
                System.out.println("Ciclo detectado na aresta: " + v + "-" + u);
                return true;
            }
            if (marked.get(u).equals(Status.WHITE)) {
                if (dfs(u)) {
                    return true;
                }
            }
        }
        // Neste ponto, não achamos um ciclo...
        marked.put(v, Status.BLACK);
        return false;
        // System.out.println("saindo c: " + inicial);
    }

    public boolean hasCycle() {
        return cycle;
    }

    public static void main(String[] args) {
        // Graph g = new Graph("exemplos/tinyG.txt");
        Digraph g = new Digraph();
        g.addEdge("0", "1");
        g.addEdge("1", "3");
        g.addEdge("3", "2");
        g.addEdge("3", "4");
        // g.addEdge("2", "0");
        g.addEdge("5", "6");
        g.addEdge("6", "7");
        g.addEdge("7", "5");
        // g = new Graph("exemplos/mediumG.txt");

        CicloDirigido ciclo = new CicloDirigido(g);
        if (ciclo.hasCycle())
            System.out.println("Tem ciclo!");
        else
            System.out.println("Não tem ciclo...");

        // System.out.println(g.toDot());
    }
}