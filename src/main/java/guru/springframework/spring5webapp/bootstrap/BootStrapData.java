package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("Started in bootstrap");
        Publisher publisher = new Publisher("publisherName", "publisherAddress", "publisherCity", "publisherPostalCode");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book someBook = new Book("fancyTitle", "isbnNumber");
        eric.getBooks().add(someBook);
        someBook.getAuthors().add(eric);
        publisher.getBooks().add(someBook);
        System.out.println("publisherRepository.count() = " + publisherRepository.count());
        someBook.setPublisher(publisher);

        authorRepository.save(eric);
        bookRepository.save(someBook);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Develpoment without EJB", "45123464215");
        noEJB.getAuthors().add(rod);
        rod.getBooks().add(noEJB);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);


        System.out.println("number of books = " + bookRepository.count());
        System.out.println("publisher.getBooks().size() = " + publisher.getBooks().size());

    }

}
