package OkkeSeller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import OkkeSeller.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long>{

	List<Book> findByTitleContaining(String keyword);


}
