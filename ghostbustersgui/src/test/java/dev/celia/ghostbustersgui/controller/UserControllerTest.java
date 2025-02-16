package dev.celia.ghostbustersgui.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import dev.celia.ghostbustersgui.model.GhostClass;
import dev.celia.ghostbustersgui.model.GhostModel;
import dev.celia.ghostbustersgui.model.UserModel;
import dev.celia.ghostbustersgui.view.HomeScreen;
import dev.celia.ghostbustersgui.view.MenuView;

public class UserControllerTest {
    @Mock
    private UserController userController;
    
    @Mock
    private UserModel userModelMock;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userModelMock = mock(UserModel.class);
        userController = new UserController(userModelMock);
    }

    @Test
    @DisplayName("Test para capturar un fantasma")
    void testCaptureGhost() {

        String name = "Casper";
        GhostClass ghostClass = GhostClass.CLASS_II;
        String dangerLevel = "Medio";
        String ability = "Invisibilidad";
        String captureDate = "15-02-2025";

        ArgumentCaptor<GhostModel> captor = ArgumentCaptor.forClass(GhostModel.class);

        userController.captureGhost(name, ghostClass, dangerLevel, ability, captureDate);

        verify(userModelMock).addGhost(captor.capture());

        GhostModel capturedGhost = captor.getValue();

        assertThat(capturedGhost, is(notNullValue()));
        assertThat(capturedGhost.getName(), is(name));
        assertThat(capturedGhost.getGhostClass(), is(ghostClass));
        assertThat(capturedGhost.getDangerLvl(), is(dangerLevel));
        assertThat(capturedGhost.getAbility(), is(ability));
        assertThat(capturedGhost.getCaptureDate(), is(captureDate));
    }

    @Test
    @DisplayName("Test para verificar que se abre HomeScreen")
    void testOpenHomeScreen() {
        assertDoesNotThrow(() -> new HomeScreen());
    }

    @Test
    @DisplayName("Test para verificar que se abre MenuView")
    void testOpenMenuView() {
        assertDoesNotThrow(() -> new MenuView(userController));
    }
    @Test
    @DisplayName("Test para verificar que se abre CreateGhostView")
    void testOpenCreateGhostView() {
        assertDoesNotThrow(() -> userController.openCreateGhostView());
    }
}
