package com.huh.BaekJoonSupporter.team;

import com.huh.BaekJoonSupporter.DataNotFoundException;
import com.huh.BaekJoonSupporter.line.Line;
import com.huh.BaekJoonSupporter.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public Team create(Long leaderId, String teamName, Line line, Member leader, LocalDateTime createDate) {
        Team team = Team.builder()
                .leaderId(leaderId)
                .teamName(teamName)
                .line(line)
                .createDate(createDate)
                .build();
        team.getMembers().add(leader);
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

    public void modify(Long leaderId, String teamName, Line line, LocalDateTime modifyDate) {
        Team team = Team.builder()
                .leaderId(leaderId)
                .teamName(teamName)
                .line(line)
                .modifyDate(modifyDate)
                .build();
        this.teamRepository.save(team);
    }

    public void delete(Team team) {
        this.teamRepository.delete(team);
    }

    public void addMember(Team team, Member member) {
        team.getMembers().add(member);
    }
}

