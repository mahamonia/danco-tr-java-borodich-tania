package com.roditeli.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "User")
public class User extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7137487987775494366L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private boolean status;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String url;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authentication_fk")
	@JsonManagedReference
	private Authentication authen;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_profile_fk")
	@JsonManagedReference
	private UserProfile profile;
	
	@OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
	@JsonBackReference
	private List<MessageUser> messageAuthorList;
	
	@OneToMany(mappedBy = "recipient",fetch = FetchType.LAZY)
	@JsonBackReference
	private List<MessageUser> messageRecipientList;
	
	
	@OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
	@JsonBackReference
	private List<MessageTheme> messageThemeList;
	
	@OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Event> eventAuthorList;
	
	@OneToMany(mappedBy = "recipient",fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Event> eventRecipientList;
	
	@OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
	@JsonBackReference
	private List<CommentEvent> commentList;
	
	// FRIEND
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="Friends",
                joinColumns={@JoinColumn(name="user_fk")},
                inverseJoinColumns={@JoinColumn(name="friend_fk")})
	@JsonBackReference
	private List<User> userList;
	
	@ManyToMany(mappedBy="userList", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<User> friendsList;
	
	// INVITES
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="Invites",
                joinColumns={@JoinColumn(name="user_fk")},
                inverseJoinColumns={@JoinColumn(name="friend_fk")})
	@JsonBackReference
	private List<User> userInvitesList;
	
	@ManyToMany(mappedBy="userInvitesList", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<User> friendsInvitesList;

	public User() {
	}

	public User(boolean status, Date date, String url, Authentication authen,
			UserProfile profile) {
		this.status = status;
		this.date = date;
		this.url = url;
		this.authen = authen;
		this.profile = profile;
		
		messageAuthorList = new ArrayList<MessageUser>();
		messageRecipientList = new ArrayList<MessageUser>();
		messageThemeList = new ArrayList<MessageTheme>();
		eventAuthorList = new ArrayList<Event>();
		eventRecipientList = new ArrayList<Event>();
		commentList = new ArrayList<CommentEvent>();
		userList = new ArrayList<User>();
		friendsList = new ArrayList<User>();
		userInvitesList = new ArrayList<User>();
		friendsInvitesList = new ArrayList<User>();
		
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Authentication getAuthen() {
		return authen;
	}

	public void setAuthen(Authentication authen) {
		this.authen = authen;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	public List<MessageUser> getMessageAuthorList() {
		return messageAuthorList;
	}

	public void setMessageAuthorList(List<MessageUser> messageAuthorList) {
		this.messageAuthorList = messageAuthorList;
	}

	public List<MessageUser> getMessageRecipientList() {
		return messageRecipientList;
	}

	public void setMessageRecipientList(List<MessageUser> messageRecipientList) {
		this.messageRecipientList = messageRecipientList;
	}

	public List<MessageTheme> getMessageThemeList() {
		return messageThemeList;
	}

	public void setMessageThemeList(List<MessageTheme> messageThemeList) {
		this.messageThemeList = messageThemeList;
	}

	public List<Event> getEventAuthorList() {
		return eventAuthorList;
	}

	public void setEventAuthorList(List<Event> eventAuthorList) {
		this.eventAuthorList = eventAuthorList;
	}

	public List<Event> getEventRecipientList() {
		return eventRecipientList;
	}

	public void setEventRecipientList(List<Event> eventRecipientList) {
		this.eventRecipientList = eventRecipientList;
	}

	public List<CommentEvent> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentEvent> commentList) {
		this.commentList = commentList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(List<User> friendsList) {
		this.friendsList = friendsList;
	}

	public List<User> getUserInvitesList() {
		return userInvitesList;
	}

	public void setUserInvitesList(List<User> userInvitesList) {
		this.userInvitesList = userInvitesList;
	}

	public List<User> getFriendsInvitesList() {
		return friendsInvitesList;
	}

	public void setFriendsInvitesList(List<User> friendsInvitesList) {
		this.friendsInvitesList = friendsInvitesList;
	}

}
