package at.ac.fhcampuswien.fhmdb.controllers;

import at.ac.fhcampuswien.fhmdb.interfaces.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.database.*;
import at.ac.fhcampuswien.fhmdb.patterns.observerPattern.Observer;
import at.ac.fhcampuswien.fhmdb.patterns.observerPattern.Observable;
import at.ac.fhcampuswien.fhmdb.ui.UserDialog;
import at.ac.fhcampuswien.fhmdb.ui.WatchlistCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WatchlistController implements Initializable, Observer {

    @FXML
    public JFXListView watchlistView;

    private WatchlistRepository watchlistRepository;

    protected ObservableList<MovieEntity> observableWatchlist = FXCollections.observableArrayList();

    private final ClickEventHandler onRemoveFromWatchlistClicked = (o) -> {
        if (o instanceof MovieEntity) {
            MovieEntity movieEntity = (MovieEntity) o;

            try {
                watchlistRepository.removeFromWatchlist(movieEntity.getApiId());
                observableWatchlist.remove(movieEntity);
            } catch (DataBaseException e) {
                UserDialog dialog = new UserDialog("Database Error", "Could not remove movie from watchlist");
                dialog.show();
                e.printStackTrace();
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<WatchlistMovieEntity> watchlist = new ArrayList<>();
        try {
            //Get instance of WatchlistRepository only once
            watchlistRepository =  WatchlistRepository.getInstance();

            watchlistRepository.subscribe(this);
            watchlist = watchlistRepository.getWatchlist();

            MovieRepository movieRepository = new MovieRepository();
            List<MovieEntity> movies = new ArrayList<>();

            for(WatchlistMovieEntity movie : watchlist) {
                movies.add(movieRepository.getMovie(movie.getApiId()));
            }

            observableWatchlist.addAll(movies);
            watchlistView.setItems(observableWatchlist);
            watchlistView.setCellFactory(movieListView -> new WatchlistCell(onRemoveFromWatchlistClicked));

        } catch (DataBaseException e) {
            UserDialog dialog = new UserDialog("Database Error", "Could not read movies from DB");
            dialog.show();
            e.printStackTrace();
        }

        if(watchlist.size() == 0) {
            watchlistView.setPlaceholder(new javafx.scene.control.Label("Watchlist is empty"));
        }

        System.out.println("WatchlistController initialized");
    }

    public void update(Observable observable) {
        UserDialog dialog = new UserDialog("Notification", "Movie removed from Watchlist!");
        dialog.show();
    }
}
