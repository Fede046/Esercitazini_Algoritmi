import java.util.*;

class Edge {
    int target; // Nodo di destinazione
    int weight; // Peso dell'arco

    public Edge(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

class Node implements Comparable<Node> {
    int vertex;   // Nodo
    int key;      // Peso minimo per raggiungere il nodo

    public Node(int vertex, int key) {
        this.vertex = vertex;
        this.key = key;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.key, other.key);
    }
}

public class PrimMST {
    private int numVertices; // Numero di nodi nel grafo
    private List<List<Edge>> adjacencyList; // Lista di adiacenza

    public PrimMST(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // Aggiunge un arco al grafo
    public void addEdge(int source, int target, int weight) {
        adjacencyList.get(source).add(new Edge(target, weight));
        adjacencyList.get(target).add(new Edge(source, weight)); // Grafo non orientato
    }

    // Algoritmo di Prim
    public int[] primMST(int startNode) {
        int[] parent = new int[numVertices]; // Array per memorizzare i padri dei nodi nell'MST
        int[] key = new int[numVertices];    // Array per memorizzare i pesi minimi
        boolean[] inMST = new boolean[numVertices]; // Array per tenere traccia dei nodi già nell'MST

        // Inizializzazione
        Arrays.fill(parent, -1);
        Arrays.fill(key, Integer.MAX_VALUE);
        key[startNode] = 0; // Il peso della radice è 0

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(startNode, 0));

        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().vertex; // Estrai il nodo con il peso minimo
            inMST[u] = true; // Aggiungi il nodo all'MST

            // Itera sui nodi adiacenti
            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.target;
                int weight = edge.weight;

                // Se il nodo non è nell'MST e il peso è minore del valore attuale
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight; // Aggiorna il peso minimo
                    parent[v] = u;   // Imposta u come padre di v
                    priorityQueue.add(new Node(v, key[v])); // Aggiungi alla coda con priorità
                }
            }
        }

        return parent; // Restituisce l'array dei padri che rappresenta l'MST
    }

    // Stampa l'MST
    public void printMST(int[] parent) {
        System.out.println("Arco \tPeso");
        for (int i = 1; i < numVertices; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + getWeight(parent[i], i));
        }
    }

    // Restituisce il peso dell'arco tra due nodi
    private int getWeight(int u, int v) {
        for (Edge edge : adjacencyList.get(u)) {
            if (edge.target == v) {
                return edge.weight;
            }
        }
        return -1; // Se l'arco non esiste
    }

    public static void main(String[] args) {
        int numVertices = 5;
        PrimMST graph = new PrimMST(numVertices);

        // Aggiungi archi al grafo
        graph.addEdge(0, 1, 4); // 0 - 1 con peso 4
        graph.addEdge(0, 2, 8); // 0 - 2 con peso 8
        graph.addEdge(1, 2, 8); // 1 - 2 con peso 8
        graph.addEdge(1, 3, 11); // 1 - 3 con peso 11
        graph.addEdge(2, 3, 7); // 2 - 3 con peso 7
        graph.addEdge(2, 4, 4); // 2 - 4 con peso 4
        graph.addEdge(3, 4, 9); // 3 - 4 con peso 9

        int startNode = 0; // Nodo di partenza
        int[] parent = graph.primMST(startNode);

        // Stampa l'MST
        graph.printMST(parent);
    }
}