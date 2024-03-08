package ssafy.navi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ssafy.navi.entity.Lyric;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LyricDto {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String content;
    private Integer sequence;
    private SongDto song;

    // 엔티티 Dto로 변환
    public static LyricDto convertToDto(Lyric lyric) {
        LyricDto lyricDto = new LyricDto();

        // set
        lyricDto.setId(lyric.getId());
        lyricDto.setStartTime(lyric.getStartTime());
        lyricDto.setEndTime(lyric.getEndTime());
        lyricDto.setContent(lyric.getContent());
        lyricDto.setSequence(lyric.getSequence());
        lyricDto.setSong(SongDto.convertToDto(lyric.getSong()));

        return lyricDto;
    }
}
