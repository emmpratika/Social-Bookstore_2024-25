package myy803.socialbookstore.datamodel.recomstrategies;

import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PopularBooksStrategy extends AbstractRecommendationStrategy{


    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    protected List<BookDto> retrieveRecommendedBooks(UserProfile userProfile) {
        //return the first 3 more popular books based on their requests
//        return bookMapper.findTopBooksByPopularity(PageRequest.of(0, 10))
//                .stream()
//                .map(book -> book.buildDto())
//                .collect(Collectors.toList());
        return bookMapper.findTop10BooksByPopularity()
                .stream()
                .map(Book::buildDto)
                .collect(Collectors.toList());
    }
}
