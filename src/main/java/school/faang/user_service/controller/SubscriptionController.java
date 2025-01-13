package school.faang.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.faang.user_service.dto.UserDto;
import school.faang.user_service.dto.UserFilterDto;
import school.faang.user_service.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestParam long followerId, @RequestParam long followeeId) {
        subscriptionService.followUser(followerId, followeeId);
        return ResponseEntity.ok("Подписка оформлена!");
    }

    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollowUser(@RequestParam long followerId, @RequestParam long followeeId) {
        subscriptionService.unfollowUser(followerId, followeeId);
        return ResponseEntity.ok("Пользователь не подписан!");
    }

    @GetMapping("/followers")
    public ResponseEntity<List<UserDto>> getFollowers(@RequestParam long followeeId, @RequestBody UserFilterDto filter) {
        List<UserDto> users = subscriptionService.getFollowers(followeeId, filter);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/followees/count")
    public ResponseEntity<Long> getFolloweesCount(@RequestParam long followerId) {
        return ResponseEntity.ok(subscriptionService.getFolloweesCount(followerId));
    }

    @GetMapping("/following")
    public ResponseEntity<List<UserDto>> getFollowing(@RequestParam long followeeId, @RequestBody UserFilterDto filter) {
        List<UserDto> users = subscriptionService.getFollowing(followeeId, filter);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/followers/count")
    public ResponseEntity<Integer> getFollowersCount(@RequestParam int followeeId) {
        return ResponseEntity.ok(subscriptionService.getFollowersCount(followeeId));
    }
}
