package at.ac.fhcampuswien.fhmdb.patterns.statePattern;

import at.ac.fhcampuswien.fhmdb.controllers.MovieListController;
import at.ac.fhcampuswien.fhmdb.models.SortedState;

public class SortDesc extends SortingState {
    public SortDesc(MovieListController con) {
        super(con);
    }

    @Override
    public void sort() {
        controller.sortMovies(SortedState.ASCENDING);
        controller.setSortedState(new SortAsc(controller));
    }
}
