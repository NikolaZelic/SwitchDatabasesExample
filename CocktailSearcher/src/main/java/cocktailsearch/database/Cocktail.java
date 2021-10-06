package cocktailsearch.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cocktail {

    private String name;

    private List<Ingredient> ingredients = new ArrayList<>();

    public Cocktail() {
    }

    public Cocktail(String name) {
        this.name = name;
    }

    public Cocktail(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocktail cocktail = (Cocktail) o;
        return name.equalsIgnoreCase(cocktail.name) && Objects.equals(ingredients, cocktail.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients);
    }
}
