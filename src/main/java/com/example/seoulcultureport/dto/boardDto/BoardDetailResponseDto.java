package com.example.seoulcultureport.dto.boardDto;

import com.example.seoulcultureport.dto.commentDto.CommentResponseDto;
import com.example.seoulcultureport.entity.*;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardDetailResponseDto {
    private Long id;
    private String title;
    private String image;
    private String pageUrl;
    private String classify;
    private String region;
    private String location;
    private String startDate;   // 타입 : string
    private String endDate;
    private String contents;
    private int cmtCount;
    private String username;
    private String nickname;
    private String createdat;
    private int thumbsUpCount;
    private boolean thumbsStatus;

    private final List<CommentResponseDto> commentList = new ArrayList<>();

    public BoardDetailResponseDto(User user, Board board, boolean thumbsStatus) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.pageUrl = board.getPageUrl();
        this.classify = board.getClassify();
        this.region = board.getRegion();
        this.location = board.getLocation();
        this.startDate = board.getStartDate();
        this.endDate = board.getEndDate();
        this.contents = board.getContents();
        this.cmtCount = board.getComments().size();
        this.username = board.getUsername();
        this.nickname = board.getNickname();
        this.createdat = board.getCreatedAt().format(formatter);
        this.thumbsUpCount = board.getBoardLikeList().size();
        this.thumbsStatus = thumbsStatus;

        for (Comment comment : board.getComments()) {
            commentList.add(new CommentResponseDto(comment, (CommentLike) comment.getCommentLikeList(), user));
        }
    }

}
