package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookOfferService {
    public List<BookDto> retrieveBookOffers(String username);
    public void saveBookOffer(BookDto bookDto, String username);
    //public void removeBookOffer(int bookId, String username);
    public List<BookCategory> getCategories();
}
