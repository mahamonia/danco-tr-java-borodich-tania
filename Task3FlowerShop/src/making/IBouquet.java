package making;

import entity.Aster;
import entity.Chamomile;
import entity.Rose;

public interface IBouquet {
	
	public void addRose(Rose rose, int amount);
	public void addAster(Aster aster, int amount);
	public void addChamomile(Chamomile chamomile, int amount);
	

}
