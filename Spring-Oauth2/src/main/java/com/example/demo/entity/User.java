package com.example.demo.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = "socialAuth")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email")
})
@SecondaryTables({
	@SecondaryTable(name = "social_auth", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private long id;
	
	@Column(name = "email", length = 100, nullable = false)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "nickname", length = 100, nullable = false)
	private String nickname;
	
	@Column(name = "tel", length = 100)
	private String tel;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "regdt", nullable = false, updatable = false)
	private Date regdt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "moddt", nullable = false)
	private Date moddt;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "providerId", column = @Column(table = "social_auth", name = "provider_id")),
		@AttributeOverride(name = "provider", column = @Column(table = "social_auth", name = "provider")),
		@AttributeOverride(name = "email", column = @Column(table = "social_auth", name = "email", length = 100, nullable = false)),
		@AttributeOverride(name = "name", column = @Column(table = "social_auth", name = "name", length = 100, nullable = false)),
		@AttributeOverride(name = "imageUrl", column = @Column(table = "social_auth", name = "imageUrl", columnDefinition = "TEXT")),
		@AttributeOverride(name = "attributes", column = @Column(table = "social_auth", name = "attributes", columnDefinition = "TEXT")),
		@AttributeOverride(name = "ip", column = @Column(table = "social_auth", name = "ip", length = 100, nullable = false))
	})
	private SocialAuth socialAuth;
}
