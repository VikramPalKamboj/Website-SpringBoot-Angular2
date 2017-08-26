package OkkeSeller.Service;

import java.util.List;

import OkkeSeller.domain.Book;

public interface BookService {
	
	List<Book> findAll();
	Book findOne(Long id);
	Book Save(Book book);
	List<Book> blurrySearch(String title);
	
	void removeOne(Long Id);
	

}
