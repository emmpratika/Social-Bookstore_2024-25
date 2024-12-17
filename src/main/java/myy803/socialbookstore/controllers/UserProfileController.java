package myy803.socialbookstore.controllers;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookAuthorMapper;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
import myy803.socialbookstore.services.UserProfileService;
import myy803.socialbookstore.services.UserProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserProfileController {


//    @Autowired
//    private UserProfileMapper userProfileMapper;

//    @Autowired
//    private BookAuthorMapper bookAuthorMapper;

//    @Autowired
//    private BookCategoryMapper bookCategoryMapper;

    @Autowired
    private UserProfileService userProfileService; //check again later


    @RequestMapping("/user/profile")
    public String retrieveProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.err.println("Logged use: " + username);
        //List<BookCategory> categories = bookCategoryMapper.findAll();

        // //check later



        UserProfileDto userProfileDto = userProfileService.retrieveProfile(username);
        List<BookCategory> categories = userProfileService.getBookCategories();
        model.addAttribute("profile", userProfileDto);
        model.addAttribute("categories", categories);

        return "user/profile";

    }

    @RequestMapping("/user/save_profile")
    public String saveProfile(@ModelAttribute("userProfile") UserProfileDto userProfileDto, Model model){
        System.err.println(userProfileDto.toString());

        userProfileService.saveUserProfile(userProfileDto);

        model.addAttribute("userProfile", userProfileDto);
        return "user/dashboard";

    }

}
