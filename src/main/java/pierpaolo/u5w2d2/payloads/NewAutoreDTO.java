package pierpaolo.u5w2d2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewAutoreDTO(
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 30, message = "Nome deve avere minimo 3 caratteri, massimo 30")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio")
        String cognome,
        @Email(message = "L'indirizzo inserito non è un indirizzo valido")
        @NotEmpty(message = "La mail è un campo obbligatorio!")
        String email,
        @NotNull(message = "La data di nascita è obbligatoria")
        LocalDate dataDiNascita
) {

}
