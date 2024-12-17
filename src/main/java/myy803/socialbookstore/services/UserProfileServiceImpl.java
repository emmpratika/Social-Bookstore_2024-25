package myy803.socialbookstore.services;


//import myy803.socialbookstore.controllers.BookRequestController;
import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookAuthorMapper;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {


    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private BookAuthorMapper bookAuthorMapper;

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    public UserProfileDto retrieveProfile(String username){
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfile userProfile = null;
        UserProfileDto userProfileDto = null;
        if (optUserProfile.isPresent()) {
            return optUserProfile.get().buildProfileDto();

        }else {
            userProfileDto = new UserProfileDto();
            userProfileDto.setUsername(username);
            return userProfileDto;
        }
        }



    public void saveUserProfile(UserProfileDto userProfileDto){
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(userProfileDto.getUsername());
        System.out.println(userProfileDto.getUsername());
        UserProfile userProfile = null;

        if (optUserProfile.isPresent()) {
            userProfile = optUserProfile.get();
        }else {
            userProfile = new UserProfile();
        }
        userProfileDto.buildUserProfile(userProfile, bookAuthorMapper, bookCategoryMapper);
        userProfileMapper.save(userProfile);
    }

    public List<BookCategory> getBookCategories(){
        return bookCategoryMapper.findAll();
    }
}
