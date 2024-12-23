package myy803.socialbookstore.datamodel.recomstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RecommendationsFactory {

	private final Map<String, RecommendationsStrategy> strategies = new HashMap<>();

//	@Autowired
//	private FavouriteCategoriesStrategy favouriteCategoriesStrategy;
//	@Autowired
//	private FavouriteAuthorsStrategy favouriteAuthorsStrategy;
//	@Autowired
//	private FavouriteCategoriesAndAuthorsStrategy favouriteCategoriesAndAuthorsStrategy;
//	@Autowired
//	private PopularBooksStrategy popularBooksStrategy;

	@Autowired
	public RecommendationsFactory(
			FavouriteCategoriesStrategy favouriteCategoriesStrategy,
			FavouriteAuthorsStrategy favouriteAuthorsStrategy,
			FavouriteCategoriesAndAuthorsStrategy favouriteCategoriesAndAuthorsStrategy,
			PopularBooksStrategy popularBooksStrategy) {

		strategies.put("Favourite Categories", favouriteCategoriesStrategy);
		strategies.put("Favourite Authors", favouriteAuthorsStrategy);
		strategies.put("Favourite Categories and Authors", favouriteCategoriesAndAuthorsStrategy);
		strategies.put("Popular Books", popularBooksStrategy);
	}


//
//	public RecommendationsStrategy create(String recomStrategy) {
//		if(recomStrategy.equals("Favourite Categories"))
//			return favouriteCategoriesStrategy;
//		else
//			if(recomStrategy.equals("Favourite Authors"))
//				return favouriteAuthorsStrategy;
//			else
//				return favouriteCategoriesAndAuthorsStrategy;
//	}

	public RecommendationsStrategy create(String recomStrategy) {
		RecommendationsStrategy strategy = strategies.get(recomStrategy);
		if (strategy == null) {
			throw new IllegalArgumentException("Unknown strategy: " + recomStrategy);
		}
		return strategy;
	}

}
