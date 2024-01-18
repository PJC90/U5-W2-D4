package pierpaolo.u5w2d2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pierpaolo.u5w2d2.entities.Post;
import pierpaolo.u5w2d2.exceptions.BadRequestException;
import pierpaolo.u5w2d2.payloads.NewPostDTO;
import pierpaolo.u5w2d2.payloads.NewPostResponseDTO;
import pierpaolo.u5w2d2.services.PostService;

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
    public NewPostResponseDTO savepost(@RequestBody @Validated NewPostDTO newPostPayload, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            Post newPost = postService.save(newPostPayload);
            return new NewPostResponseDTO(newPost.getId());
        }

    }
    @GetMapping("/{id}")
    public Post findById(@PathVariable int id){return postService.findById(id);}
    @PutMapping("/{id}")
    public Post findIdUpdate(@PathVariable int id, @RequestBody Post body){
        return this.postService.findByIdAndUpdate(id, body);
    }
    @DeleteMapping("/{id}")
    public void findIdDelete(@PathVariable int id){ this.postService.findByIdAndDelete(id);}
}
