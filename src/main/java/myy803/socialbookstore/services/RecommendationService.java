package myy803.socialbookstore.services;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendationService {

    public List<BookDto> recommendBooks(RecommendationsDto recomDto, String username);
}
