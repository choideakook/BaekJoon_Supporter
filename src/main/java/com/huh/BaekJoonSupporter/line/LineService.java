package com.huh.BaekJoonSupporter.line;


import com.huh.BaekJoonSupporter.team.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineService {

    private final LineRepository lineRepository;

    public Line create(String token, Team team) {
        Line line = Line.builder()
                .token(token)
                .team(team)
                .build();

        return lineRepository.save(line);
    }

    public void delete(Line line) {
        this.lineRepository.delete(line);
    }
}