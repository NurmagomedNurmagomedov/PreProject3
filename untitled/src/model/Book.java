package model;

public class Book {
    private String title;
    private String genre;
    private int rating;

    public Book(String title, String genre, int rating) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getRating() { return rating; }

    @Override
    public String toString() { return title + "(" + rating + ")"; }
}