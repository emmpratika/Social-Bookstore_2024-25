package myy803.socialbookstore.datamodel.searchstrategies;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.mappers.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategorySearchStrategy extends AbstractSearchStrategy{

    @Autowired
    protected BookMapper bookMapper;

    @Autowired
    protected BookCategoryMapper bookCategoryMapper;

    @Override
    protected List<Book> makeInitialListOfBooks(SearchDto searchDto){
//        List<BookCategor> books = bookCategoryMapper.findByName(searchDto.getCategory());
//        return books;
        if (searchDto.getCategory() == null || searchDto.getCategory().isEmpty()) {
            return new ArrayList<>(); // No category specified, return empty list
        }

        // Fetch the category by name
        List<BookCategory> categories = bookCategoryMapper.findByName(searchDto.getCategory());
        if (categories.isEmpty()) {
            return new ArrayList<>(); // Category not found
        }

        // Get books from the first matching category
        return categories.get(0).getBookOffers();
    }


    @Override
    protected boolean checkIfAuthorsMatch(SearchDto searchDto, Book book) {
        return true; //this abstract method isn't needed for this category

    }
}
