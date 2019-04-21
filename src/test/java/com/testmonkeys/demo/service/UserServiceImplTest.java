package com.testmonkeys.demo.service;

import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.Project;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.entity.UserProject;
import com.testmonkeys.demo.enums.PositionEnum;
import com.testmonkeys.demo.mapper.UserMapper;
import com.testmonkeys.demo.repo.ProjectRepository;
import com.testmonkeys.demo.repo.UserRepository;
import com.testmonkeys.demo.service.factory.UserTestFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private UserMapper userMapper; //TODO

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void findAllUsersTest() {
        final ArrayList<User> testList = new ArrayList<>();
        testList.add(new User());
        when(userRepository.findAll()).thenReturn(testList);
        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void getOneTest() {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(new User().setId(3L)));
        assertEquals(3L, (long) userService.getOne(3L).getId());
    }

    @Test
    public void createUserTest__EmailInvalid() {
        final UserDTO userDTO = new UserDTO()
                .setFirstname("Ostap")
                .setPosition("Middle Java developer")
                .setEmail("badformed35@em@il.c435om");
        exceptionRule.expect(IllegalArgumentException.class);
        userService.createUser(userDTO);
    }

    @Test
    public void createUserTest__EmailExists() {
        final UserDTO userDTO = new UserDTO()
                .setFirstname("Ostap")
                .setPosition("Middle Java developer")
                .setEmail("goodemail@gmail.com");
        exceptionRule.expect(InternalError.class); //May be custom exceptions
        when(userRepository.findByEmail(any())).thenReturn(new User());
        userService.createUser(userDTO);
    }

    @Test
    public void findAllBEMiddleDevs() {
        final List<User> testUsersList = Arrays.asList(
                new User()
                        .setId(1L).setFirstname("User1")
                        .setPosition(PositionEnum.findByPosition("Middle Java developer").getValue())
                        .setActivated(true),
                new User()
                        .setId(2L).setFirstname("User2")
                        .setPosition(PositionEnum.findByPosition("Middle Java developer").getValue())
                        .setActivated(false),
                new User()
                        .setFirstname("Chicken")
                        .setId(3L).setFirstname("User3")
                        .setActivated(false),
                new User()
                        .setId(4L).setFirstname("Bandit")
                        .setPosition(PositionEnum.findByPosition("Junior QA Manual").getValue())
                        .setActivated(false),
                new User()
                        .setId(5L).setFirstname("User5")
                        .setPosition(PositionEnum.findByPosition("Middle Front-end developer").getValue())
                        .setActivated(true),
                null,
                new User()
                        .setId(6L).setFirstname("User6")
                        .setPosition(PositionEnum.findByPosition("Middle Java developer").getValue())
                        .setActivated(true));
        when(userRepository.findAll()).thenReturn(testUsersList);
        final List<UserDTO> users = userService.findAllActivatedBEMiddleDevs();
        assertNotNull(users);
//        for (final UserDTO userDTO : users) {
//            assertNotNull(userDTO.getId());
//            assertNotNull(userDTO.getFirstname());
//            assertEquals(userDTO.getPosition(), "Middle Java developer");
//        }
        assertEquals(2, users.size());

    }

//
//    @Test
//    public void check(){
//        AccountDTO accountDTO = AccountDTOTestFactory.getRandomActiveUser();
//        userService.validateUser(accountDTO);
//    }


    @Test
    public void getNullUserProjectTest(){
        User user = UserTestFactory.getRandomActiveUser();
        Long projectId = 12l;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());
        UserProject userProject = userService.getUserProject(user, projectId);
        Assert.assertNull(userProject);

    }


    @Test
    public void getUserProjectTest(){
        User user = UserTestFactory.getRandomActiveUser();
        Long projectId = 1L;
        Project project = new Project();
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        UserProject userProject = userService.getUserProject(user, projectId);
        Assert.assertEquals(userProject.getUser(), user);
        Assert.assertEquals(userProject.getProject(), project);
        Assert.assertTrue(userProject.getStartWork().isAfter(LocalDateTime.now().minusSeconds(40)));
        Assert.assertTrue(userProject.getStartWork().isBefore(LocalDateTime.now()));
    }


    @Test
    public void setUserProjectTest(){
        User user = UserTestFactory.getRandomActiveUser();

        List<Long> projectId = Arrays.asList(1L, 2L, 3L, 4L);

        Project project1 = new Project();
        project1.setId(1L);
        Project project2 = new Project();
        project2.setId(2L);
        Project project3 = new Project();
        project3.setId(3L);
        Project project4 = new Project();
        project4.setId(4L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project1));
        when(projectRepository.findById(2L)).thenReturn(Optional.of(project2));
        when(projectRepository.findById(3L)).thenReturn(Optional.of(project3));
        when(projectRepository.findById(4L)).thenReturn(Optional.of(project4));

        userService.setProjectsToUser(user, projectId);
        Assert.assertNotNull(user.getProjectsForUser());


        Assert.assertEquals(user.getProjectsForUser().size(), 4);


    }







}
