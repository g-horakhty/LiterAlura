package com.literalura.literalura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "livros")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @ManyToOne
    private Author author;
    
    @ElementCollection
    @CollectionTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "language")
    private List<String> languages;
    
    private Integer downloadCount;

    // Constructors, Getters and Setters
    public Book() {}
    
    public Book(String title, Author author, List<String> languages, Integer downloadCount) {
        this.title = title;
        this.author = author;
        this.languages = languages;
        this.downloadCount = downloadCount;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }
    public Integer getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Integer downloadCount) { this.downloadCount = downloadCount; }

    @Override
    public String toString() {
        return "Título: " + title + 
               "\nAutor: " + author.getName() + 
               "\nIdiomas: " + String.join(", ", languages) + 
               "\nDownloads: " + downloadCount + "\n";
    }
}