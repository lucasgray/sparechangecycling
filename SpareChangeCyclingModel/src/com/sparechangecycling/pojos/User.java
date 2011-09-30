package com.sparechangecycling.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="username",unique=true)
	private String username;
	
	@Column(name="email",unique=true)
	private String email;
	
	@Column(name="pass")
	private String pass;
	
	@Column(name="salt")
	private byte[] salt;
	
	@Column(name="is_active")
	private boolean isActive;
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@OneToMany(targetEntity=SccBikeAd.class,fetch=FetchType.LAZY,mappedBy="user")
	private List<SccBikeAd> ads;
	
	@OneToMany(targetEntity=Message.class,fetch=FetchType.LAZY,mappedBy="recipient")
	private List<Message> receivedMessages;
	
	@OneToMany(targetEntity=Message.class,fetch=FetchType.LAZY,mappedBy="sender")
	private List<Message> sentMessages;
	
	@OneToOne(targetEntity=Subscription.class,fetch=FetchType.LAZY)
	@Cascade(value=CascadeType.ALL)
	@JoinColumn(name="subscription_id")
	private Subscription subscription;
	
	@OneToOne(targetEntity=Profile.class,fetch=FetchType.LAZY)
	@Cascade(value=CascadeType.ALL)
	@JoinColumn(name="profile_id")
	private Profile profile;
	
	@OneToOne(targetEntity=ZipCode.class,fetch=FetchType.LAZY)
	@JoinColumn(name="zip")
	private ZipCode zip;
	
	public User() {
		super();
	}
	public User(String username,String email,String pass) {
		this.email = email;
		this.username = username;
		this.pass = pass;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String passHash) {
		this.pass = passHash;
	}
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<SccBikeAd> getAds() {
		return ads;
	}
	public void setAds(List<SccBikeAd> ads) {
		this.ads = ads;
	}
	public ZipCode getZip() {
		return zip;
	}
	public void setZip(ZipCode zip) {
		this.zip = zip;
	}
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}
	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}
	public List<Message> getSentMessages() {
		return sentMessages;
	}
	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public void addOneAd(SccBikeAd ad) {
		if (ads == null) {
			ads = new ArrayList<SccBikeAd>();
		}
		ads.add(ad);
	}
}
