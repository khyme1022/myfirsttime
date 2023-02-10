package com.springboot.myfirsttime.information.data.entity;

import com.springboot.myfirsttime.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 *  공지사항 게시판으로 오직 관리자만 글쓰기가 가능하다
 */
@Entity(name="INFO_BOARD")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class) //JPA 사용
public class information extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NO", nullable = false, updatable = false)
    private int no;
    /* 글쓴이*/
    @Column(name="WRITER", length = 30, nullable = false)
    private String writer;
    /* 글 제목 */
    @Column(name = "TITLE", nullable = false) // 정해주지 않으면 기본 길이는 255
    private String title;
    /* 글 내용 */
    @Column(name = "CONTENT", nullable = false, columnDefinition = "LONGTEXT")
    private String content;
    /* 글 삭제여부 */
    @Column(name = "IS_DELETE", nullable = false, columnDefinition = "TINYINT(4) default 0")
    private boolean isDelete;
    /* 글 조회 수 */
    @Column(name = "VIEW", nullable = false, columnDefinition = "Integer default 0")
    private int view;
}
