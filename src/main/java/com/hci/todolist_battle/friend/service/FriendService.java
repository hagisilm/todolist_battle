package com.hci.todolist_battle.friend.service;

import com.hci.todolist_battle.friend.entity.Friend;

import java.util.List;

public interface FriendService {
    void register(String memberId,String friendId);

    List<Friend> getFriends(String memberId);
}
