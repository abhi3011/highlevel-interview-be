package com.highlevel.interview.model;

import com.highlevel.interview.constant.DbConstant;
import com.highlevel.interview.repository.PostRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = PostRepository.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id", unique = true)
    private String postId;

    @Size(
        max = DbConstant.POST_TITLE_MAX_LEN,
        message = "title must be at most " + DbConstant.POST_TITLE_MAX_LEN + " characters")
    @Column(name = "title", nullable = false)
    private String title;

    @Size(
        max = DbConstant.POST_BODY_MAX_LEN,
        message = "body must be at most " + DbConstant.POST_BODY_MAX_LEN + " characters")
    @Column(name = "body")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private UserEntity author;

    @Column(name = "author_handle", nullable = false)
    private String authorHandle;
}
