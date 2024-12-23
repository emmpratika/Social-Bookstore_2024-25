package myy803.socialbookstore.services;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookRequestService {
    public void requestBook(int bookId, String username);
    public List<BookDto> getUserBookRequests(String username);
    public List<UserProfileDto> getPendingRequests(int bookId);
    public void removeBookRequest(int bookId, String username);
    public void acceptBookRequest(int bookId, String requesterUsername, String giverUsername);
}
