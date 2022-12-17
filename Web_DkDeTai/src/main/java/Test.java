
import java.util.List;

import DAO.AccountDAO;
import model.Account;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountDAO accDAO=new AccountDAO();
		List<Account> acc=accDAO.findAll();
		for(Account i: acc) {
			System.out.println(i.getUsername());
		}
	}

}
