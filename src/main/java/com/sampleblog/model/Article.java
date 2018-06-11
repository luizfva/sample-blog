/**
 *
 */
package com.sampleblog.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "article")
@ToString(of = {"email", "title"})
@EqualsAndHashCode(of = {"id", "email", "title"})
@Document(indexName = "article", type = "article")
public class Article implements Serializable {

    private static final long serialVersionUID = 5124000706092599751L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "title")
    private String title;

    @Size(min = 0, max = 32768)
    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "published")
    private Boolean published;
}
