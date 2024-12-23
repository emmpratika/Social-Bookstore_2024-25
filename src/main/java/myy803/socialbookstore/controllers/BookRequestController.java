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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping("/user/delete_book_request")
    public String deleteBookRequest(@RequestParam("selected_request_id") int bookId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        bookRequestService.removeBookRequest(bookId, username);

        System.err.println("Delete Book Request for book id: " + bookId);

        //return "redirect:/user/dashboard";
        return "/user/book_requests";
    }

    @RequestMapping("/user/accept_request")
    public String acceptRequest(@RequestParam("selected_user") String username, @RequestParam("book_id") int bookId, Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String giverUsername = authentication.getName();
        bookRequestService.acceptBookRequest(bookId, username, giverUsername);
        String debugMessage = "Selected user: " + username + " for book id: " + bookId + "from the: " + giverUsername;
        redirectAttributes.addFlashAttribute("debugMessage", debugMessage);
        redirectAttributes.addFlashAttribute("message", "Request accepted");
        //for model attrubute we need maybe a return method
        //model attribute for notification

        //for service layer
        //two users -> selected that requests the book and the one that accepts the request

        //store some notification or verification method
        //that this verification we will get with a return method in order to
        //call it in a model attribute to render in the front.

        System.err.println("Selected user: " + username + " for book id: " + bookId);

        return "redirect:/user/dashboard";
    }
}
