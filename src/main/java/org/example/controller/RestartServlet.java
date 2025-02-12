package org.example.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Game;
import org.example.service.GameServiceImpl;


@WebServlet(value = "/rest/restart")
public class RestartServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        request.getServletContext().removeAttribute("game-service");
        request.getServletContext().removeAttribute("game");
        Game game = Game.getInstance();
        game.init();
        request.getServletContext().setAttribute("game", game);
        request.getServletContext().setAttribute("game-service", new GameServiceImpl());

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
