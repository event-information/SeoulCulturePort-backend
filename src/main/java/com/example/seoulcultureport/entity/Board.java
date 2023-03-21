package com.example.seoulcultureport.entity;


import com.example.seoulcultureport.dto.boardDto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String pageUrl;

    @Column(nullable = false)
    private String classify;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String startDate; // 타입 : string

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userid; //단방향

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @OrderBy(value = "createdAt DESC")
    List<Comment> comments = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "boardid")
    List<BoardLike> boardLikeList = new ArrayList<>();

    public Board(BoardRequestDto boardRequestDto, User user) {
        this.title = boardRequestDto.getTitle();
        this.image = boardRequestDto.getImage();
        this.pageUrl = boardRequestDto.getPageUrl();
        this.classify = boardRequestDto.getClassify();
        this.region = boardRequestDto.getRegion();
        this.location = boardRequestDto.getLocation();
        this.startDate = boardRequestDto.getStartDate();
        this.endDate = boardRequestDto.getEndDate();
        this.contents = boardRequestDto.getContents();
        this.userid = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.image = boardRequestDto.getImage();
        this.pageUrl = boardRequestDto.getPageUrl();
        this.classify = boardRequestDto.getClassify();
        this.region = boardRequestDto.getRegion();
        this.location = boardRequestDto.getLocation();
        this.startDate = boardRequestDto.getStartDate();
        this.endDate = boardRequestDto.getEndDate();
        this.contents = boardRequestDto.getContents();
    }

}
