package com.literalura.literalura.repository;

import com.literalura.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT b FROM Book b WHERE :language MEMBER OF b.languages")
    List<Book> findByLanguage(String language);
    
    @Query("SELECT b FROM Book b ORDER BY b.downloadCount DESC LIMIT 10")
    List<Book> findTop10ByOrderByDownloadCountDesc();
}