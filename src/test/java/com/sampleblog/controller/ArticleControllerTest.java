package com.sampleblog.controller;

import com.sampleblog.model.Article;
import com.sampleblog.search.ArticleSearchRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private ArticleSearchRepository searchRepository;

    @Before
    public void setup() throws Exception {
        searchRepository.deleteAll();
    }

    @Test
    public void testArticleShouldBeCreated() throws Exception {
        final HttpEntity<Object> article = getHttpEntity(
                "{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }");
        final ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article,
                Article.class);
        Assert.assertNotNull(resultAsset.getBody().getId());
    }

    @Test
    public void testArticleShouldBeRetrieved() {
        final Article article = createArticle();

        final ResponseEntity<Article> resultAsset = template.getForEntity("/articles/" + article.getId(),
                Article.class);

        Assert.assertNotNull(resultAsset.getBody().getId());
        Assert.assertEquals("user1@gmail.com", resultAsset.getBody().getEmail());
    }

    @Test
    public void testArticleShouldSearch() throws Exception {
        final HttpEntity<Object> article = getHttpEntity("{\"email\": \"user1@gmail.com\", \"title\": \"test-search\" }");
        template.postForEntity("/articles", article, Article.class);

        final ResponseEntity<List> searchResult = template.getForEntity("/articles/search?text=test-search", List.class);
        Assert.assertNotNull(searchResult);
        Assert.assertEquals(1, searchResult.getBody().size());
        Assert.assertEquals("test-search", ((LinkedHashMap) searchResult.getBody().get(0)).get("title"));
    }

    @Test
    public void testArticleShouldBeUpdated() throws Exception {
        final Article article = createArticle();

        final HttpEntity<Object> updatedArticle = getHttpEntity("{\"id\": " + article.getId()
                + ", \"email\": \"user2@gmail.com\", \"title\": \"hello\" }");
        template.put("/articles/" + article.getId(), updatedArticle, Article.class);
        final ResponseEntity<Article> resultAsset = template.getForEntity("/articles/" + article.getId(),
                Article.class);

        Assert.assertNotNull(resultAsset.getBody().getId());
        Assert.assertEquals("user2@gmail.com", resultAsset.getBody().getEmail());
    }

    private Article createArticle() {
        final HttpEntity<Object> article = getHttpEntity("{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }");
        return template.postForEntity("/articles", article, Article.class).getBody();
    }

    @Test
    public void testArticleShouldBeDeleted() throws Exception {
        final Article article = createArticle();
        template.delete("/articles/" + article.getId());
        final ResponseEntity<Article> resultAsset = template.getForEntity("/articles/" + article.getId(), Article.class);
        Assert.assertEquals(HttpStatus.NOT_FOUND, resultAsset.getStatusCode());
    }

    private HttpEntity<Object> getHttpEntity(final Object body) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }
}
