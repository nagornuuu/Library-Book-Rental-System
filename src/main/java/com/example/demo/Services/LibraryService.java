package com.example.demo.Services;

import com.example.demo.models.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    private final List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public String rentBook(String email, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                return "Book rented successfully by " + email;
            }
        }
        return "Book is not available for rent";
    }

    public String returnBook(String email, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {
                book.setAvailable(true);
                return "Book returned successfully by " + email;
            }
        }
        return "No record found for this book and email";
    }

    public Book addBook(Book book) {
        book.setId(nextId++);
        book.setAvailable(true);
        books.add(book);
        return book;
    }
}
