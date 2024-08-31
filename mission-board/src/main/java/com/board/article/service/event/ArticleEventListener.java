package com.board.article.service.event;

import com.board.article.domain.Article;
import com.board.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleEventListener {

    private final ArticleService articleService;

    @EventListener
    public void handleArticleEvent(ArticleFindEvent articleFindEvent){
        Article article = articleService.findArticle(articleFindEvent.getArticleId());
        articleFindEvent.getFuture()
                .complete(article);
    }
}
