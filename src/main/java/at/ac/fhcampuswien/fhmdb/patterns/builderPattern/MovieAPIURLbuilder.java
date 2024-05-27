package at.ac.fhcampuswien.fhmdb.patterns.builderPattern;

import at.ac.fhcampuswien.fhmdb.models.Genre;

public class MovieAPIURLbuilder {

    private Genre genre;
    private String releaseYear;
    private String query;
    private String ratingFrom;
    private String baseUrl;
    private static final String DELIMITER = "&";

    public MovieAPIURLbuilder genre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public MovieAPIURLbuilder releaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public MovieAPIURLbuilder query(String query) {
        this.query = query;
        return this;
    }

    public MovieAPIURLbuilder ratingFrom(String ratingFrom) {
        this.ratingFrom = ratingFrom;
        return this;
    }

    public MovieAPIURLbuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String build(){
        StringBuilder url = new StringBuilder(baseUrl);
        if ( (query != null && !query.isEmpty()) ||
                genre != null || releaseYear != null || ratingFrom != null) {

            url.append("?");

            // check all parameters and add them to the url
            if (query != null && !query.isEmpty()) {
                url.append("query=").append(query).append(DELIMITER);
            }
            if (genre != null) {
                url.append("genre=").append(genre).append(DELIMITER);
            }
            if (releaseYear != null) {
                url.append("releaseYear=").append(releaseYear).append(DELIMITER);
            }
            if (ratingFrom != null) {
                url.append("ratingFrom=").append(ratingFrom).append(DELIMITER);
            }
        }

        return url.toString();
    }


}
