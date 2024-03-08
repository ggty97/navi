package ssafy.navi.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Getter @Setter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_pk")
    private Long id;

    @Column(name="nickname")
    private String nickname;

    // 소셜로그인 email
    @Column(name="email")
    private String email;

    // 프로필 사진 S3 URL
    @Column(name="image")
    private String image;

    // 팔로잉 수
    @Column(name = "following_count")
    private Integer followingCount;

    // 팔로워 수
    @Column(name = "follower_count")
    private Integer followerCount;

    // 노래방
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Noraebang> noraebangs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<NoraebangLike> noraebangLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<NoraebangReview> noraebangReviews;

    // AI 커버
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CoverUser> coverUsers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CoverLike> coverLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CoverReview> coverReviews;

    // 팔로워
    @OneToMany(mappedBy = "toUser", cascade = CascadeType.ALL)
    private List<Follow> followers;

    // 팔로잉
    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL)
    private List<Follow> followings;

    // 알람
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Alarm> alarms;

    // 매칭 중계 테이블
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MatchingUser> matchingUsers;

}
