package com.meetnewfriend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entity.Block;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.BlockRepo;
import com.meetnewfriend.services.BlockService;

@Service
public class BlockServiceImpl implements BlockService{
	
	@Autowired
	private BlockRepo blockRepo;
	
	@Autowired
	private RealFollowerServiceImpl followerServiceImpl;
	
	@Autowired
	private FollowingServiceImpl followingServiceImpl;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean blockUser(int blockUser, int realUser) throws Exception {
		try {
			User block=new User();
			block.setId(blockUser);
			
			User real=new User();
			real.setId(realUser);
			
			Block bl=new Block();
			bl.setBlockUser(block);
			bl.setRealUser(real);
			
			if(this.blockRepo.save(bl)!=null) {
				//here we decrease followers from both user
				if(this.followerServiceImpl.deleteRealFollower(realUser,blockUser)>0) {
					if(this.followerServiceImpl.deleteRealFollower(blockUser,realUser)>0) {
						//here we decrese both side following
						if(this.followingServiceImpl.deleteFollowing(blockUser,realUser)>0) {
							if(this.followingServiceImpl.deleteFollowing(realUser,blockUser)>0)
							{
								return true;
							}
							
						}
					}
				}
			}
		}catch(Exception e) {
			throw new Exception("Exception occur");
		}
		return false;
	}

	@Override
	public List<Block> getBlockUsers(int userId) {
		User user=new User();
		user.setId(userId);
		return this.blockRepo.findByRealUser(user);
	}

	@Transactional
	@Override
	public int unblockUser(int blockUser, int realUser) {
		return this.blockRepo.unblockUser(blockUser,realUser);
	}

}
