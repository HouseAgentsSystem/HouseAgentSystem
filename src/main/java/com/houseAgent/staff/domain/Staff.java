package com.houseAgent.staff.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.houseAgent.store.domain.Store;

/**
 * The persistent class for the ACT_ID_USER database table.
 */
@Entity
@Table(name = "ACT_ID_USER")
public class Staff implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String email;
    private String phoneNumber;
    private Integer rev;
    private String first;
    private String last;
    private String password;
    private String realName;
    private String salt;//加密密码的盐
    private String sex;
    private String faceImg;
    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    private Store store;//门店
    private List<Group> actIdGroups;
    private String position;
    public Staff() {
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(name = "ID_")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "EMAIL_")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "PHONENUMBER_")
    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name = "REV_")
	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	@Column(name = "FIRST_")
    public String getFirst() {
        return this.first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Column(name = "LAST_")
    public String getLast() {
        return this.last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Column(name = "PWD_")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //bi-directional many-to-many association to Group
    @ManyToMany
    @JoinTable(name = "ACT_ID_MEMBERSHIP", joinColumns = {@JoinColumn(name = "USER_ID_")}, inverseJoinColumns = {@JoinColumn(name = "GROUP_ID_")})
    public List<Group> getActIdGroups() {
        return this.actIdGroups;
    }

    public void setActIdGroups(List<Group> actIdGroups) {
        this.actIdGroups = actIdGroups;
    }
    
    public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFaceImg() {
		return faceImg;
	}

	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH)//这里别改
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 密码盐.
	 * @return
	 */
	@Transient
	public String getCredentialsSalt(){
		return this.id+this.salt;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", rev=" + rev + ", first="
				+ first + ", last=" + last + ", password=" + password + ", realName=" + realName + ", salt=" + salt
				+ ", sex=" + sex + ", faceImg=" + faceImg + ", state=" + state + ", actIdGroups=" + actIdGroups + "]";
	}
	
}

