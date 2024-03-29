package pierpaolo.u5w2d2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pierpaolo.u5w2d2.config.MailgunSender;
import pierpaolo.u5w2d2.entities.Autore;
import pierpaolo.u5w2d2.entities.Post;
import pierpaolo.u5w2d2.exceptions.BadRequestException;
import pierpaolo.u5w2d2.exceptions.NotFoundException;
import pierpaolo.u5w2d2.payloads.NewAutoreDTO;
import pierpaolo.u5w2d2.repositories.AutoreDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class AutoreService {
    @Autowired
    private AutoreDAO autoreDAO;
    @Autowired
    private MailgunSender mailgunSender;

    public List<Autore> getAutori(){ return this.autoreDAO.findAll();}
    public Autore save(NewAutoreDTO body){
        Autore newAutore = new Autore();
        newAutore.setAvatar("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        newAutore.setNome(body.nome());
        newAutore.setCognome(body.cognome());
        newAutore.setEmail(body.email());
        newAutore.setDataDiNascita(body.dataDiNascita());
        mailgunSender.sendRegistrationEmail(newAutore);
        return autoreDAO.save(newAutore);
    }
    public Autore findById(long id){
        return autoreDAO.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public Autore findByIdAndUpdate(long id, Autore body){
       Autore found = this.findById(id);
       found.setNome(body.getNome());
       found.setCognome(body.getCognome());
       found.setEmail(body.getEmail());
       found.setDataDiNascita(body.getDataDiNascita());
       found.setAvatar(body.getAvatar());
       return autoreDAO.save(found);
    }


    public void findByIdAndDelete(int id){
        Autore found = this.findById(id);
        autoreDAO.delete(found);
    }
}
