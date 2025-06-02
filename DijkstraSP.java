import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DijkstraSP {
    private Map<String, Edge> edgeTo;
    private Map<String, Double> distTo;
    private IndexMinHeap<String, Double> pq;
    private String start;

    public DijkstraSP(EdgeWeightedDigraph g, String s) {
        start = s;
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        pq = new IndexMinHeap<>();

        for (String v : g.getVerts()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(s, 0.0);
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            String v = pq.delMin();
            for (Edge e : g.getAdj(v)) {
                relax(e);
            }
            // System.out.println("Tam heap: "+pq.size());
        }
    }

    public boolean hasPathTo(String w) {
        return distTo.get(w) != Double.POSITIVE_INFINITY;
    }

    public double distTo(String w) {
        if(distTo.get(w) == Double.POSITIVE_INFINITY)
            throw new UnsupportedOperationException("Sem caminho para este vértice!");
        return distTo.get(w);
    }

    public Iterable<Edge> pathTo(String w) {
        LinkedList<Edge> path = new LinkedList<>();
        if(!hasPathTo(w))
            return path;
        Edge e = edgeTo.get(w);
        while(e != null) {
            path.add(0, e);
            e = edgeTo.get(e.getV());
        }
        return path;
    }

    private void relax(Edge e) {
        String v = e.getV();
        String w = e.getW();
        double edgeWeight = distTo.get(v) + e.getWeight();
        if(distTo.get(w) > edgeWeight) {
            distTo.put(w, edgeWeight);
            edgeTo.put(w, e);
            if(pq.contains(w))
                pq.decreaseValue(w, edgeWeight);
            else
                pq.insert(w, edgeWeight);
        }
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph("exemplos/tinyEWD.txt");
        DijkstraSP dij = new DijkstraSP(g, "7");

        for(String v: g.getVerts()) {
            System.out.print(v+": ");
            if(!dij.hasPathTo(v))
                System.out.println("Sem caminho");
            else {
                for(Edge e: dij.pathTo(v)) {
                    System.out.print(e+" ");
                }
                System.out.println(dij.distTo(v));
            }
        }
    }
}
