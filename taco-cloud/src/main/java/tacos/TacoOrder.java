package tacos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import lombok.Data;


@Data
@Entity
public class TacoOrder implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @NotBlank(message="Delivery name is required")
    private String deliveryName;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos =  new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

}
