package myy803.socialbookstore.datamodel.searchstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchFactory {

	@Autowired
	private ExactSearchStrategy exactSearchStrategy;
	@Autowired
	private ApproximateSearchStrategy approximateSearchStrategy;
	@Autowired
	private CategorySearchStrategy categorySearchStrategy;
	
//	public SearchStrategy create(String searchStrategy) {
//		if(searchStrategy.equals("Exact"))
//			return exactSearchStrategy;
//		else
//			return approximateSearchStrategy;
//	}

	public SearchStrategy create(String searchStrategy) {
		switch (searchStrategy) {
			case "Exact":
				return exactSearchStrategy;
			case "Approximate":
				return approximateSearchStrategy;
			case "Category":
				return categorySearchStrategy;
			default:
				throw new IllegalArgumentException("Unknown search strategy: " + searchStrategy);
		}
	}
}
