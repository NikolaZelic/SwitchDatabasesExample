package cocktailsearch;

import cocktailsearch.database.Cocktail;
import cocktailsearch.exceptions.MissingParameterException;
import cocktailsearch.exceptions.NotExitingCocktailException;
import cocktailsearch.services.DeleteCocktailServiceImpl;
import cocktailsearch.mocks.CocktailRepositoryMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCocktailTests {

    public static final String NOT_EXISTING_COCKTAIL_NAME = "notExistingCocktailName";

    public static final String COCKTAIL_NAME = "cocktailName";

    private final CocktailRepositoryMock cocktailRepository = new CocktailRepositoryMock();

    private final DeleteCocktailService deleteCocktailService;

    public DeleteCocktailTests() {
        deleteCocktailService = new DeleteCocktailServiceImpl(cocktailRepository);
    }

    @Test
    void notProvideName_expectException() {
        final MissingParameterException exception = assertThrows(MissingParameterException.class,
                () -> deleteCocktailService.deleteCocktail(null));
        assertEquals("Missing parameter: 'cocktailName'", exception.getMessage());
    }

    @Test
    void deleteNotExistingCocktail_expectException() {
        cocktailRepository.setFindByNameToReturn(null);
        final NotExitingCocktailException exception = assertThrows(NotExitingCocktailException.class,
                () -> deleteCocktailService.deleteCocktail(NOT_EXISTING_COCKTAIL_NAME));
        assertEquals("Cocktail with name: 'notExistingCocktailName' doesn't exist", exception.getMessage());
    }

    @Test
    void deleteCocktail_expectRepositoryMethodToBeCalled() {
        cocktailRepository.setFindByNameToReturn(new Cocktail(COCKTAIL_NAME));
        deleteCocktailService.deleteCocktail(COCKTAIL_NAME);
        assertTrue(cocktailRepository.isDeleteMethodCalled());
    }

}
