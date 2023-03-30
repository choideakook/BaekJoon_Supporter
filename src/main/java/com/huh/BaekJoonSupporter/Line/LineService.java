package com.huh.BaekJoonSupporter.Line;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineService {

    private final LineRepository lineRepository;
}