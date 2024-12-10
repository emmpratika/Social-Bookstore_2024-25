

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

//import myy803.socialbookstore.datamodel.searchstrategies.ApproximateSearchStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.datamodel.searchstrategies.*;

public class ApproximateSearchStrategyTest {

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private ApproximateSearchStrategy approximateSearchStrategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearch_withMatchingTitleAndAuthor() {
        // Mock input and dependencies
        SearchDto searchDto = new SearchDto();
        searchDto.setTitle("Spring");
        searchDto.setAuthors("John Doe");

        Book book1 = mock(Book.class);
        when(book1.buildDto()).thenReturn(new BookDto());
        when(book1.authorsListIncludes("John Doe")).thenReturn(true);

        when(bookMapper.findByTitleContaining("Spring"))
                .thenReturn(Arrays.asList(book1));

        // Call the method
        List<BookDto> result = approximateSearchStrategy.search(searchDto, bookMapper);

        // Assertions
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookMapper).findByTitleContaining("Spring");
        verify(book1).authorsListIncludes("John Doe");
    }

    @Test
    public void testSearch_withNoAuthorsMatch() {
        SearchDto searchDto = new SearchDto();
        searchDto.setTitle("Spring");
        searchDto.setAuthors("Jane Doe");

        Book book1 = mock(Book.class);
        when(book1.authorsListIncludes("Jane Doe")).thenReturn(false);

        when(bookMapper.findByTitleContaining("Spring"))
                .thenReturn(Arrays.asList(book1));

        List<BookDto> result = approximateSearchStrategy.search(searchDto, bookMapper);

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(bookMapper).findByTitleContaining("Spring");
        verify(book1).authorsListIncludes("Jane Doe");
    }

    @Test
    public void testSearch_withNullTitle() {
        SearchDto searchDto = new SearchDto();
        searchDto.setTitle(null);

        List<BookDto> result = approximateSearchStrategy.search(searchDto, bookMapper);

        assertNotNull(result);
        assertEquals(0, result.size());
        verifyNoInteractions(bookMapper);
    }
}
