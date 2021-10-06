package inmemorydb;

import cocktailsearch.database.Cocktail;
import cocktailsearch.database.CocktailsRepository;
import cocktailsearch.database.Ingredient;
import cocktailsearch.exceptions.MissingParameterException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CocktailsRepositoryTest {

    private final CocktailsRepository cocktailsRepository = new CocktailsRepositoryImpl();

    @Test
    void save_expectSameCocktail() {
        Cocktail cocktail = new Cocktail("Long Island", Arrays.asList(new Ingredient("Apple"), new Ingredient("Orange"), new Ingredient("Blubbery")));
        assertEquals(cocktail, cocktailsRepository.save(cocktail));
    }

    @Test
    void saveWhenInputIsNull_expectException() {
        final MissingParameterException exception = assertThrows(MissingParameterException.class, () -> cocktailsRepository.save(null));
        assertEquals("Missing parameter: 'cocktail'", exception.getMessage());
    }

    @Test
    void findAllWhenTwoCocktailsAreSaved_expectTwoCocktailsToBeReturned() {
        Cocktail cocktail1 = new Cocktail("Long Island", Arrays.asList(new Ingredient("Apple"), new Ingredient("Orange"), new Ingredient("Blubbery")));
        Cocktail cocktail2 = new Cocktail("Espresso Martini", Arrays.asList(new Ingredient("Espresso"), new Ingredient("Martini")));
        cocktailsRepository.save(cocktail1);
        cocktailsRepository.save(cocktail2);
        assertEquals(2, cocktailsRepository.findAll().size());
    }

    @Test
    void deleteCocktail_expectCocktailToMissFromResult() {
        Cocktail cocktail1 = new Cocktail("Long Island", Arrays.asList(new Ingredient("Apple"), new Ingredient("Orange"), new Ingredient("Blubbery")));
        Cocktail cocktail2 = new Cocktail("Espresso Martini", Arrays.asList(new Ingredient("Espresso"), new Ingredient("Martini")));
        cocktailsRepository.save(cocktail1);
        cocktailsRepository.save(cocktail2);
        cocktailsRepository.deleteCocktail(cocktail1);
        final List<Cocktail> result = cocktailsRepository.findAll();
        assertEquals(1, result.size());
        assertEquals(cocktail2, result.get(0));
    }

    @Test
    void deleteCocktailWhenInputIsNull_expectException() {
        final MissingParameterException exception = assertThrows(MissingParameterException.class, () -> cocktailsRepository.deleteCocktail(null));
        assertEquals("Missing parameter: 'cocktail'", exception.getMessage());
    }

    @Test
    void findByIngredientsWhenCocktailContainsAllIngredients_expectCocktailToBeReturned() {
        Cocktail cocktail1 = new Cocktail("Long Island", Arrays.asList(new Ingredient("Apple"), new Ingredient("Orange"), new Ingredient("Blubbery")));
        cocktailsRepository.save(cocktail1);
        final List<Cocktail> result = cocktailsRepository.findByIngredients("Apple", "Orange");
        assertEquals(1, result.size());
    }

    @Test
    void findByIngredientsWhenCocktailNotContainsAllIngredients_expectEmptyList() {
        Cocktail cocktail1 = new Cocktail("Long Island", Collections.singletonList(new Ingredient("Apple")));
        cocktailsRepository.save(cocktail1);
        assertTrue(cocktailsRepository.findByIngredients("Apple", "Orange").isEmpty());
    }

    @Test
    void findByIngredientsWhenCocktailNameIsUpperCaseAndSearchIsLowerCase_expectCocktailToBeReturned() {
        Cocktail cocktail1 = new Cocktail("Long Island", Collections.singletonList(new Ingredient("Apple")));
        cocktailsRepository.save(cocktail1);
        assertEquals(1, cocktailsRepository.findByIngredients("apple").size());
    }

    @Test
    void findByIngredientsWhenMoreCocktailsAreInDb_expectOnlyCocktailWithIngredients() {
        Cocktail cocktail1 = new Cocktail("Long Island", Arrays.asList(new Ingredient("Apple"), new Ingredient("Orange"), new Ingredient("Blubbery")));
        Cocktail cocktail2 = new Cocktail("Espresso Martini", Arrays.asList(new Ingredient("Espresso"), new Ingredient("Martini")));
        cocktailsRepository.save(cocktail1);
        cocktailsRepository.save(cocktail2);
        final List<Cocktail> result = cocktailsRepository.findByIngredients("Espresso");
        assertEquals(1, result.size());
        assertEquals(cocktail2, result.get(0));
    }



}