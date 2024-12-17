package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.searchstrategies.SearchFactory;
import myy803.socialbookstore.datamodel.searchstrategies.SearchStrategy;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchFactory searchFactory;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDto> searchBooks(SearchDto searchDto){

        SearchStrategy searchStrategy = searchFactory.create(searchDto.getSelectedStrategy());
        List<BookDto> bookDtos = searchStrategy.search(searchDto, bookMapper);
        return bookDtos;
    }
}
