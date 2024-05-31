package at.ac.fhcampuswien.fhmdb.patterns.statePattern;

import at.ac.fhcampuswien.fhmdb.controllers.MovieListController;

public abstract class SortingState {
    protected MovieListController controller;
    public SortingState(MovieListController con){
        this.controller=con;
    }

    public abstract void sort();
}
