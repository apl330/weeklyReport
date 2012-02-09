package com.dayatang.weekly.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.dayatang.domain.AbstractEntity;
import com.dayatang.domain.QuerySettings;

@Entity
@Table(name = "weekly_users")
public class User extends AbstractEntity implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = -5139547379510674278L;

	private static final String DEFAULT_PASSWORD = "123456";

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	private String password;

	private String passwordHint;
	
	private String firstName; 
	
	private String lastName; 
	
	private String email; 
	
	private String phoneNumber;
	
	private String website;
	
	@Embedded
	private Address address = new Address();

	@Column(name = "account_non_expired")
	private boolean accountNonExpired = true;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked = true;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired = true;

	private boolean enabled = true;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role_name")
	private Set<Role> roles = new HashSet<Role>();

	User() {
	}

	public User(String username) {
		this.username = username;
		password = DEFAULT_PASSWORD;
	}

	public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Set<Role> roles) {

		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		}

		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.roles = Collections.unmodifiableSet(roles);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRoles(Role... roles) {
		for (Role role : roles) {
			this.roles.add(role);
		}
	}

	public void removeRoles(Role... roles) {
		for (Role role : roles) {
			this.roles.remove(role);
		}
	}

	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> results = new HashSet<GrantedAuthority>();
		for (Role role : roles) {
			results.add(new GrantedAuthorityImpl(role.name()));
		}
		return results;
	}

	public void eraseCredentials() {
		password = null;
	}

	public boolean equals(Object rhs) {
		if (rhs instanceof User) {
			return username.equals(((User) rhs).username);
		}
		return false;
	}

	public int hashCode() {
		return username.hashCode();
	}

	public String toString() {
		return username;
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public static User getByUsername(String username) {
		return getRepository().getSingleResult(QuerySettings.create(User.class).eq("username", username));
	}

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 * @return
	 */
	public static boolean judgeUserExistByUsername(String username) {
		if (getRepository().find(QuerySettings.create(User.class).eq("username", username)).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 登录验证
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static User loginChecking(String username, String password) {
		return getRepository().getSingleResult(QuerySettings.create(User.class).eq("username", username).eq("password", password));
	}

	public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
