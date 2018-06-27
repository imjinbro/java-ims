package codesquad.web;

import codesquad.domain.Comment;
import codesquad.domain.User;
import codesquad.dto.CommentDto;
import codesquad.security.LoginUser;
import codesquad.service.CommentService;
import codesquad.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/issues/{issueId}/comments")
public class ApiCommentController {
    private static final Logger log = LoggerFactory.getLogger(ApiCommentController.class);

    @Autowired
    private IssueService issueService;

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> create(@LoginUser User loginUser, @PathVariable Long issueId, @Valid @RequestBody CommentDto commentDto) {
        Comment comment = commentService.create(loginUser, issueService.findById(issueId), commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@LoginUser User loginUser, @PathVariable Long issueId, @PathVariable Long id, @Valid @RequestBody CommentDto updateCommentDto) {
        Comment comment = commentService.update(loginUser, issueService.findById(issueId), id, updateCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
}
