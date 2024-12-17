package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.UserProfileDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserProfileService {
    public UserProfileDto retrieveProfile(String username);
    public void saveUserProfile(UserProfileDto profile);
    public List<BookCategory> getBookCategories();
}
