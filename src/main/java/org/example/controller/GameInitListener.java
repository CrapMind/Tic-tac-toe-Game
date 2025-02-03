package org.example.controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.model.Game;


@WebListener
public class GameInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Game game = Game.getInstance();
        game.init();
        event.getServletContext().setAttribute("game", game);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("game");
    }
}
