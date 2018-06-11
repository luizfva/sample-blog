package com.sampleblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "comment")
@ToString(of = {"email", "message"})
@EqualsAndHashCode(of = {"id", "email", "message"})
public class Comment implements Serializable {
    private static final long serialVersionUID = -481073315751589931L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotNull
    @Column(name = "email")
    private String email;

    @Setter
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    @Size(max = 32768)
    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private LocalDateTime date;
}
