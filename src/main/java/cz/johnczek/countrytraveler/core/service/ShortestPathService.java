package cz.johnczek.countrytraveler.core.service;

import cz.johnczek.countrytraveler.country.graph.CountryNode;
import lombok.NonNull;

import java.util.List;

public interface ShortestPathService {

    /**
     * Method finds the shortest path (or one of the shortest paths) between two graph nodes.
     *
     * Primary algorithm used to perform this action is BFS. But it is possible to implement this interface with other
     * service performing this task via other algorithm.
     *
     * @param source source node from which we want to find
     * @param destination destination node we want to reach
     * @return shortest path between two nodes. If no path is present, empty list is returned.
     */
    List<String> findShortestPathInGraph(
            @NonNull CountryNode source, @NonNull CountryNode destination);
}
