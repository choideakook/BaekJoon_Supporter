package com.huh.BaekJoonSupporter.team;

import com.huh.BaekJoonSupporter.DataNotFoundException;
import com.huh.BaekJoonSupporter.line.Line;
import com.huh.BaekJoonSupporter.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public Team create(Long leaderId, String teamName, Line line, Member leader, LocalDateTime createDate) {
        Team team = Team.builder()
                .leaderId(leaderId)
                .teamName(teamName)
                .line(line)
                .leader(leader)
                .createDate(createDate)
                .build();
        this.teamRepository.save(team);
        return team;
    }

    public Team getTeam(Long id) {
        Optional<Team> team = this.teamRepository.findById(id);
        if(team.isPresent()) {
            return team.get();
        } else {
            throw new DataNotFoundException("comment not found");
        }
    }

    public void modify(Team team) {

    }

    public void delete(Team team) {
        this.teamRepository.delete(team);
    }
}

