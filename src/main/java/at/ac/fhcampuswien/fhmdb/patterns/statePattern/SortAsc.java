package at.ac.fhcampuswien.fhmdb.patterns.statePattern;

import at.ac.fhcampuswien.fhmdb.controllers.MovieListController;
import at.ac.fhcampuswien.fhmdb.models.SortedState;

public class SortAsc extends SortingState {
    public SortAsc(MovieListController con) {
        super(con);
    }

    @Override
    public void sort() {
        controller.sortMovies(SortedState.DESCENDING);
        controller.setSortedState(new SortDesc(controller));
    }
}
