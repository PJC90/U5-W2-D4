package pierpaolo.u5w2d2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pierpaolo.u5w2d2.config.MailgunSender;
import pierpaolo.u5w2d2.entities.Autore;
import pierpaolo.u5w2d2.entities.Post;
import pierpaolo.u5w2d2.exceptions.BadRequestException;
import pierpaolo.u5w2d2.payloads.NewAutoreDTO;
import pierpaolo.u5w2d2.payloads.NewAutoreResponseDTO;
import pierpaolo.u5w2d2.services.AutoreService;

import java.util.List;

@RestController
@RequestMapping("/autori")
public class AutoriController {
    @Autowired
    private AutoreService autoreService;

    @GetMapping
    public List<Autore> getAutori(){ return autoreService.getAutori();}
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewAutoreResponseDTO savepost(@RequestBody @Validated NewAutoreDTO body, BindingResult validation){
        // Per completare la validazione devo in qualche maniera fare un controllo del tipo: se ci sono errori -> manda risposta con 400 Bad Request
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            Autore newAutore = autoreService.save(body);
            return new NewAutoreResponseDTO(newAutore.getId());
        }

    }
    @GetMapping("/{id}")
    public Autore findById(@PathVariable int id){return autoreService.findById(id);}
    @PutMapping("/{id}")
    public Autore findIdUpdate(@PathVariable int id, @RequestBody Autore body){
        return this.autoreService.findByIdAndUpdate(id, body);
    }
    @DeleteMapping("/{id}")
    public void findIdDelete(@PathVariable int id){ this.autoreService.findByIdAndDelete(id);}
}
