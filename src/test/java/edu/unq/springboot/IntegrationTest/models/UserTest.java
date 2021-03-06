package edu.unq.springboot.IntegrationTest.models;

import edu.unq.springboot.models.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void UsuarioGeneraUnLink() {
        User usuario = new User("nelson", "1234", "Nelson", "Gonzalez", "nelgonzalez88@gmail.com");
       usuario.generateLink();
        Assert.assertEquals(usuario.getLink(),"http://localhost:3000/repo/:nelson");
    }
    @Test
    void UserCreateHaveNotLink() {
        User usuario = new User("nelson", "1234", "Nelson", "Gonzalez", "nelgonzalez88@gmail.com");
        Assert.assertEquals(usuario.getLink(),null);
    }

    @Test
    void ModifyUserTitle() {
        User usuario = new User("nelson", "1234", "Nelson", "Gonzalez", "nelgonzalez88@gmail.com");
        usuario.modifyTitle("Nelson Gonzales Desarrollador Web");
        Assert.assertEquals(usuario.getTitle(),"Nelson Gonzales Desarrollador Web");
    }

}