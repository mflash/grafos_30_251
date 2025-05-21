import java.util.ArrayList;
import java.util.List;

public class KruskalMST {
    
    private EdgeWeightedGraph g;
    private List<Edge> mst;
    private double custoTotal;

    public KruskalMST(EdgeWeightedGraph g)
    {
        this.g = g;
        mst = new ArrayList<>();
        custoTotal = 0;

        MinHeap<Edge> pq = new MinHeap<>();
        for(Edge e: g.getEdges())
            pq.put(e);

        // Usamos union-find para identificar
        // se ao conectar dois vértices, formamos
        // um ciclo ou não
        UnionFind uf = new UnionFind(g);

        // Enquanto há arestas...
        while(!pq.isEmpty()) {
            Edge e = pq.delMin();
            String conjV = uf.find(e.getV());
            String conjW = uf.find(e.getW());
            // Se não estão no mesmo conjunto,
            // posso adicionar a aresta sem
            // formar ciclo!
            if(!conjV.equals(conjW)) {
                mst.add(e); // adiciona à árvore
                // altera a "cor" da aresta no grafo original
                e.setColor("color=red penwidth=2");
                uf.union(conjV, conjW);
                custoTotal += e.getWeight();
            }
        }

        // while(!pq.isEmpty())
            // System.out.println(pq.delMin());
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public Iterable<Edge> getMST() {
        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph("exemplos/tinyEWG.txt");
        KruskalMST k = new KruskalMST(g);
        System.out.println("Custo mínimo: "+k.getCustoTotal());
        System.out.println("Arestas:");
        for(Edge e: k.getMST())
            System.out.println(e);

        
        System.out.println(g.toDot());
    }
}
