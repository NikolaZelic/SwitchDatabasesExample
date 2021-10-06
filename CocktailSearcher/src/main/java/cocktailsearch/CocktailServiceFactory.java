package cocktailsearch;

import cocktailsearch.database.CocktailsRepository;
import cocktailsearch.services.CreateCocktailServiceImpl;
import cocktailsearch.services.DeleteCocktailServiceImpl;
import cocktailsearch.services.EditCocktailServiceImpl;
import cocktailsearch.services.SearchCocktailServiceImpl;

public final class CocktailServiceFactory {

    public static CreateCocktailService newCreateCocktailService(CocktailsRepository repository) {
        return new CreateCocktailServiceImpl(repository);
    }

    public static DeleteCocktailService newDeleteCocktailService(CocktailsRepository repository) {
        return new DeleteCocktailServiceImpl(repository);
    }

    public static EditCocktailService newEditCocktailService(CocktailsRepository repository) {
        return new EditCocktailServiceImpl(repository);
    }

    public static SearchCocktailService newSearchCocktailService(CocktailsRepository repository) {
        return new SearchCocktailServiceImpl(repository);
    }

}
