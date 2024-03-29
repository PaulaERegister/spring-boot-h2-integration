package DatabaseProject.Controllers;

import DatabaseProject.Exceptions.BookIdMismatchException;
import DatabaseProject.Exceptions.BookNotFoundException;
import DatabaseProject.Models.BookModel;
import DatabaseProject.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    public BookRepository getBookRepository() {
        return bookRepository;
    }


//    public BookModel storeFile(){
//        String filename = StringUtils.cleanPath(file.getOriginalFilename());
//        try {
//            if(filename.contains("..")){
//            }
//            BookModel book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
//            book.setData(file.getBytes());
//            bookRepository.save(book);
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
    @GetMapping
    public Iterable findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public BookModel findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookModel create(@ModelAttribute BookModel book){
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public BookModel updateBook(@RequestBody BookModel book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException("id mismatch", new Throwable());
        }
        bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }
}
