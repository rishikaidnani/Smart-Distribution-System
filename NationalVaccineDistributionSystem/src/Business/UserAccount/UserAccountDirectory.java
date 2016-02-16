/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccount;

import Business.Person.Person;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class UserAccountDirectory {

    private ArrayList<UserAccount> userAccountList;

    public UserAccountDirectory() {
        this.userAccountList = new ArrayList<>();
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(ArrayList<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public UserAccount createAndAddUserAccount(String userName, String password, Role role, Person person) {

        for (UserAccount userAccount : userAccountList) {
            if (userAccount.getPerson().getName().equals(person.getName())) {
                return null;
            }
        }

        UserAccount userAccount = new UserAccount();
        userAccountList.add(userAccount);

        userAccount.setUserName(userName);
        userAccount.setPassword(password);
        userAccount.setRole(role);
        userAccount.setPerson(person);

        return userAccount;
    }

    public void removeUserAccount(UserAccount userAccount) {
        userAccountList.remove(userAccount);
    }

    public Boolean checkUserAccountAvailability(String userName) {
        if (userName.equals("sysadmin")) {
            return true;
        }

        for (UserAccount userAccount : userAccountList) {
            if (userAccount.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public UserAccount authenticateUserAccount(String userName, String password) {
        for (UserAccount userAccount : userAccountList) {
            if (userAccount.getUserName().equals(userName)
                    && userAccount.getPassword().equals(password)) {
                return userAccount;
            }
        }
        return null;
    }

}
