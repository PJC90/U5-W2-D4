package pierpaolo.u5w2d2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pierpaolo.u5w2d2.entities.Post;
import pierpaolo.u5w2d2.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostService postService;
//    @GetMapping
//    public List<Post> getPosts(@RequestParam(required = false) String category){
//        if(category != null){
//            return postService.getPostsByCategory(category);
//        } else {
//            return postService.getPosts();
//        }
//    }
    @GetMapping
    public Page<Post> getPosts(@RequestParam(required = false) String category,
                                @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String orderBy){
        // per provare se funziona Error 500 Internal Server Error
//        throw new RuntimeException("Boooooooooooooo*******************************************************ooooooooooooom");

       if (category != null){
           return postService.getPostsByCategory(category, page, size, orderBy);
       }else{
           return postService.getPosts(page, size, orderBy);

       }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post savepost(@RequestBody Post body, @RequestParam long id){return postService.save(body, id);}
    @GetMapping("/{id}")
    public Post findById(@PathVariable int id){return postService.findById(id);}
    @PutMapping("/{id}")
    public Post findIdUpdate(@PathVariable int id, @RequestBody Post body){
        return this.postService.findByIdAndUpdate(id, body);
    }
    @DeleteMapping("/{id}")
    public void findIdDelete(@PathVariable int id){ this.postService.findByIdAndDelete(id);}
}
