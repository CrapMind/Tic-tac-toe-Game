package org.example.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Game;

@WebServlet
public class InitServlet extends HttpServlet {

    @Override
    public void init() {
        Game game = new Game();
        game.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }


}
