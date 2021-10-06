package sqldb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cocktails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailEntity {

    @Id
    private String name;

    @ElementCollection
    @CollectionTable(name = "ingredients", joinColumns = @JoinColumn(name = "ing_name"))
    private List<IngredientEntity> ingredients = new ArrayList<>();

    @Override
    public String toString() {
        return "CocktailEntity{" +
                "name='" + name + '\'' +
                '}';
    }

}