package com.kakaobank.blogsearch.keyword.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Keyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String keyword;
	private LocalDateTime createdAt;

	public static Keyword searched(final String keyword) {
		if (Objects.isNull(keyword)) {
			throw new IllegalArgumentException("keyword is null");
		}
		return new Keyword(null, keyword, LocalDateTime.now());
	}

}
