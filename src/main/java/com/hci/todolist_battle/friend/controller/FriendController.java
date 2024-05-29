package com.hci.todolist_battle.friend.controller;

import com.hci.todolist_battle.friend.entity.Friend;
import com.hci.todolist_battle.friend.entity.FriendDto;
import com.hci.todolist_battle.friend.service.FriendService;
import com.hci.todolist_battle.friend.service.FriendServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    @PostMapping("/api/friend/register")
    public void addFriend(@RequestBody FriendDto friendDto) {
        friendService.register(friendDto.getMemberId(), friendDto.getFriendId());
    }

    @GetMapping("/api/{memberId}/friends")
    public List<Friend> getFriends(@PathVariable String memberId) {
        return friendService.getFriends(memberId);
    }
}
