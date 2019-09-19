//package DatabaseProject;
//
//import DatabaseProject.Models.BookModel;
////import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
////import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
//import org.apache.catalina.connector.Response;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = { Application.class }, webEnvironment
//        = SpringBootTest.WebEnvironment.DEFINED_PORT)
//public class SpringBootBootstrapLiveTest {
//
//    private static final String API_ROOT
//            = "http://localhost:8081/api/books";
//
////    private BookModel createRandomBook() {
////        BookModel book = new BookModel();
////        book.setTitle(randomAlphabetic(10));
////        book.setAuthor(randomAlphabetic(15));
////        return book;
////    }
////
////    private String createBookAsUri(BookModel book) {
////        Response response = RestAssured.given()
////                .contentType(MediaType.APPLICATION_JSON_VALUE)
////                .body(book)
////                .post(API_ROOT);
////        return API_ROOT + "/" + response.jsonPath().get("id");
////    }
//}
