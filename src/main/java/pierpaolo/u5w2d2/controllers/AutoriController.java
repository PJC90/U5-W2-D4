package pierpaolo.u5w2d2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pierpaolo.u5w2d2.entities.Autore;
import pierpaolo.u5w2d2.entities.Post;
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
    public Autore savepost(@RequestBody Autore body){return autoreService.save(body);}
    @GetMapping("/{id}")
    public Autore findById(@PathVariable int id){return autoreService.findById(id);}
    @PutMapping("/{id}")
    public Autore findIdUpdate(@PathVariable int id, @RequestBody Autore body){
        return this.autoreService.findByIdAndUpdate(id, body);
    }
    @DeleteMapping("/{id}")
    public void findIdDelete(@PathVariable int id){ this.autoreService.findByIdAndDelete(id);}
}
