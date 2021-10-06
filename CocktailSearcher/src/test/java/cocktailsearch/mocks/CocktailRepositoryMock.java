package cocktailsearch.mocks;

import cocktailsearch.database.Cocktail;
import cocktailsearch.database.CocktailsRepository;

import java.util.List;

public class CocktailRepositoryMock implements CocktailsRepository {

    private boolean saveMethodIsCalled = false;

    @Override
    public Cocktail save(Cocktail cocktail) {
        saveMethodIsCalled = true;
        return cocktail;
    }

    public boolean isSaveMethodCalled() {
        return saveMethodIsCalled;
    }

    public void saveMethodCallRefresh() {
        saveMethodIsCalled = false;
    }

    private boolean deleteMethodCalled = false;

    @Override
    public void deleteCocktail(Cocktail cocktail) {
        deleteMethodCalled = true;
    }

    public boolean isDeleteMethodCalled() {
        return deleteMethodCalled;
    }

    private List<Cocktail> findAllResponse = null;

    public void setFindAllResponse(List<Cocktail> findAllResponse) {
        this.findAllResponse = findAllResponse;
    }

    @Override
    public List<Cocktail> findAll() {
        return findAllResponse;
    }

    private Cocktail findByNameToReturn = null;

    @Override
    public Cocktail findByName(String name) {
        return findByNameToReturn;
    }

    public void setFindByNameToReturn(Cocktail findByNameToReturn) {
        this.findByNameToReturn = findByNameToReturn;
    }

    private List<Cocktail> findByIngredientResponse = null;

    public void setFindByIngredientResponse(List<Cocktail> findByIngredientResponse) {
        this.findByIngredientResponse = findByIngredientResponse;
    }

    @Override
    public List<Cocktail> findByIngredients(String... ingredients) {
        return findByIngredientResponse;
    }
}
