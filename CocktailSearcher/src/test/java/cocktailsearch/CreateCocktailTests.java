package cocktailsearch;

import cocktailsearch.database.Cocktail;
import cocktailsearch.exceptions.MissingParameterException;
import cocktailsearch.services.CreateCocktailServiceImpl;
import cocktailsearch.mocks.CocktailRepositoryMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateCocktailTests {

    private static final String COCKTAIL_NAME = "Long Island";

    public static final String INGREDIENT_1 = "Gomme Syrup";

    public static final String INGREDIENT_2 = "Lemon juice";

    public static final String INGREDIENT_3 = "Gin";

    private final CreateCocktailService createCocktailService;

    private final CocktailRepositoryMock cocktailRepository = new CocktailRepositoryMock();

    public CreateCocktailTests() {
        this.createCocktailService = new CreateCocktailServiceImpl(cocktailRepository);
    }

    @Test
    public void missingName_expectException() {
        final MissingParameterException exception = assertThrows(MissingParameterException.class,
                () -> createCocktailService.createCocktail(null));
        assertEquals("Missing parameter: 'cocktailName'", exception.getMessage());
    }

    @Test
    void missingIngredients_expectException() {
        final MissingParameterException exception = assertThrows(MissingParameterException.class,
                () -> createCocktailService.createCocktail("Name"));
        assertEquals("Missing parameter: 'ingredients'", exception.getMessage());
    }

    @Test
    void validInput_expectResultWithProperValues() {
        final Cocktail cocktail = createCocktailService.createCocktail(COCKTAIL_NAME, INGREDIENT_1, INGREDIENT_2, INGREDIENT_3);
        assertEquals(COCKTAIL_NAME, cocktail.getName());
        assertNotNull(cocktail.getIngredients());
        assertEquals(3, cocktail.getIngredients().size());
        assertEquals(INGREDIENT_1, cocktail.getIngredients().get(0).getName());
        assertEquals(INGREDIENT_2, cocktail.getIngredients().get(1).getName());
        assertEquals(INGREDIENT_3, cocktail.getIngredients().get(2).getName());
        assertTrue(cocktailRepository.isSaveMethodCalled());
    }

}
