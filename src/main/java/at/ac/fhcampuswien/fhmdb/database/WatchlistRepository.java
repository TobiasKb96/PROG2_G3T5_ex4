package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.patterns.observerPattern.Observable;
import at.ac.fhcampuswien.fhmdb.patterns.observerPattern.NotificationType;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public class WatchlistRepository extends Observable{

    Dao<WatchlistMovieEntity, Long> dao;

    private static WatchlistRepository instance;


    private WatchlistRepository() throws DataBaseException {
        try {
            this.dao = DatabaseManager.getInstance().getWatchlistDao();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public List<WatchlistMovieEntity> getWatchlist() throws DataBaseException {
        try {
            return dao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while reading watchlist");
        }
    }
    public int addToWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            // only add movie if it does not exist yet
            long count = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if (count == 0) {
                dao.create(movie);
                notifyAllSubscribers(NotificationType.WatchlistADD);
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while adding to watchlist");
        }
    }

    public int removeFromWatchlist(String apiId) throws DataBaseException {
        try {
            dao.delete(dao.queryBuilder().where().eq("apiId", apiId).query());
            notifyAllSubscribers(NotificationType.WatchlistREMOVE);
            return 1;

        } catch (Exception e) {
            throw new DataBaseException("Error while removing from watchlist");
        }
    }

    //Getter for singleton pattern
    public static WatchlistRepository getInstance() throws DataBaseException {
        if (instance == null) {
            instance = new WatchlistRepository();
        }
        return instance;
    }
}
