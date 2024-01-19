package pierpaolo.u5w2d2.payloads;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import pierpaolo.u5w2d2.entities.Autore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

// Aggiungi la dipendenza validation che serve per validare il payload
public record NewPostDTO(
         @NotEmpty(message = "categoria è un campo obbligatorio!")
         String categoria,
         @NotEmpty(message = "titolo è un campo obbligatorio!")
         @Size(min = 3, max = 30, message = "Il titolo deve essere compreso tra 3 e 30 caratteri!")
         String titolo,
         @NotEmpty(message = "cover è un campo obbligatorio!")
         String cover,
         @NotEmpty(message = "contenuto è un campo obbligatorio!")
         @Size(min = 3, max = 30, message = "Il contenuto deve essere compreso tra 3 e 30 caratteri!")
         String contenuto,
         @NotNull(message = "tempoDiLettura è un campo obbligatorio!")
         int tempoDiLettura,
         @NotNull(message = "autoreId è un campo obbligatorio!")
         int autoreId
         //pa
) {
}
