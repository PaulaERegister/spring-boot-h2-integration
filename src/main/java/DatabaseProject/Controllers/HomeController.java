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
public class HomeController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public String formPage(Model model) {
        model.addAttribute("book", new BookModel());
        model.addAttribute("bookCount", bookRepository.count());
        model.addAttribute("books", bookRepository.findAll());
        return "home";
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute BookModel book, Model model){
        bookRepository.save(book);
        model.addAttribute("book", new BookModel());
        model.addAttribute("books", bookRepository.findAll());
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String handleDelete(@PathVariable Long id) {
        delete(id);
        return "redirect:/home";
    }

    @DeleteMapping("/form/{id}")
    public String delete(@PathVariable Long id) {
        bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
        return "home";
    }

    @PutMapping("/update")
    public String updateBook(@RequestBody BookModel book, Model model) {
        bookRepository.findById(book.getId()).orElseThrow(BookNotFoundException::new);
        bookRepository.save(book);
        model.addAttribute("book", new BookModel());
        model.addAttribute("books", bookRepository.findAll());
        return "home";
    }
}
