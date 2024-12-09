package Pojos;

import java.util.List;
import java.util.Objects;

/**
 * Represents a role with a name, an optional ID, and a list of associated users.
 */
public class Role {

    private Integer id;
    private String name;
    private List<User> users;

    /**
     * Constructs a Role with the specified name.
     *
     * @param name the name of the role.
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier of the role.
     *
     * @return the role ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the role.
     *
     * @param id the role ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     *
     * @return the role name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     *
     * @param name the new role name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of users associated with the role.
     *
     * @return the list of users.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the list of users associated with the role.
     *
     * @param users the list of users.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Computes the hash code for the Role object based on its fields.
     *
     * @return the hash code value for this Role.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, users);
    }

    /**
     * Compares this Role object to another object for equality.
     *
     * @param obj the object to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(users, other.users);
    }

    /**
     * Returns the string representation of the Role object.
     *
     * @return the name of the role as its string representation.
     */
    @Override
    public String toString() {
        return this.name;
    }
}
