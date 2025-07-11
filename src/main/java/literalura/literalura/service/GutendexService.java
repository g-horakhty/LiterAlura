package com.literalura.literalura.service;

import com.literalura.literalura.dto.BookResponse;
import com.literalura.literalura.model.Author;
import com.literalura.literalura.model.Book;
import com.literalura.literalura.repository.AuthorRepository;
import com.literalura.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class GutendexService {
    @Value("${gutenberg.api.url}")
    private String apiUrl;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    public Optional<Book> getBookByTitle(String title) {
        try {
            String url = apiUrl + "?search=" + URLEncoder.encode(title, StandardCharsets.UTF_8);
            BookResponse response = restTemplate.getForObject(url, BookResponse.class);
            
            if (response != null && !response.results().isEmpty()) {
                BookResponse.BookData bookData = response.results().get(0);
                
                // Verifica se o livro já existe no banco
                Optional<Book> existingBook = bookRepository.findByTitleContainingIgnoreCase(bookData.title());
                if (existingBook.isPresent()) {
                    return existingBook;
                }
                
                // Processa o autor
                Author author = processAuthor(bookData);
                
                // Cria e salva o novo livro
                Book book = new Book(
                    bookData.title(),
                    author,
                    bookData.languages(),
                    bookData.downloadCount()
                );
                
                bookRepository.save(book);
                return Optional.of(book);
            }
        } catch (Exception e) {
            System.err.println("Erro ao acessar a API Gutendex: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    private Author processAuthor(BookResponse.BookData bookData) {
        if (bookData.authors().isEmpty()) {
            return new Author("Autor Desconhecido", null, null);
        }
        
        var authorResponse = bookData.authors().get(0);
        Optional<Author> existingAuthor = authorRepository.findByNameContainingIgnoreCase(authorResponse.name());
        
        if (existingAuthor.isPresent()) {
            return existingAuthor.get();
        } else {
            Author newAuthor = new Author(
                authorResponse.name(),
                authorResponse.birthYear(),
                authorResponse.deathYear()
            );
            return authorRepository.save(newAuthor);
        }
    }
}