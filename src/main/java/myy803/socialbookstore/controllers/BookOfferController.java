package myy803.socialbookstore.controllers;


import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.services.BookOfferService;
import myy803.socialbookstore.services.BookOfferServiceImpl;
import myy803.socialbookstore.formsdata.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookOfferController {

    @Autowired
    private BookOfferService bookOfferService;

    @Autowired
    private BookCategoryMapper bookCategoryMapper;


    @RequestMapping("/user/offers")
    public String listBookOffers(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<BookDto> bookOffers = bookOfferService.retrieveBookOffers(username);

        model.addAttribute("offers" , bookOffers);

        return "user/offers";
    }


    @RequestMapping("/user/show_offer_form")
    public String showOfferForm(Model model){
        List<BookCategory> categories = bookOfferService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("offer", new BookDto());

        return "user/offer-form";
    }

    @RequestMapping("/user/save_offer")
    public String saveOffer(@ModelAttribute("offer") BookDto bookOfferDto, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        bookOfferService.saveBookOffer(bookOfferDto, username);

        return "redirect:/user/offers";
    }

    @RequestMapping("/user/delete_book_offer")
    public String deleteBookOffer(@RequestParam("selected_offer_id") int bookId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        bookOfferService.removeBookOffer(bookId, username);
        System.err.println("Delete Book Offer for book id: " + bookId);

        //return "redirect:/user/dashboard";
        return "redirect:/user/offers";
        //maybe return to user/ipolipes offers
    }


}
