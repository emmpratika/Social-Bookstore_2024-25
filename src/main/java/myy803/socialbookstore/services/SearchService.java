package myy803.socialbookstore.services;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {
    public List<BookDto> searchBooks(SearchDto searchDto);
}
