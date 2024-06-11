package Board.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    // Insert (게시글 생성)
    @PostMapping
    public ResponseEntity<ContentDTO> createContent (@RequestBody ContentDTO content) {
        ContentDTO createdContent = contentService.createContent(content);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContent);
    }

    // SELECT (전체 데이터)
    @GetMapping
    public ResponseEntity<List<ContentDTO>> getAllContent() {
        List<ContentDTO> comments = contentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    // SELECT (일부 데이터)
    @GetMapping("/{id}")
    public ResponseEntity<ContentDTO> getContentById(@PathVariable Long content_id) {
        ContentDTO content = contentService.getContentById(content_id);

        if(content == null) {
            // 해당 정보가 없다면
            return ResponseEntity.notFound().build();
        }
            // 정보가 있어서 가지고 올 수 있다면
            return ResponseEntity.ok(content);
    }

    // update (게시글 수정)
    @PutMapping("/{id}")
    public ResponseEntity<ContentDTO> updateArticle(@PathVariable Long content_id, @RequestBody ContentDTO updateContent) {
        ContentDTO content = contentService.updateContent(content_id, updateContent);

        if(content == null) {
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok().build();
    }

}
