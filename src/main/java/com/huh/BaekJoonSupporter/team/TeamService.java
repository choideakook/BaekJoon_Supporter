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

    public Team create(Long leaderId, String teamName, Line line, Member leader) {
        Team team = Team.builder()
                .leaderId(leaderId)
                .teamName(teamName)
                .line(line)
                .createDate(LocalDateTime.now())
                .build();
        team.getMembers().add(leader); // 리더 등록 시 멤버 등록도 동시에 이뤄져야함
        this.teamRepository.save(team);
        return team;
    }

    public Team getTeam(String teamName) {
        Optional<Team> team = this.teamRepository.findByTeamName(teamName);
        if(team.isPresent()) {
            return team.get();
        } else {
            throw new DataNotFoundException("comment not found");
        }
    }

    public Team getTeam(Long id) {
        Optional<Team> team = this.teamRepository.findById(id);
        if(team.isPresent()) {
            return team.get();
        } else {
            throw new DataNotFoundException("comment not found");
        }
    }

    public void modify(Team team, String teamName, Line line) {
        Team modifyTeam = team.toBuilder()
                .teamName(teamName)
                .line(line)
                .modifyDate(LocalDateTime.now())
                .build();
        this.teamRepository.save(modifyTeam);
    }

    public void delete(Team team) {
        this.teamRepository.delete(team);
    }

    public void addMember(Team team, Member member) {
        team.getMembers().add(member);
        this.teamRepository.save(team);
    }
}

