package classes;

import org.bukkit.entity.Player;

public class tpaData {
	private Player myself;
	private Player targetparty;
	public tpaData(Player me, Player target)
	{
		setMyself(me);
		setTargetparty(target);
	}
	public Player getTargetparty() {
		return targetparty;
	}
	public void setTargetparty(Player targetparty) {
		this.targetparty = targetparty;
	}
	public Player getMyself() {
		return myself;
	}
	public void setMyself(Player myself) {
		this.myself = myself;
	}

}
