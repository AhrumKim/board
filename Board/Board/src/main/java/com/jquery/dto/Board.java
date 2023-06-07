package com.jquery.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private int postId;
    private String postTitle;
    private String postContent;
    private Timestamp createdAt;
    private String author;
}
