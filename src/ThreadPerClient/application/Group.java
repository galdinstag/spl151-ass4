package ThreadPerClient.application;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by gal on 1/14/2015.
 */
public class Group {

    private final String _groupName;
    private ArrayList<User> _usersList;

    public Group(String groupName){
        _groupName = groupName;
        _usersList = null;
    }

    public String getGroupName(){ return _groupName; }

    public void addUsersList(ArrayList<User> users) {
        _usersList = users;
    }

    public boolean containUser(String userName) {
        synchronized (_usersList) {
            boolean isExist = false;
            for(User currUser : _usersList){
                if(currUser.getName().equals(userName)){
                    isExist = true;
                }
            }
            return isExist;
        }
    }

    public void addUser(User user) {
        synchronized (_usersList) {
            _usersList.add(user);
        }
    }

    public void removeUser(User userToRemove) {
        synchronized (_usersList) {
            _usersList.remove(userToRemove);
        }
    }

    public void addMessage(String content) {
        synchronized (_usersList) {
            Iterator<User> it = _usersList.iterator();
            while (it.hasNext()) {
                User currUser = it.next();
                currUser.addMessage(content, _groupName);
            }
        }
    }
    public String getUsersPhone(){
        StringBuilder sb = new StringBuilder();
        synchronized (_usersList) {
            for (User currUser : _usersList) {
                sb.append(currUser.getPhoneNumber());
                sb.append(",");
            }
            //trim last ","
            if(sb.length() > 0){
                sb.deleteCharAt(sb.length()-1);
            }
        }
        return new String(sb);
    }

    public String getUsersInformation() {
        StringBuilder sb = new StringBuilder();
        synchronized (_usersList) {
            for (User currUser : _usersList) {
                sb.append(currUser.getName());
                sb.append("@");
                sb.append(currUser.getPhoneNumber());
                sb.append("\n");
            }
        }
        return new String(sb);

    }
}
