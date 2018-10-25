package com.houseAgent;

import java.util.Arrays;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
//import org.activiti.engine.identity.Group;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.houseAgent.staff.domain.Group;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.staff.repository.GroupRepository;
import com.houseAgent.staff.repository.StaffRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHouseProjectApplicationTests {

	@Autowired
    protected IdentityService identityService;
	@Autowired  
	private RepositoryService repositoryService; 
	@Autowired  
	private RuntimeService runtimeService;  
	@Autowired  
	private TaskService taskService;  
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private GroupRepository groupRepository;
	@Test
	public void contextLoads() {
	}
	/**
	 * 添加用户，角色测试
	 */
	
	@Test
	public void testAddMyUser() {
		String imageResource = "";
		String userId1 = "admin1";
		String firstName1 = "Zhang";
		String password1 = "admin1";
		String salt1 = new SecureRandomNumberGenerator().nextBytes(32).toHex();
        String email1 = "513609643@qq.com";
        String phoneNumber1 = "13538367525";
        String position = "管理员";
        String realName = "小王";
        List<String> groups1 = Arrays.asList("admin");
        
        String userId2 = "manager1";
		String firstName2 = "Zhang";
		String password2 = "manager1";
		String salt2 = new SecureRandomNumberGenerator().nextBytes(32).toHex();
        String email2 = "513609643@qq.com";
        String phoneNumber2 = "13538367525";
        List<String> groups2 = Arrays.asList("manager");
        
        String userId3 = "staff3";
		String firstName3 = "Zhang";
		String password3 = "staff3";
		String salt3 = new SecureRandomNumberGenerator().nextBytes(32).toHex();
        String email3 = "513609643@qq.com";
        String phoneNumber3 = "13538367525";
        List<String> groups3 = Arrays.asList("staff");
        
		Staff staff1 = new Staff();
		Staff staff2 = new Staff();
		Staff staff3 = new Staff();
		staff1.setId(userId1);
		staff1.setFirst(firstName1);
		staff1.setEmail(email1);
		staff1.setPhoneNumber(phoneNumber1);
		staff1.setSalt(salt1);
		staff1.setPosition(position);
		staff1.setRealName(realName);
		
		staff2.setId(userId2);
		staff2.setFirst(firstName2);
		staff2.setEmail(email2);
		staff2.setPhoneNumber(phoneNumber2);
		staff2.setSalt(salt2);
		
		staff3.setId(userId3);
		staff3.setFirst(firstName3);
		staff3.setEmail(email3);
		staff3.setPhoneNumber(phoneNumber3);
		staff3.setSalt(salt3);
		
		String password1R = new SimpleHash("MD5", password1, staff1.getCredentialsSalt(), 2).toString();
		String password2R = new SimpleHash("MD5", password2, staff2.getCredentialsSalt(), 2).toString();
		String password3R = new SimpleHash("MD5", password3, staff3.getCredentialsSalt(), 2).toString();
		System.out.println(password1R);
		System.out.println(password2R);
		System.out.println(password3R);
		
		staff1.setPassword(password1R);
		staff2.setPassword(password2R);
		staff3.setPassword(password3R);
        staffRepository.save(staff1);
        staffRepository.save(staff2);
        staffRepository.save(staff3);
        if (groups1 != null) {
            for (String group : groups1) {
                identityService.createMembership(userId1, group);
            }
        }
        if (groups2 != null) {
            for (String group : groups2) {
                identityService.createMembership(userId2, group);
            }
        }
        if (groups3 != null) {
            for (String group : groups3) {
                identityService.createMembership(userId3, group);
            }
        }
		System.out.println("添加完成-------------");
		
	}
	
//	@Test
//	public void testAddActivitiUser() {
//		String userId = "admin15";
//		String firstName = "Henry";
//		String lastName = "Yan";
//		String password = "55555555";
//        String email = "henry.yan@kafeitu.me";
//        String phoneNumber = "13538367525";
//        String imageResource = "";
//        List<String> groups = Arrays.asList("admin");
//		
//		if (identityService.createUserQuery().userId(userId).count() == 0) {
//
//            // Following data can already be set by demo setup script
//			System.out.println("1111111");
//            User user = identityService.newUser(userId);
//			user.setId(userId);
////			user.setFirst(firstName);
//            user.setFirstName(firstName);
////			user.setLast(lastName);
//            user.setLastName(lastName);
//            user.setPassword(password);
//            user.setEmail(email);
//            identityService.saveUser(user);
//
////            if (groups != null) {
////                for (String group : groups) {
////                    identityService.createMembership(userId, group);
////                }
////            }
//        }
//		System.out.println("3333333333");
//		System.out.println("ooooooooooooooooo!!!!!!!!!");
//	}
	
//	@Test
//	public void testFindUser() {
//		Iterable<User> users = userRepository.findAll();
//		for (User user : users) {
//			System.out.println(user);
//		}
//	}
	
//	@Test
//	public void testAddUser() {
//		User user1 = new User();
//		user1.setPhoneNumber("13538367525");
//		user1.setFirst("zhang");
//		
//		System.out.println("222222222");
//		System.out.println("ooooooooooooooooo!!!!!!!!!");
//		userRepository.save(user1);
//	}
	
	
	@Test
	public void testAddGroup() {
//		String groupId = "admin";
//		String type = "security-role";
//		if (identityService.createGroupQuery().groupId(groupId).count() == 0) {
//            Group newGroup = identityService.newGroup(groupId);
//            newGroup.setName(groupId.substring(0, 1).toUpperCase() + groupId.substring(1));
//            newGroup.setType(type);
//            identityService.saveGroup(newGroup);
//        }
//		System.out.println("ooooooooooooooooo!!!!!!!!!");
		Group group1 = new Group();
		Group group2 = new Group();
		Group group3 = new Group();
		group1.setId("admin");
		group1.setName("管理员");
		group1.setType("security-role");
		group1.setAvailable(true);
		group2.setId("manager");
		group2.setName("经理");
		group2.setAvailable(true);
		group3.setId("staff");
		group3.setName("员工");
		group3.setAvailable(true);
		groupRepository.save(group1);
		groupRepository.save(group2);
		groupRepository.save(group3);
//		admin管理员，manager经理，staff员工
		
	}
}
