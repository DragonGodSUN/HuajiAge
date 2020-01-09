package com.lh_lshen.mcbbs.huajiage.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class StandUserWorldSavedData extends WorldSavedData{
	private HashMap<UUID,String> stands =new HashMap<UUID,String>() ;
	private List<UUID> players = new ArrayList<UUID>();
	public StandUserWorldSavedData(String name) {
		super(name);
	}

	    public int size()
	    {
	        return stands.size();
	    }

	    public String getStand(UUID uuid)
	    {
	        return stands.get(uuid);
	    }
	    public String getPlayers()
	    {	
	    	StringBuffer message = new StringBuffer();
	    	for(int i =0;i<players.size();i++) {
	    		message.append("&"+players.get(i).toString()+"&");
	    	}
	        return message.toString();
	    }
	    public List<UUID> getPlayersByUUID()
	    {	
	        return players;
	    }
	    public void add(String stand, UUID playerId)
	    {
	        if(!stands.containsKey(playerId)) {
	        	players.add(playerId);
	        }
	        stands.put(playerId, stand);
	        this.markDirty();
	    }
	    
	    public void remove(UUID playerId)
	    {
	    	this.stands.remove(playerId);
	    	this.players.remove(playerId);
	        this.markDirty();
	    }
	    
	    public void clear()
	    {
	    	this.stands.clear();
	    	this.players.clear();
	        this.markDirty();
	    }
	    public String getPlayerStand(EntityPlayer player) {
	    	World world = player.world;
	    	UUID uuid = player.getUniqueID();
	    	String stand = stands.get(uuid);
	    	if(stand!=null) {
	    		return stand;
	    	}
	    	return EnumStandtype.EMPTY;
	    }
	    @Override
	    public void readFromNBT(NBTTagCompound nbt)
	    {
	        stands.clear();
	        players.clear();
	        NBTTagList list = (NBTTagList) nbt.getTag("standUsers");
	        if (list == null)
	        {
	            list = new NBTTagList();
	        }
	        for (int i = list.tagCount() - 1; i >= 0; --i)
	        {
	            NBTTagCompound compound = (NBTTagCompound) list.get(i);
	            stands.put(UUID.fromString(compound.getString("player")),
	            		compound.getString("stand")) ;
	            players.add(UUID.fromString(compound.getString("player")));
	        }
	    }

	    @Override
	    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	    {
	        NBTTagList list = new NBTTagList();
	        for (UUID player : players)
	        {
	        	String stand = stands.get(player);
	            NBTTagCompound compound = new NBTTagCompound();
	            compound.setString("stand", stand);
	            compound.setString("player", player.toString());
	            list.appendTag(compound);
	        }
	        nbt.setTag("standUsers", list);
			return nbt;
	    }
	    
	    public static StandUserWorldSavedData get(World world)
	    {
	        WorldSavedData data = world.getPerWorldStorage().getOrLoadData(StandUserWorldSavedData.class, "HJStands");
	        if (data == null)
	        {
	            data = new StandUserWorldSavedData("HJStands");
	            world.getPerWorldStorage().setData("HJStands", data);
	        }
	        return (StandUserWorldSavedData) data;
	    }

	    public static StandUserWorldSavedData getGlobal(World world)
	    {
	        WorldSavedData data = world.getMapStorage().getOrLoadData(StandUserWorldSavedData.class, "HJStandsGlobal");
	        if (data == null)
	        {
	            data = new StandUserWorldSavedData("HJStandsGlobal");
	            world.getMapStorage().setData("HJStandsGlobal", data);
	        }
	        return (StandUserWorldSavedData) data;
	    }
	    
	}


