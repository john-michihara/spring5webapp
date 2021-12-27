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

        System.out.println("Started in Bootstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "234234");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Number of Books: " + bookRepository.count());

        Publisher randomHouse = new Publisher("Random House", "123 Aloha St", "Honolulu", "HI", "96816");
        Publisher oxford = new Publisher("Oxford", "123 Cherry Lane", "London", "UK", "12345");

        publisherRepository.save(randomHouse);
        publisherRepository.save(oxford);

        ddd.setPublisher(randomHouse);
        randomHouse.getBooks().add(ddd);

        noEJB.setPublisher(randomHouse);
        randomHouse.getBooks().add(noEJB);

        bookRepository.save(ddd);
        bookRepository.save(noEJB);
        publisherRepository.save(randomHouse);

        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Random House Number of Books " + randomHouse.getBooks().size());
    }
}
