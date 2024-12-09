package Pojos;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a user with an ID, email, password, and an associated role.
 */
public class User {
	private Integer id;
	private String email;
	private byte[] password;
	private Role role;

	/**
	 * Constructs a User with the specified email, password, and role.
	 *
	 * @param email    the email of the user.
	 * @param password the password of the user as a byte array.
	 * @param role     the role assigned to the user.
	 */
	public User(String email, byte[] password, Role role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	/**
	 * Gets the unique identifier of the user.
	 *
	 * @return the user ID.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the user.
	 *
	 * @param id the user ID.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the email of the user.
	 *
	 * @return the email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 *
	 * @param email the new email of the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password of the user as a byte array.
	 *
	 * @return the password of the user.
	 */
	public byte[] getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password the new password of the user as a byte array.
	 */
	public void setPassword(byte[] password) {
		this.password = password;
	}

	/**
	 * Gets the role assigned to the user.
	 *
	 * @return the user's role.
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Sets the role assigned to the user.
	 *
	 * @param role the new role for the user.
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Computes the hash code for the User object based on its fields.
	 *
	 * @return the hash code value for this User.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + Objects.hash(email, id, role);
		return result;
	}

	/**
	 * Compares this User object to another object for equality.
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
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Arrays.equals(password, other.password) && Objects.equals(role, other.role);
	}

	/**
	 * Returns a string representation of the User object.
	 *
	 * @return a string containing the user's ID, email, password, and role.
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + Arrays.toString(password) + ", role=" + role
				+ "]";
	}
}

