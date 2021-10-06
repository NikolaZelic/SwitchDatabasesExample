package cocktailsearch;

import cocktailsearch.database.Cocktail;
import cocktailsearch.database.Ingredient;
import cocktailsearch.exceptions.MissingParameterException;
import cocktailsearch.exceptions.NotExitingCocktailException;
import cocktailsearch.services.EditCocktailServiceImpl;
import cocktailsearch.mocks.CocktailRepositoryMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class EditCocktailTests {

    public static final String NOT_EXISTING_COCKTAIL_NAME = "NotExistingCocktail";

    public static final String COCKTAIL_NAME = "Long Island";

    public static final String NEW_COCKTAIL_NAME = "Boosted Long Island";

    public static final String INGREDIENT_1 = "Gomme Syrup";

    public static final String INGREDIENT_2 = "Lemon juice";

    public static final String INGREDIENT_3 = "Gin";

    private final CocktailRepositoryMock cocktailRepository = new CocktailRepositoryMock();

    private final EditCocktailService editCocktailService;

    public EditCocktailTests() {
        editCocktailService = new EditCocktailServiceImpl(cocktailRepository);
    }

    @AfterEach
    void tearDown() {
        cocktailRepository.saveMethodCallRefresh();
    }

    @Test
    void editNotExistingCocktail_expectException() {
        cocktailRepository.setFindByNameToReturn(null);
        final NotExitingCocktailException exception = assertThrows(NotExitingCocktailException.class,
                () -> editCocktailService.editCocktail(NOT_EXISTING_COCKTAIL_NAME, NEW_COCKTAIL_NAME, INGREDIENT_1));
        assertEquals("Cocktail with name: '"+NOT_EXISTING_COCKTAIL_NAME+"' doesn't exist", exception.getMessage());
    }

    @Test
    void editCocktailWithoutIngredients_expectException() {
        final MissingParameterException exception = assertThrows(MissingParameterException.class,
                () -> editCocktailService.editCocktail(COCKTAIL_NAME, null));
        assertEquals("Missing parameter: 'ingredients'", exception.getMessage());
    }

    @Test
    void editCocktailName_expectNewCocktailName() {
        cocktailRepository.setFindByNameToReturn(new Cocktail(COCKTAIL_NAME, Collections.singletonList(new Ingredient(INGREDIENT_1))));
        final Cocktail cocktail = editCocktailService.editCocktail(COCKTAIL_NAME, NEW_COCKTAIL_NAME, INGREDIENT_1);
        assertTrue(cocktailRepository.isSaveMethodCalled());
        assertEquals(NEW_COCKTAIL_NAME, cocktail.getName());
    }

    @Test
    void addIngredient_expectNewIngredient() {
        cocktailRepository.setFindByNameToReturn(new Cocktail(COCKTAIL_NAME, Arrays.asList(new Ingredient(INGREDIENT_1), new Ingredient(INGREDIENT_2))));
        final Cocktail cocktail = editCocktailService.editCocktail(COCKTAIL_NAME, null, INGREDIENT_1, INGREDIENT_2, INGREDIENT_3);
        assertEquals(3, cocktail.getIngredients().size());
    }

    @Test
    void removeIngredient_expectIngredientToBeRemoved() {
        cocktailRepository.setFindByNameToReturn(new Cocktail(COCKTAIL_NAME, Arrays.asList(new Ingredient(INGREDIENT_1), new Ingredient(INGREDIENT_2))));
        final Cocktail cocktail = editCocktailService.editCocktail(COCKTAIL_NAME, null, INGREDIENT_1);
        assertEquals(1, cocktail.getIngredients().size());
    }

    @Test
    void editIngredientName_expectNewIngredientName() {
        cocktailRepository.setFindByNameToReturn(new Cocktail(COCKTAIL_NAME, Arrays.asList(new Ingredient(INGREDIENT_1), new Ingredient(INGREDIENT_2))));
        final Cocktail cocktail = editCocktailService.editCocktail(COCKTAIL_NAME, null, INGREDIENT_1, INGREDIENT_3);
        assertEquals(2, cocktail.getIngredients().size());
        assertEquals(INGREDIENT_3, cocktail.getIngredients().get(1).getName());
    }

    @Test
    void ingredientsNotChanged_expectSameIngredients() {
        cocktailRepository.setFindByNameToReturn(new Cocktail(COCKTAIL_NAME, Arrays.asList(new Ingredient(INGREDIENT_1), new Ingredient(INGREDIENT_2))));
        final Cocktail cocktail = editCocktailService.editCocktail(COCKTAIL_NAME, null, INGREDIENT_1, INGREDIENT_2);
        assertEquals(2, cocktail.getIngredients().size());
        assertEquals(INGREDIENT_1, cocktail.getIngredients().get(0).getName());
        assertEquals(INGREDIENT_2, cocktail.getIngredients().get(1).getName());
    }

}
