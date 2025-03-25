package bankapp;

import java.util.ArrayList;
import java.util.List;

public class ChooseAccountToManage {

	 
	    private List<BankAccount> accounts;
	    
	    
	    public void addAccount(BankAccount account) {
	        accounts.add(account);
	    }
	    
	    public List<BankAccount> getAccounts() {
    
        return accounts;
    }
    
    public List<BankAccount> getAccountsByType(String type) {
    	
        List<BankAccount> filtered = new ArrayList<>();
        
        for(BankAccount account : accounts) {
            
        	if(type.equalsIgnoreCase("savings") && account instanceof SavingsAccount) {
                
        		filtered.add(account);
            
        	} else if(type.equalsIgnoreCase("checking") && account instanceof CheckingAccount) {
                
        		filtered.add(account);
            }
        }
        return filtered;
    }
    
   
    public BankAccount chooseAccount(int index, List<BankAccount> accountsList) {
        
    	if(index >= 0 && index < accountsList.size()) {
            
    		return accountsList.get(index);
        }
        
    	return null;
    }
}

