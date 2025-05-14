import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CicloNaoDirigido {
    private Graph g;
    private Set<String> marked;
    private Set<String> edgeSet;
    private boolean cycle;

    public CicloNaoDirigido(Graph g) {
        this.g = g;
        marked = new HashSet<>();
        edgeSet = new HashSet<>();
        cycle = false; // a princípio não tem ciclo...
        for (String v : g.getVerts()) {
            if (!marked.contains(v))
                cycle = dfs(v);
            if (cycle)
                break;
        }
    }

    private String edgeName(String v1, String v2) {
        if (v1.compareTo(v2) > 0)
            return v2 + "-" + v1;
        return v1 + "-" + v2;
    }

    private boolean dfs(String v) {
        // System.out.println("entrando: " + inicial);
        marked.add(v); // marca como visitado
        for (String u : g.getAdj(v)) {
            if (!marked.contains(u)) {
                // adiciona a aresta ao conj. arestas
                edgeSet.add(edgeName(v, u));
                boolean res = dfs(u);
                if (res)
                    return res;
            } else {
                // verifica se aresta u-v (ou v-u)
                // não está no conj. arestas
                String eName = edgeName(u, v);
                if (!edgeSet.contains(eName)) {
                    // Se não estiver lá, significa
                    // que chegamos por um OUTRO
                    // CAMINHO: ciclo!
                    System.out.println("Aresta onde detecta o ciclo: " + eName);
                    return true;
                }
            }
        }
        // Neste ponto, não achamos um ciclo...
        return false;
        // System.out.println("saindo c: " + inicial);
    }

    public boolean hasCycle() {
        return cycle;
    }

    public static void main(String[] args) {
        // Graph g = new Graph("exemplos/tinyG.txt");
        Graph g = new Graph();
        g.addEdge("0", "1");
        g.addEdge("1", "3");
        g.addEdge("3", "2");
        g.addEdge("3", "4");
        // g.addEdge("2", "0");
        g.addEdge("5", "6");
        g.addEdge("6", "7");
        g.addEdge("7", "5");
        g = new Graph("exemplos/mediumG.txt");

        CicloNaoDirigido ciclo = new CicloNaoDirigido(g);
        if (ciclo.hasCycle())
            System.out.println("Tem ciclo!");
        else
            System.out.println("Não tem ciclo...");

        System.out.println(g.toDot());
    }
}