
import com.danco.api.IWorkingMenu;
import com.danco.dependency.DependencyInjection;
import com.danco.training.controller.workmenu.WorkingMenu;

public class Run {

	public static void main(String[] args) {
		
		IWorkingMenu menu = new WorkingMenu();				
		DependencyInjection.getInstance().getDI(menu);
		
		//start menu
		menu.workMenu();
		

	}

}
