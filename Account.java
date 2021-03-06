import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;

public class Account implements Serializable, Comparable<Account> {
    private String name;
    private String password;
    private String userId;
    private boolean passwordReset;
    private Set<Account> friends = new TreeSet<Account>();
    private Set<Account> ignoredFriends = new TreeSet<Account>();
    private int postsAtLastSync;


    
    public Account(String userId, String password) {
        this.userId   = userId;
        this.password = password;
    }

    public Account(String userId, String password, String name) {
        this(userId, password);
        this.name = name;
    }

    public Account safeCopy() {
        return new Account(userId,null,name);    //Gömmer lösenordet
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void addFriend(Account a) {
        this.friends.add(a);
    }

    public void removeFriend(Account a) {
        this.friends.remove(a);
    }

    public void ignoreFriend(Account a) {
        if (this.isFriendsWith(a)) this.ignoredFriends.add(a);
    }

    public void unIgnoreFriend(Account a) {
        if (this.isFriendsWith(a)) this.ignoredFriends.remove(a);
    }

    public boolean isFriendsWith(Account a) {
        return this.friends.contains(a);
    }

    public boolean isCurrentlyIgnoring(Account a) {
        return this.ignoredFriends.contains(a);
    }

    public int compareTo(Account a) {
        return a.userId.compareTo(this.userId);
    }

    public boolean hasFriends() {
        return this.friends.size() > 0;
    }
 
    //checks whether there are any ignored friends.
    public boolean hasIgnoredFriends() {
        return this.ignoredFriends.size() > 0;
    }

    public Account[] getFriends() {
        return (Account[]) this.friends.toArray(new Account[0]);
    }

    //returns a list of all the ignored friends
    public Account[] getIgnoredFriends() {
        return (Account[]) this.ignoredFriends.toArray(new Account[0]);
    }

    public boolean equals(Object o) {
        if (o instanceof Account) {
            return ((Account) o).userId.equals(this.userId);
        } else {
            return false;
        }
    }

    public int getPostAtLastSync() {
        return this.postsAtLastSync;
    }

    public void setPostAtLastSync(int posts) {
        this.postsAtLastSync = posts;
    }

    public void updateFriends(Set<Account> accounts)
    {
        for (Account a : accounts)
            {
            if (this.friends.contains(a))
                {
                this.friends.remove(a);
                this.friends.add(a);
                }
            if (this.ignoredFriends.contains(a))
                {
                this.friends.remove(a);
                this.friends.add(a);
                }
            }
    }
        // Returns the name for a friend of the user. 
    public String getFriendsName(Account a)
    {
        for (Account friend : this.friends)
            {
                if (friend.equals(a))
                    {
                        return friend.getName();
                    }
            }
        return "That is not a friend";
    }

}
