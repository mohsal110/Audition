package ir.netbox.audition.Models;

public class Movie {
    String title;
    String posterAddress;
    int id;
    int sort;
    Movie.Type type;

    public Movie(String title, String posterAddress, int id, int sort,Movie.Type type) {
        this.title = title;
        this.posterAddress = posterAddress;
        this.id = id;
        this.sort = sort;
        this.type=type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterAddress() {
        return posterAddress;
    }

    public void setPosterAddress(String posterAddress) {
        this.posterAddress = posterAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Movie.Type getType(){
        return type;
    }
    public enum Type {

        MOVIE_COMPLETE,
        MOVIE,
        MOVIE_BASE,
        ICON,
        SQUARE_BIG,
        SINGLE_LINE,
        GAME,
        SQUARE_SMALL,
        DEFAULT,
        SIDE_INFO,
        SIDE_INFO_TEST_1,
        TEXT,
        CHARACTER,
        GRID_SQUARE,
        VIDEO_GRID

    }


}
