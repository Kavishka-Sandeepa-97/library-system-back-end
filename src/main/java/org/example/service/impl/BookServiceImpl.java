package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Book;
import org.example.entity.BookEntity;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;

    ModelMapper modelMapp ;
    @Bean
    public void setUp(){
        this.modelMapp=new ModelMapper();
    }
    @Override
    public void addBook(Book book) {
        BookEntity bookEntity = modelMapp.map(book, BookEntity.class);
        bookRepository.save(bookEntity);
    }

    @Override
    public Iterable<BookEntity> getBooks() {
        Iterable<BookEntity> all = bookRepository.findAll();
        return all;
    }

    @Override
    public boolean deleteBookById(Long id) {
        if (bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Book getBook(Long id) {

        Optional<BookEntity> byId = bookRepository.findById(id);
        return modelMapp.map(byId,Book.class);
    }
}
