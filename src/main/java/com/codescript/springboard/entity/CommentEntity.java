package com.codescript.springboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Comment")
@Table(name = "Comment")
public class CommentEntity {
		// Member Field
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int commentId;
		private int boardNumber;
		private String userEmail;
		private String commentUserProfile;
		private String commentUserNickname;
		private String CommentWriteDate;
		private String commentContent;
}
