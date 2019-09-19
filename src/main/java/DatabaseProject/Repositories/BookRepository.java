package DatabaseProject.Repositories;

import DatabaseProject.Models.BookModel;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Book;
import java.util.List;

public interface BookRepository extends CrudRepository<BookModel, Long> {
    List<Book> findByTitle(String title);
}
