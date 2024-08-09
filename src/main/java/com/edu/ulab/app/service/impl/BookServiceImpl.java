package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {
    BookRepository repository;
    BookMapper mapper;

    @Override
    public BookDto createBook(BookDto bookDto) {
        var book = repository.save(mapper.bookDtoToBook(bookDto));
        bookDto.setId(book.getId());
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        var bookToUpdate = repository.findById(bookDto.getId())
                .orElseThrow(() -> new NotFoundException("Book with id " + bookDto.getId() + " not found"));
        bookToUpdate.setUserId(bookDto.getUserId());
        bookToUpdate.setTitle(bookDto.getTitle());
        bookToUpdate.setAuthor(bookDto.getAuthor());
        bookToUpdate.setPageCount(bookDto.getPageCount());
        return mapper.bookToBookDto(repository.save(bookToUpdate));
    }

    @Override
    public BookDto getBookById(Long id) {
        return repository.findById(id)
                .map(mapper::bookToBookDto)
                .orElseThrow(() -> new NotFoundException("Book with id " + id + " not found"));
    }

    @Override
    public void deleteBookById(Long id) {
        repository.deleteById(id);
    }
}
