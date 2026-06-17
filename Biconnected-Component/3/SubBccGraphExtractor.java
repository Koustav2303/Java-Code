import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Sub-BCC Induced Graph Extractor
 * * Given a specific query vertex, isolate and extract the full induced edge subgraphs of every single 
 * biconnected component block that this vertex participates in.
 * * Strategy: Intersection Membership Filter
 * Decompose the graph into BCC edge components using Tarjan's stack approach. 
 * Iterate through the components, filtering out and saving any BCC edge sets where either the source 
 * or destination endpoint matches the query vertex ID.
 */
public class SubBccGraphExtractor {
    public List<List<BccDiameter.Edge>> extractSubgraphsForVertex(int queryVertex, List<List<BccDiameter.Edge>> allBccs) {
        List<List<BccDiameter.Edge>> associatedComponents = new ArrayList<>();

        for (List<BccDiameter.Edge> bcc : allBccs) {
            boolean isMember = false;
            for (BccDiameter.Edge e : bcc) {
                if (e.u == queryVertex || e.v == queryVertex) {
                    isMember = true;
                    break;
                }
            }
            if (isMember) {
                associatedComponents.add(bcc); // Retain block component containing the query vertex
            }
        }
        return associatedComponents;
    }

    public static void main(String[] args) {
        System.out.println("Sub-BCC node-induced graph extraction module active.");
    }
}