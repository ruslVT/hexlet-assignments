package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "{postId}/comments")
    public Iterable<Comment> getCommentsByPostId(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "{postId}/comments/{commentId}")
    public Comment getCommentByPostIdAndCommentId(@PathVariable long postId, @PathVariable long commentId) {
        return commentRepository.findByIdAndPostId(postId, commentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment " + commentId + " for post " + postId + " not found"));
    }

    @PostMapping(path = "{postId}/comments")
    public Iterable<Comment> createCommentForPost(@PathVariable long postId, @RequestBody Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post " + postId + " not found"));

        this.commentRepository.save(comment).setPost(post);
        return commentRepository.findAllByPostId(postId);
    }

    @PatchMapping(path = "{postId}/comments/{commentId}")
    public void updateCommitByCommentIdAndPostId(@PathVariable long postId,
                                                @PathVariable long commentId,
                                                @RequestBody Comment comment) {
        Comment updatedComment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment " + commentId + " for post " + postId + " not found"));

        updatedComment.setContent(comment.getContent());
        commentRepository.save(updatedComment);
    }

    @DeleteMapping(path = "{postId}/comments/{commentId}")
    public void deleteCommitByCommentIdAndPostId(@PathVariable long postId,
                                                @PathVariable long commentId) {
        commentRepository.findByIdAndPostId(postId, commentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment " + commentId + " for post " + postId + " not found"));

        commentRepository.deleteById(commentId);
    }
    // END
}
