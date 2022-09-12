package com.meetnewfriend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meetnewfriend.entity.Block;

@Service
public interface BlockService {
	public boolean blockUser(int blockUser,int realUser) throws Exception;
	
	public List<Block> getBlockUsers(int userId);
	
	public int unblockUser(int blockUser,int realUser);
}
