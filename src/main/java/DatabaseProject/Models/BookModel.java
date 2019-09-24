package DatabaseProject.Models;

import javax.persistence.*;

@Entity
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private int isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column
    private String review;

    @Column(nullable = false)
    private int pages;

    @Column
    private float rating;

    public BookModel(int isbn, String title, String author, String review, int pages, float rating) {
        this.title = title;
        this.author = author;
        this.review = review;
        this.pages = pages;
        this.isbn = isbn;
        this.rating = rating;
    }
    public BookModel(String[] data) {
        this(Integer.parseInt(data[0]), data[1], data[2], data[3], Integer.parseInt(data[4]), Float.parseFloat(data[5]));
    }
    public BookModel(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
