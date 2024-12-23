package myy803.socialbookstore.mappers;

//import java.awt.print.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.socialbookstore.datamodel.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMapper extends JpaRepository<Book, Integer> {
	List<Book> findByTitle(String title);
	List<Book> findByTitleContaining(String title);
	//List<Book> findByCategory(String name);
	//List<Book> findTopBooksByPopularity(Pageable pageable);
	@Query("SELECT b FROM Book b ORDER BY b.popularity DESC")
	List<Book> findTop10BooksByPopularity();
}
