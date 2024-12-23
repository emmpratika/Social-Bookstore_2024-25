package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.mappers.BookAuthorMapper;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookOfferServiceImpl implements BookOfferService {


    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private BookAuthorMapper bookAuthorMapper;

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Autowired
    private BookMapper bookMapper;






    public List<BookDto> retrieveBookOffers(String username){
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfile userProfile = optUserProfile.get();
        List<BookDto> bookOffersDtos = userProfile.buildBookOffersDtos();
        return bookOffersDtos;
    }


    public void saveBookOffer(BookDto bookDto, String username)  {

        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfile userProfile = optUserProfile.get();

        //conversion from bookDto to Book
        Book aBook = bookDto.buildBookOffer(bookAuthorMapper, bookCategoryMapper);
        userProfile.addBookOffer(aBook);
        userProfileMapper.save(userProfile);

    }

    public List<BookCategory> getCategories(){
        return bookCategoryMapper.findAll();
    }

    public void removeBookOffer(int bookId, String username)  {
        //Retrieve User Profile
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        if (optUserProfile.isEmpty()) {
            throw new RuntimeException("User profile not found");
        }
        UserProfile userProfile = optUserProfile.get();

        //Retrieve Book
        Optional<Book> optSelectedBook = bookMapper.findById(bookId);
        if (optSelectedBook.isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        Book selectedBook = optSelectedBook.get();

        userProfile.removeBookOfer(selectedBook);
        userProfileMapper.save(userProfile);
        System.out.println(userProfile.getBookOffers());
    }
}
