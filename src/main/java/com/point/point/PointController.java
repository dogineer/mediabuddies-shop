package com.point.point;

import com.point.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/point")
public class PointController {
    private final UserRepository userRepository;
    private final PointService pointService;

    @PostMapping("/add/{name}")
    public void pointAdd(@PathVariable String name ){
        UUID userId = userRepository.findByName(name).getId();

        pointService.addPoint(userId);
    }

    @GetMapping("/info/{name}")
    public List<Point> userPointDetails(@PathVariable  String name){
        UUID userId = userRepository.findByName(name).getId();

        return pointService.findUserPoint(userId);
    }

    @GetMapping("/total/{name}")
    public Integer userTotalPointDetails(@PathVariable String name){
        UUID userId = userRepository.findByName(name).getId();

        return pointService.findUserTotalPoint(userId);
    }

    @PostMapping("/use/{usePoint}/{name}")
    public void pointRemove(@PathVariable Integer usePoint, @PathVariable String name) {
        UUID userId = userRepository.findByName(name).getId();

        pointService.removeUsePoint(usePoint, userId);
    }

    @GetMapping("/use/info/{name}")
    public List<PointsOfUsed> pointsOfUsedDetails(@PathVariable String name){
        UUID userId = userRepository.findByName(name).getId();

        return pointService.findUserPointUsageHistory(userId);
    }
}
