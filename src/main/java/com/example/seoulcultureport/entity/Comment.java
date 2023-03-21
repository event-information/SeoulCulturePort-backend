package com.example.seoulcultureport.entity;

import com.example.seoulcultureport.dto.ThumbsupStatus;
import com.example.seoulcultureport.dto.commentDto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Long userid;

    @ManyToOne
    @JoinColumn(name = "Board_ID", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Thumbsup> thumbsups = new ArrayList<>();

    public void update(CommentRequestDto commentRequestDto) {

        this.comment = commentRequestDto.getComment();
    }

    public Comment(CommentRequestDto commentRequestDto, Board board, User user) {
        this.comment = commentRequestDto.getComment();
        this.board = board;
        this.userid = user.getId();
        this.nickname = user.getNickname();
    }

    public void addThumbsup(Thumbsup thumbsup) {
        this.thumbsups.add(thumbsup);
        thumbsup.setComment(this);
    }

    public void cancelThumbsup(Thumbsup thumbsup) {
        this.thumbsups.remove(thumbsup);
        thumbsup.setComment(null);
    }

    public ThumbsupStatus commentThumbsupByUser(Long userid) {
        for(Thumbsup thumbsup : this.thumbsups) {
            if(thumbsup.getUser().getId().equals(userid)) {
                return thumbsup.getThumbsupStatus();
            }
        }
        return ThumbsupStatus.CANCELED;
    }

}
