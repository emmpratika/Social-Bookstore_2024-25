package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookRequestServiceImpl implements BookRequestService{

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;


    public void requestBook(int bookId, String username) {
        Optional<Book> requestedBook = bookMapper.findById(bookId);
        Optional<UserProfile> userProfile = userProfileMapper.findById(username);
        requestedBook.get().addRequestingUser(userProfile.get());
        bookMapper.save(requestedBook.get());
    }

    public List<BookDto> getUserBookRequests(String username){
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfile userProfile = optUserProfile.get();
        List<BookDto> bookRequestsDtos= userProfile.buildBookRequestsDtos();
        return bookRequestsDtos;
    }

    public List<UserProfileDto> getPendingRequests(int bookId){
        Optional<Book> book = bookMapper.findById(bookId);
        List<UserProfileDto> requestingUsers = book.get().getRequestingUserProfileDtos();
        return requestingUsers;
    }
}
