package cocktailsearch;

import cocktailsearch.database.Cocktail;
import cocktailsearch.database.Ingredient;
import cocktailsearch.exceptions.MissingParameterException;
import cocktailsearch.mocks.CocktailRepositoryMock;
import cocktailsearch.services.SearchCocktailServiceImpl;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SearchCocktailServiceTest {

    private final CocktailRepositoryMock cocktailRepository = new CocktailRepositoryMock();

    private final SearchCocktailService searchCocktailService = new SearchCocktailServiceImpl(cocktailRepository);

    @Test
    void findAll_expectAllDbRecordsToBePresentInResponse() {
        cocktailRepository.setFindAllResponse(Arrays.asList(new Cocktail("Mohito"), new Cocktail("Long Island"), new Cocktail("Espresso Martini")));
        assertEquals(3, searchCocktailService.findAll().size());
    }

    @Test
    void findAllWhenDbIsEmpty_expectEmptyList() {
        cocktailRepository.setFindByNameToReturn(null);
        assertTrue(searchCocktailService.findAll().isEmpty());
    }

    @Test
    void findByName_expectCocktailWithGivenName() {
        cocktailRepository.setFindByNameToReturn(new Cocktail("Long Island"));
        assertEquals("Long Island", searchCocktailService.findByName("Long Island").getName());
    }

    @Test
    void findByNameWhenThereIsNotCocktailWithGivenName_expectNull() {
        cocktailRepository.setFindByNameToReturn(null);
        assertNull(searchCocktailService.findByName("Long Island"));
    }

    @Test
    void findByNameWhenInputIsNull_expectException() {
        final MissingParameterException exception = assertThrows(MissingParameterException.class, () -> searchCocktailService.findByName(null));
        assertEquals("Missing parameter: 'name'", exception.getMessage());
    }


    @Test
    void findByIngredients_expectAllCocktailsInResult() {
        cocktailRepository.setFindByIngredientResponse(Arrays.asList(new Cocktail("Long Island",
                Arrays.asList(new Ingredient("Apple"), new Ingredient("Orange")))));
        assertEquals(1, searchCocktailService.findByIngredients("Apple").size());
    }

    @Test
    void findByIngredientsWhenInputIsNull_expectException() {
        final MissingParameterException exception = assertThrows(MissingParameterException.class, () -> searchCocktailService.findByIngredients(null));
        assertEquals("Missing parameter: 'ingredients'", exception.getMessage());
    }

    @Test
    void findByIngredientsWhenInputIsEmptyArr_expectException() {
        final MissingParameterException exception = assertThrows(MissingParameterException.class, searchCocktailService::findByIngredients);
        assertEquals("Missing parameter: 'ingredients'", exception.getMessage());
    }

}