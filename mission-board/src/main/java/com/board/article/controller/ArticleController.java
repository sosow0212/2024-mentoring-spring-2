package com.board.article.controller;

import com.board.article.controller.dto.ArticleRequest;
import com.board.article.controller.dto.ArticleResponse;
import com.board.article.controller.dto.ArticleResponses;
import com.board.article.mapper.ArticleMapper;
import com.board.article.service.ArticleService;
import com.board.login.annotation.Login;
import com.board.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public ResponseEntity<Void> createArticle(@RequestBody ArticleRequest articleRequest, @Login Member member) {
        ArticleResponse articleResponse = ArticleMapper.toArticleResponse(articleService.createArticle(member, articleRequest));
        URI location = URI.create("/api/articles/" + articleResponse.id());
        log.info("{}님 '{}'글 작성 완료", articleResponse.memberNickName(), articleResponse.title());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/articles")
    public ResponseEntity<ArticleResponses> showAllArticles() {
        ArticleResponses articleResponses = ArticleMapper.toArticleResponses(articleService.findAllArticles());
        log.info("게시된 모든 글을 조회했습니다.");
        return ResponseEntity.ok(articleResponses);
    }

    @GetMapping("/articles/{articleId}")
    public ResponseEntity<ArticleResponse> showArticle(@PathVariable Long articleId) {
        ArticleResponse articleResponse = ArticleMapper.toArticleResponse(articleService.findArticle(articleId));
        log.info("{}번 게시글을 조회했습니다.", articleResponse.id());
        return ResponseEntity.ok(articleResponse);
    }

    @PatchMapping("/articles/{articleId}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long articleId, @Login Member member, @RequestBody ArticleRequest articleRequest) {
        ArticleResponse articleResponse = ArticleMapper.toArticleResponse(articleService.updateArticle(articleId, member, articleRequest));
        log.info("{}님 게시물을 수정했습니다.", articleResponse.memberNickName());
        return ResponseEntity.ok(articleResponse);
    }

    @DeleteMapping("/articles/{articleId}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable Long articleId, @Login Member member) {
        ArticleResponse articleResponse = ArticleMapper.toArticleResponse(articleService.deleteArticle(articleId, member));
        log.info("{}님의 게시물이 삭제되었습니다.", articleResponse.memberNickName());
        return ResponseEntity.ok(articleResponse);
    }

    @GetMapping("/members/articles")
    public ResponseEntity<ArticleResponses> showMemberArticles(@Login Member member){
        ArticleResponses articleResponses = ArticleMapper.toArticleResponses(articleService.findMemberArticles(member));
        log.info("{}님의 모든 게시물을 불러왔습니다.", member.getMemberNickName());
        return ResponseEntity.ok(articleResponses);
    }
}
