package OkkeSeller.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OkkeSeller.Service.BookService;
import OkkeSeller.domain.Book;
import OkkeSeller.repository.BookRepository;



@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	public BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		List<Book> bookList=(List<Book>)bookRepository.findAll();
		List<Book> activeBookList=new ArrayList<>();
		
		for (Book book:bookList) {
			if (book.isActive()) {
				activeBookList.add(book);
			}
		}
		return activeBookList;
	}

	@Override
	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}

	@Override
	public Book Save(Book book) {
	
		return bookRepository.save(book);
	}

	@Override
	public List<Book> blurrySearch(String keyword) {
		List<Book> bookList=bookRepository.findByTitleContaining(keyword);
		
        List<Book> activeBookList=new ArrayList<>();
		
		for (Book book:bookList) {
			if (book.isActive()) {
				activeBookList.add(book);
			}
		}
		return activeBookList;
	}

	@Override
	public void removeOne(Long id) {
		bookRepository.delete(id);
	}
	

}
