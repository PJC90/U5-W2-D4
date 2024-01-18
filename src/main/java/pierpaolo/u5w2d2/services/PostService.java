package pierpaolo.u5w2d2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pierpaolo.u5w2d2.entities.Autore;
import pierpaolo.u5w2d2.entities.Post;
import pierpaolo.u5w2d2.exceptions.BadRequestException;
import pierpaolo.u5w2d2.exceptions.NotFoundException;
import pierpaolo.u5w2d2.payloads.posts.NewPostDTO;
import pierpaolo.u5w2d2.repositories.AutoreDAO;
import pierpaolo.u5w2d2.repositories.PostDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class PostService {
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private AutoreService autoreService;

    public Page<Post> getPosts(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable =  PageRequest.of(page, size, Sort.by(orderBy));
        return this.postDAO.findAll(pageable);
    }
    public Page<Post> getPostsByCategory(String category, int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return this.postDAO.findByCategoria(category, pageable);
    }

    public Post save(NewPostDTO body){
        Autore autore = autoreService.findById(body.autoreId());
        Post newPost = new Post();
        newPost.setTitolo(body.titolo());
        newPost.setCategoria(body.categoria());
        newPost.setCover("http://picsum.photos/200/300");
        newPost.setTempoDiLettura(body.tempoDiLettura());
        newPost.setContenuto(body.contenuto());
        newPost.setAutore(autore);
        return postDAO.save(newPost);
    }
    public Post findById(long id){
        return postDAO.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public Post findByIdAndUpdate(long id, Post body){
        Post found = this.findById(id);
        found.setCategoria(body.getCategoria());
        found.setTitolo(body.getTitolo());
        found.setCover(body.getCover());
        found.setContenuto(body.getContenuto());
        found.setTempoDiLettura(body.getTempoDiLettura());
        return postDAO.save(found);
    }


    public void findByIdAndDelete(int id){
        Post found = this.findById(id);
        postDAO.delete(found);
    }


}
