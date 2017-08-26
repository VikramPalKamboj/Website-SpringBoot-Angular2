package OkkeSeller.repository.resources;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import OkkeSeller.Service.BookService;
import OkkeSeller.domain.Book;


@RestController
@RequestMapping("/book")

public class BookResource {
	
	@Autowired 
	private BookService bookService;

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Book addBookPost(@RequestBody Book book){
		
		return bookService.Save(book);
		
	}
	
	@RequestMapping(value="/add/image", method=RequestMethod.POST)
	public ResponseEntity upload(
			@RequestParam("id") Long id, 
			HttpServletResponse response, HttpServletRequest request
			){
			
		
		try {
			MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
			Iterator<String> it=multipartRequest.getFileNames();
			MultipartFile multipart=multipartRequest.getFile(it.next());
			String fileName=id+".png";
			String imageName = fileName;
			
			byte[] bytes=multipart.getBytes();
			BufferedOutputStream stream= new BufferedOutputStream(new FileOutputStream("src/main/resources/static/image/book/"+fileName));;
			
			stream.write(bytes);
			stream.close();
			return new ResponseEntity("upload success", HttpStatus.OK);
					
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload fialed", HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@RequestMapping(value="/update/image", method=RequestMethod.POST)
	public ResponseEntity updateImagePost(
			@RequestParam("id") Long id, 
			HttpServletResponse response, HttpServletRequest request
			){
			
		
		try {
			MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
			Iterator<String> it=multipartRequest.getFileNames();
			MultipartFile multipart=multipartRequest.getFile(it.next());
			String fileName=id+".png";
			String imageName = fileName;
			
			Files.delete(Paths.get("src/main/resources/static/image/book/"+fileName));
			
			byte[] bytes=multipart.getBytes();
			BufferedOutputStream stream= new BufferedOutputStream(new FileOutputStream("src/main/resources/static/image/book/"+fileName));
			
			stream.write(bytes);
			stream.close();
			return new ResponseEntity("upload success", HttpStatus.OK);
					
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload fialed", HttpStatus.BAD_REQUEST);
		}
		
		
	}	
	
	@RequestMapping("/bookList")
	public List<Book> getBookList(){
		return bookService.findAll();
	}
	
	@RequestMapping("/{id}")
	public Book getBook(@PathVariable("id") Long id){
		Book book=bookService.findOne(id);
		return book;
		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Book UpdateBookPost(@RequestBody Book book){
		return bookService.Save(book);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ResponseEntity remove(
			@RequestBody String id
			) throws IOException {
		bookService.removeOne(Long.parseLong(id));
		String fileName=id+".png";		
		Files.delete(Paths.get("src/main/resources/static/image/book/"+fileName));
		return new ResponseEntity("remove successfully", HttpStatus.OK);
	}
}
