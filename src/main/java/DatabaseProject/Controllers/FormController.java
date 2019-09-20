package DatabaseProject.Controllers;

import DatabaseProject.Exceptions.BookIdMismatchException;
import DatabaseProject.Exceptions.BookNotFoundException;
import DatabaseProject.Models.BookModel;
import DatabaseProject.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class FormController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/form")
    public String formPage(Model model) {
        model.addAttribute("book", new BookModel());
        model.addAttribute("bookCount", bookRepository.count());
        model.addAttribute("books", bookRepository.findAll());
        return "form";
    }

    @GetMapping("/getBooks")
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

    @PostMapping("/form")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute BookModel book, Model model){
        bookRepository.save(book);
        model.addAttribute("book", new BookModel());
        model.addAttribute("books", bookRepository.findAll());
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String handleDelete(@PathVariable Long id) {
        delete(id);
        return "redirect:/form";
    }

    @DeleteMapping("/form/{id}")
    public String delete(@PathVariable Long id) {
        bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
        return "form";
    }

    @GetMapping("/update/{id}")
    public String handleUpdate(@PathVariable Long id, Model model) {
        System.err.println("Updating");
        return "redirect:/form";
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
