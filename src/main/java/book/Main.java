package book;

import book.model.Book;
import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.PersistenceModule;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        BookDao bookDao = injector.getInstance(BookDao.class);
        BookGenerator bookGen = new BookGenerator();
        int n = args.length == 1 ? Integer.parseInt(args[0]) : 1000;
        for (int i = 0; i < n; i++) {
            Book book = bookGen.CreateBook();
            bookDao.persist(book);
        }
        bookDao.findAll()
                .stream()
                .forEach(System.out::println);
    }
}
