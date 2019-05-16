package iTpa;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import classes.tpaData;
import classes.tpaHereData;

public class iTpa
  extends JavaPlugin
  {
  public void onEnable()
  {
	 getConfig().options().copyDefaults(true);
	 getConfig().options().copyHeader(true);
	 saveDefaultConfig();
	 
  }
  public static List<tpaData> tpaList = new ArrayList<tpaData>();
  public static List<tpaHereData> tpaHereList = new ArrayList<tpaHereData>();
  public boolean IsPlayerInvitedByPlayer_tphere(Player p1, Player p2)
  {
	  for(tpaHereData tpaHereData : tpaHereList)
	  {
		  if(((Player)p1).getDisplayName().equals(((Player)tpaHereData.getMyself()).getDisplayName()) && ((Player)p2).getDisplayName().equals(((Player)tpaHereData.getTargetparty()).getDisplayName()))
		  {
			  return true;
		  }
	  }
	  return false;
  }
  
  public boolean IsPlayerInvitedByPlayer_tpa(Player p1, Player p2)
  {
	  for(tpaData tpaData : tpaList)
	  {
		  if(((Player)p1).getDisplayName().equals(((Player)tpaData.getMyself()).getDisplayName()) && ((Player)p2).getDisplayName().equals(((Player)tpaData.getTargetparty()).getDisplayName()))
		  {
			  return true;
		  }
	  }
	  return false;
  }
  
  public void DeletetpaRequest(Player p1, Player p2)
  {
	  tpaList.removeIf(tpaData -> ((Player)tpaData.getMyself()).getDisplayName().equals(((Player)p1).getDisplayName()) && ((Player)tpaData.getTargetparty()).getDisplayName().equals(((Player)p2).getDisplayName()));
  }
  public void DeletetpahereRequest(Player p1, Player p2)
  {
	  tpaHereList.removeIf(tpaHereData -> ((Player)tpaHereData.getMyself()).getDisplayName().equals(((Player)p1).getDisplayName()) && ((Player)tpaHereData.getTargetparty()).getDisplayName().equals(((Player)p2).getDisplayName()));
  }
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
	  if (!(sender instanceof Player))
	  {
		  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("ConsoleForbidden")));
		  return true;
	  }
	  final Player me = (Player)sender;
	  if (cmd.getName().equalsIgnoreCase("tphere"))
	  {
    	
	        if(args.length == 0)
	        {
	          me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("NoPlayerSpecified")));
	          return true;
	        }
	        Player target = Bukkit.getServer().getPlayer(args[0]);
	        if (target == null)
	        {
	          me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("PlayerDoesNotExistError")));
	          return true;
	        }
	        if(IsPlayerInvitedByPlayer_tphere(me, target))
	        {
	        	me.sendMessage(ChatColor.RED + "You have already requested a tphere from that player!");
	            return true;
	        }
	        if(IsPlayerInvitedByPlayer_tpa(me, target))
	        {
	        	me.sendMessage(ChatColor.RED + "You have already requested a tpa from that player!");
	            return true;
	        }
	        if (me.getWorld() == target.getWorld())
	        {
	          tpaHereData tpaHereData = new tpaHereData(me, target);
	          tpaHereList.add(tpaHereData);
	          me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("requestSent").replaceAll("/target/", target.getDisplayName())));
	          target.sendMessage(ChatColor.GOLD+ "_____________________________________________");
	          target.sendMessage(" ");
	          target.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("requestReceived_3").replaceAll("/user/", me.getDisplayName())));
	          target.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("requestReceived_4").replaceAll("/user/", me.getDisplayName())));
	          target.sendMessage(" ");
	          target.sendMessage(ChatColor.GOLD+ "_____________________________________________");
	          return true;
	        }
	        else
	        {
		        me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("PlayerInAnotherWorldError")));
		        return true;
	        }
	  }
	  if (cmd.getName().equalsIgnoreCase("tpa"))
	  {

	        if (args.length == 0)
	        {
	          me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("NoPlayerSpecified")));
	          return true;
	        }
	        Player target = Bukkit.getServer().getPlayer(args[0]);
	        if (target == null)
	        {
	          me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("PlayerDoesNotExistError")));
	          return true;
	        }
	        if(IsPlayerInvitedByPlayer_tpa(me, target))
	        {
	        	me.sendMessage(ChatColor.RED + "You have already requested a tphere from that player!");
	            return true;
	        }
	        if(IsPlayerInvitedByPlayer_tphere(me, target))
	        {
	        	me.sendMessage(ChatColor.RED + "You have already requested a tpa from that player!");
	            return true;
	        }
	        if (me.getWorld() == target.getWorld())
	        {
	          tpaData tpaData = new tpaData(me, target);
	          tpaList.add(tpaData);
	          me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("requestSent").replaceAll("/target/", target.getDisplayName())));
	          target.sendMessage(ChatColor.GOLD+ "_____________________________________________");
	          target.sendMessage(" ");
	          target.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("requestReceived_1").replaceAll("/user/", me.getDisplayName())));
	          target.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("requestReceived_2").replaceAll("/user/", me.getDisplayName())));
	          target.sendMessage(" ");
	          target.sendMessage(ChatColor.GOLD+ "_____________________________________________");
	          return true;
	        }
	        else 
	        {
	        	me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("PlayerInAnotherWorldError")));
	        	return true;
	        }
	  }
	  if (cmd.getName().equalsIgnoreCase("tpaccept"))
	  {
		  	if (args.length == 0)
			{
			  me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("NoPlayerSpecified")));
			  return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null)
			{
			  me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("PlayerDoesNotExistError")));
			  return true;
			}
			if (!IsPlayerInvitedByPlayer_tphere(target, me) && !IsPlayerInvitedByPlayer_tpa(target, me))
			{
				me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("NoRequestToAccept")));
				return true;
			}
			if(IsPlayerInvitedByPlayer_tpa(target, me))
			{
				System.out.println("[tpa] Teleported "+target.getDisplayName()+" to "+me.getDisplayName()+"");
				((Player)target).teleport(me);
			    me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tpaRequestAccepted").replaceAll("/user/", ((Player)target).getDisplayName())));
			    ((Player)target).sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("RequestAccepted").replaceAll("/target/", me.getDisplayName())));
			    
			    DeletetpaRequest(target, me);
			    return true;
			}
			else if (IsPlayerInvitedByPlayer_tphere(target, me))
	      	{
				System.out.println("[tpahere] Teleported "+me.getDisplayName()+" to "+target.getDisplayName()+"");
		        me.teleport((Player)target);
		        me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tphereRequestAccepted").replaceAll("/user/", ((Player)target).getDisplayName())));
		        ((Player)target).sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("RequestAccepted").replaceAll("/target/", me.getDisplayName())));
		        DeletetpahereRequest(target, me);
		        return true;
	      	}
			return true;
	  }
	  if (cmd.getName().equalsIgnoreCase("tpdeny"))
	  {
	    	if (args.length == 0)
			{
			  me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("NoPlayerSpecified")));
			  return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null)
			{
			  me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("PlayerDoesNotExistError")));
			  return true;
			}
			if (!IsPlayerInvitedByPlayer_tphere(target, me) && !IsPlayerInvitedByPlayer_tpa(target, me))
			{
				me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("NoRequestToDeny")));
				return true;
			}
			if (IsPlayerInvitedByPlayer_tpa(target, me))
			{
				me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("RequestDenied")));
				((Player)target).sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("RequestDeniedTeleport").replaceAll("/target/", me.getDisplayName())));
	
				DeletetpaRequest(target, me);
				return true;
			}
			else if (IsPlayerInvitedByPlayer_tphere(target, me))
			{
				me.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("RequestDenied")));
				((Player)target).sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("RequestDeniedTeleport").replaceAll("/target/", me.getDisplayName())));
				DeletetpahereRequest(target, me);
				return true;
			}
			return true;
	  	}
	  	return true;
  	}
}
