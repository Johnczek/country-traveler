package cz.johnczek.countrytraveler.core.service;

import cz.johnczek.countrytraveler.country.graph.CountryNode;
import lombok.NonNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Primary
@Service
public class BFSService implements ShortestPathService {

    @Override
    public List<String> findShortestPathInGraph(
            @NonNull CountryNode source, @NonNull CountryNode destination) {

        processGraphViaBFS(source, destination);

        List<String> result = new ArrayList<>();
        CountryNode currentNode = destination;
        while (currentNode != null) {
            result.add(currentNode.getName());
            currentNode = currentNode.getPrevious();
        }

        // If we have list of size 1 in case of not equal source and destination, it means there is no possible way
        if (!source.getName().equals(destination.getName()) && result.size() == 1) {
            return Collections.emptyList();
        }

        Collections.reverse(result);

        return result;
    }

    /**
     * Method processes graph using Breath First Search (BFS) trying to find shortest path between source and destination.
     * Method modifies nodes on the path and ends as soon as first reaches destination node.
     * In each iteration modifies nodes on path so after its end is it possible to find a way using back pointers from
     * destination back to source.
     *
     * @param source source node from which we start searching
     * @param destination destionation node we want to reach
     */
    private static void processGraphViaBFS(@NonNull CountryNode source, @NonNull CountryNode destination) {
        Queue<CountryNode> queue = new LinkedList<>();

        source.setProcessed(true);
        queue.add(source);

        while (!queue.isEmpty()) {
            CountryNode current = queue.poll();

            for (CountryNode neighbour: current.getNeighbours()) {
                if (neighbour.isProcessed()) {
                    continue;
                }

                neighbour.setProcessed(true);
                queue.add(neighbour);
                neighbour.setPrevious(current);

                if (neighbour.getName().equals(destination.getName())) {
                    queue.clear();
                    break;
                }
            }
        }
    }
}
