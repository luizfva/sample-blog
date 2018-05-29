package com.sampleblog.controller;

import com.sampleblog.model.Comment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() throws Exception {

    }

    @Test
    public void testCommentShouldBeCreated() throws Exception {
        final ResponseEntity<Comment> resultAsset = createComment();
        Assert.assertNotNull(resultAsset.getBody().getId());
    }

    @Test
    public void testCommentShouldBeRetrieved() throws Exception {
        createComment();
        final ResponseEntity<List> resultAsset = template.getForEntity("/articles/1/comments", List.class);
        Assert.assertNotNull(((LinkedHashMap) resultAsset.getBody().get(0)).get("id"));
    }

    private ResponseEntity<Comment> createComment() {
        final HttpEntity<Object> article = getHttpEntity("{\"email\": \"user1@gmail.com\", \"message\": \"hello\" }");
        return template.postForEntity("/articles/1/comments", article, Comment.class);
    }

    private HttpEntity<Object> getHttpEntity(final Object body) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }
}
