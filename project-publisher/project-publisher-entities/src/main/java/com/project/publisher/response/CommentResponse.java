package com.project.publisher.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentResponse extends BaseResponse {

    private String text;
    private int likes;
    private UUID publisherId;
    private String publisherName;
    private UUID discussionId;
    private UUID parentId;
    private List<CommentResponse> replies;
    private int replyCount;
    private boolean isLiked;
}
