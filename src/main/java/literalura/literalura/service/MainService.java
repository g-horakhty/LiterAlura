package com.literalura.literalura.service;

import com.literalura.literalura.model.Author;
import com.literalura.literalura.model.Book;
import com.literalura.literalura.repository.AuthorRepository;
import com.literalura.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class MainService {
    private final Scanner scanner = new Scanner(System.in);
    
    @Autowired
    private GutendexService gutendexService;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    public void showMenu() {
        var option = -1;
        
        while (option != 0) {
            System.out.println("""
                \n
                ** LiterAlura - Catálogo de Livros **
                
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores
                4 - Listar autores em determinado ano
                5 - Listar livros em determinado idioma
                6 - Top 10 livros mais baixados
                0 - Sair
                """);
            
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 3:
                    listAllAuthors();
                    break;
                case 4:
                    listAuthorsByYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 6:
                    listTop10Books();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
    
    private void searchBookByTitle() {
        System.out.println("Digite o título do livro:");
        var title = scanner.nextLine();
        
        Optional<Book> book = gutendexService.getBookByTitle(title);
        if (book.isPresent()) {
            System.out.println("\nLivro encontrado:");
            System.out.println(book.get());
        } else {
            System.out.println("Livro não encontrado na API Gutendex");
        }
    }
    
    private void listAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("Nenhum livro registrado no banco de dados");
            return;
        }
        
        System.out.println("\nLivros registrados:");
        books.forEach(System.out::println);
    }
    
    private void listAllAuthors() {
        List<Author> authors = authorRepository.findAllByOrderByNameAsc();
        if (authors.isEmpty()) {
            System.out.println("Nenhum autor registrado no banco de dados");
            return;
        }
        
        System.out.println("\nAutores registrados:");
        authors.forEach(author -> {
            System.out.println(author);
            author.getBooks().forEach(book -> 
                System.out.println("  - " + book.getTitle()));
        });
    }
    
    private void listAuthorsByYear() {
        System.out.println("Digite o ano para pesquisa:");
        var year = scanner.nextInt();
        scanner.nextLine();
        
        List<Author> authors = authorRepository.findAuthorsAliveInYear(year);
        if (authors.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano " + year);
            return;
        }
        
        System.out.println("\nAutores vivos em " + year + ":");
        authors.forEach(System.out::println);
    }
    
    private void listBooksByLanguage() {
        System.out.println("""
            Digite o idioma para pesquisa:
            es - Espanhol
            en - Inglês
            fr - Francês
            pt - Português
            """);
        var language = scanner.nextLine().toLowerCase();
        
        List<Book> books = bookRepository.findByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma " + language);
            return;
        }
        
        System.out.println("\nLivros no idioma " + language + ":");
        books.forEach(System.out::println);
    }
    
    private void listTop10Books() {
        List<Book> books = bookRepository.findTop10ByOrderByDownloadCountDesc();
        if (books.isEmpty()) {
            System.out.println("Nenhum livro registrado no banco de dados");
            return;
        }
        
        System.out.println("\nTop 10 livros mais baixados:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i+1) + ". " + books.get(i).getTitle() + 
                " - Downloads: " + books.get(i).getDownloadCount());
        }
    }
}