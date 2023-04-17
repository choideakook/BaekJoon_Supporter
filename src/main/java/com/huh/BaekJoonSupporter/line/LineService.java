package com.huh.BaekJoonSupporter.line;


import com.huh.BaekJoonSupporter.DataNotFoundException;
import com.huh.BaekJoonSupporter.team.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LineService {

    private final LineRepository lineRepository;

    public Line create(String token, Team team) {
        Line line = Line.builder()
                .token(token)
                .team(team)
                .createDate(LocalDateTime.now())
                .build();

        return lineRepository.save(line);
    }

    public Line getLine(Long id) {
        Optional<Line> line = this.lineRepository.findById(id);
        if(line.isPresent()) {
            return line.get();
        } else {
            throw new DataNotFoundException("line not found");
        }
    }

    public Line getLine(String token) {
        Optional<Line> line = this.lineRepository.findByToken(token);
        if(line.isPresent()) {
            return line.get();
        } else {
            throw new DataNotFoundException("line not found");
        }
    }

    public void delete(Line line) {
        this.lineRepository.delete(line);
    }

    public void modify(Line line, String m_token, Team m_team) {
        Line m_line = line.toBuilder()
                .token(m_token)
                .team(m_team)
                .modifyDate(LocalDateTime.now())
                .build();

        this.lineRepository.save(m_line);
    }
}