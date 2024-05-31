package at.ac.fhcampuswien.fhmdb.patterns.statePattern;

import at.ac.fhcampuswien.fhmdb.controllers.MovieListController;
import at.ac.fhcampuswien.fhmdb.models.SortedState;

public class NotSorted extends SortingState {
    public NotSorted(MovieListController con) {
        super(con);
    }

    @Override
    public void sort() {
        controller.sortMovies(SortedState.ASCENDING);
        controller.setSortedState(new SortAsc(controller));
    }
}
