package myy803.socialbookstore.controllers;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/user/search")
    public String showSearchForm(Model model){
        SearchDto searchDto = new SearchDto();
        model.addAttribute("searchDto", searchDto);

        return "user/search_form";
    }

    @RequestMapping("/user/search_offer")
    public String search(@ModelAttribute("searchDto") SearchDto searchDto, Model model){

        List<BookDto> bookDtos = searchService.searchBooks(searchDto);
        model.addAttribute("books", bookDtos);


        return "user/search_results";
    }
}
