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
@Entity(name = "Likes")
@Table(name = "Likes")
public class LikesEntity {
		// Member Field
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int LikeId;
		private int boardNumber;
		private String userEmail;
		private String likeUserProfile;
		private String likeUserNickname;
}
