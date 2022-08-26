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
    public void run(String... args) throws Exception {
        Publisher prenticeHall = new Publisher();
        prenticeHall.setName("Prentice Hall");
        prenticeHall.setAddressLine1("One Lake Street");
        prenticeHall.setCity("Upper Saddle River");
        prenticeHall.setZipCode("7458");

        publisherRepository.save(prenticeHall);
        System.out.println("Number of publishers: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(prenticeHall);
        prenticeHall.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(prenticeHall);

        Author rob = new Author("Robert", "Martin");
        Book cc = new Book("Clean Code", "345345");

        rob.getBooks().add(cc);
        cc.getAuthors().add(rob);
        cc.setPublisher(prenticeHall);
        prenticeHall.getBooks().add(cc);

        authorRepository.save(rob);
        bookRepository.save(cc);
        publisherRepository.save(prenticeHall);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher Number of books: " + prenticeHall.getBooks().size());


    }
}
