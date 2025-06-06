package org.example.controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.model.Game;
import org.example.service.GameService;
import org.example.service.GameServiceImpl;


@WebListener
public class GameInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Game game = Game.getInstance();
        game.init();
        GameService gameService = new GameServiceImpl();
        event.getServletContext().setAttribute("game", game);
        event.getServletContext().setAttribute("game-service", gameService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("game");
        sce.getServletContext().removeAttribute("game-service");
    }
}
