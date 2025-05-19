public class MiniES
{
    public static void main(String[] args) {
        Digraph g = new Digraph();
        In arq = new In("exemplos/miniES.txt");
        while(arq.hasNextLine())
        {
            String linha = arq.readLine();
            String[] dados = linha.split("/");
            String start = dados[0];
            for(int i=1; i<dados.length; i++) {
                String end = dados[i];
                g.addEdge(start, end);                
            }
        }
        System.out.println(g.toDot());
        OrdemTopologica ot = new OrdemTopologica(g);
        System.out.println("Ordem topológica:");
        for(String v: ot.getOrdemTopologica()) {
            System.out.println("-> "+v);
        }        
    }
}