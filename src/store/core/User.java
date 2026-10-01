
package store.core;

/**
 * Abstract class representing a user in the store system.
 */
public abstract class User {
    private final String username;
    private final String email;

    /**
     * Constructor to initialize a User with username and email.
     *
     * @param username the username of the user
     * @param email the email of the user
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    /**
     * toString method to represent the User object as a string.
     *
     * @return the string representation of the User object
     */
    @Override
    public String toString(){
        return "Username: " + username + "\nEmail: " + email;
    }

    /**
     * Equals method to compare two User objects based on username.
     *
     * @param o the object to compare with
     * @return true if both users have the same username, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof User))
            return false;
        User u = (User) o;
        return this.username.equals(u.username);
    }

    /**
     * Gets the username of the user.
     *
     *
     * @return the username of the user
     */
    public String getUsername(){
        return username;
    }

}
