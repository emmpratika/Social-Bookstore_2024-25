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

    @Autowired
    private BookOfferService bookOfferService;


    public void requestBook(int bookId, String username) {
        Optional<Book> requestedBook = bookMapper.findById(bookId);
        Optional<UserProfile> userProfile = userProfileMapper.findById(username);
        //requestedBook.get().addRequestingUser(userProfile.get());
        if (!requestedBook.get().hasThisUserProfile(userProfile.get())){
            requestedBook.get().addRequestingUser(userProfile.get());
        }


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

    public void removeBookRequest(int bookId, String username) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        if (optUserProfile.isEmpty()) {
            throw new RuntimeException("User profile not found");
        }
        UserProfile userProfile = optUserProfile.get();

        Optional<Book> optSelectedBook = bookMapper.findById(bookId);
        if (optSelectedBook.isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        Book requestedBook = optSelectedBook.get();

        //1. Remove the user from the requesting user list of the book
        //requestedBook.get().remove
        requestedBook.removeRequestingUser(userProfile);
        //1.1
        bookMapper.save(requestedBook);

        //2. update the user's list of requesting books
        //maybe doesnt need because of orphanremoval = TRUE

    }

    public void acceptBookRequest(int bookId, String requesterUsername, String giverUsername) {
        Optional<UserProfile> requesterUserProfile = userProfileMapper.findById(requesterUsername);
        Optional<UserProfile> giverUserProfile = userProfileMapper.findById(giverUsername);

        Optional<Book> requestedBook = bookMapper.findById(bookId);
        //1st way if requested book
        //if (getPendingRequests(bookId).g
        //2nd way use getPendingRequest above method
        List<UserProfileDto> requestersDtoList = getPendingRequests(bookId);
        for (UserProfileDto userProfileDto : requestersDtoList) {
            if (userProfileDto.getUsername().equals(requesterUsername)) {
                removeBookRequest(bookId, requesterUsername);
                bookOfferService.removeBookOffer(bookId, giverUsername);

            }
        }


    }
}
