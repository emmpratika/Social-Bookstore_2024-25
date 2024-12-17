package myy803.socialbookstore.controllers;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.services.BookRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookRequestController {

    @Autowired
    private BookRequestService bookRequestService;

    @RequestMapping("/user/request_book")
    public String request(@RequestParam("selected_book_id") int bookId, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        bookRequestService.requestBook(bookId, username);

        return "redirect:/user/dashboard";
    }

    @RequestMapping("/user/requests")
    public String showUserBookRequests(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<BookDto> bookRequests = bookRequestService.getUserBookRequests(username);
        model.addAttribute("requests", bookRequests);
        return "/user/book_requests";
    }

    @RequestMapping("/user/book_requesting_users")
    public String showRequestingUsersForBookOffer(@RequestParam("selected_offer_id") int bookId, Model model){

        List<UserProfileDto> requestingUsers = bookRequestService.getPendingRequests(bookId);
        model.addAttribute("requesting_users", requestingUsers);
        model.addAttribute("book_id", bookId);
        return "/user/requesting_users";
    }
}
