package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.recomstrategies.RecommendationsFactory;
import myy803.socialbookstore.datamodel.recomstrategies.RecommendationsStrategy;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationsFactory recommendationsFactory;

    public List<BookDto> recommendBooks(RecommendationsDto recomDto, String username){
        RecommendationsStrategy recommendationsStrategy = recommendationsFactory.create(recomDto.getSelectedStrategy());
        List<BookDto> bookDtos = recommendationsStrategy.recommend(username);

        return bookDtos;


    }
}
