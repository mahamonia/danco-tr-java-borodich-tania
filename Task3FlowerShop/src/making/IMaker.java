package making;
import entity.Aster;
import entity.Chamomile;
import entity.Rose;

public interface IMaker {
	
	public Bouquet makeBouquet(Rose rose, Aster aster, Chamomile chamomile);

}
