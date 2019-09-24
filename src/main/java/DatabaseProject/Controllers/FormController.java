package DatabaseProject.Controllers;

import DatabaseProject.Exceptions.BookNotFoundException;
import DatabaseProject.Models.BookModel;
import DatabaseProject.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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


    @PostMapping("/form")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute BookModel book, Model model){
        bookRepository.save(book);
        System.err.println(book.getId());
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

    @PutMapping("/update")
    public String updateBook(@RequestBody BookModel book, Model model) {
        System.err.println(book.getId());
        bookRepository.findById(book.getId()).orElseThrow(BookNotFoundException::new);
        bookRepository.save(book);
        System.out.println(bookRepository.findById(book.getId()));
        model.addAttribute("book", new BookModel());
        model.addAttribute("books", bookRepository.findAll());
        return "form";
    }
}
