package pierpaolo.u5w2d2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pierpaolo.u5w2d2.entities.Post;

import java.util.Optional;

@Repository
public interface PostDAO extends JpaRepository<Post, Long> {
    Optional<Post> findByTitolo(String titolo);


    Page<Post> findByCategoria(String categoria, Pageable pageable);
}
