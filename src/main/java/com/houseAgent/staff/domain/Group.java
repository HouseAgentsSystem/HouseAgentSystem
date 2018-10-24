package com.houseAgent.staff.domain;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the ACT_ID_GROUP database table.
 */
@Entity
@Table(name = "ACT_ID_GROUP")
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Integer rev;
    private String name;//用户组描述信息
    private String type;
	private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户
	
    private List<Staff> actIdUsers;
    //角色 -- 权限关系： 多对多关系;
  	
  	private List<SysPermission> permissions;
    public Group() {
    }

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(name = "ID_")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "REV_")
    public Integer getRev() {
        return this.rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    @Column(name = "NAME_")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "TYPE_")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
    //bi-directional many-to-many association to User
//    @ManyToMany(mappedBy = "actIdGroups")
    @ManyToMany
    @JoinTable(name = "ACT_ID_MEMBERSHIP", joinColumns = {@JoinColumn(name = "GROUP_ID_")}, inverseJoinColumns = {@JoinColumn(name = "USER_ID_")})
    public List<Staff> getActIdUsers() {
        return this.actIdUsers;
    }

    public void setActIdUsers(List<Staff> actIdUsers) {
        this.actIdUsers = actIdUsers;
    }
    @ManyToMany(fetch=FetchType.EAGER)
  	@JoinTable(name="SysGroupPermission",joinColumns={@JoinColumn(name="groupId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
	public List<SysPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}
}