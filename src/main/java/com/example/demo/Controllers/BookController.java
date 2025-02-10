package com.example.demo.Controllers;

import com.example.demo.Services.LibraryService;
import com.example.demo.models.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final LibraryService libraryService;

    @Autowired
    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<Book> getAvailableBooks() {
        return libraryService.getAvailableBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody @Valid Book book) {
        return libraryService.addBook(book);
    }

    @PostMapping("/rent")
    @ResponseStatus(HttpStatus.OK)
    public String rentBook(@RequestParam String email, @RequestParam String title) {
        return libraryService.rentBook(email, title);
    }

    @PostMapping("/return")
    @ResponseStatus(HttpStatus.OK)
    public String returnBook(@RequestParam String email, @RequestParam String title) {
        return libraryService.returnBook(email, title);
    }
}
