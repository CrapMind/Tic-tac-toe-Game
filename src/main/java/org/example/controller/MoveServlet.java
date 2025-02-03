package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.CellDTO;
import org.example.model.Game;
import org.example.model.util.Value;
import org.example.service.GameService;
import org.example.service.GameServiceImpl;

import java.io.IOException;

@WebServlet(value = "/rest/move")
public class MoveServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    GameService gameService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            CellDTO cellDTO = objectMapper.readValue(request.getReader(), CellDTO.class);

            if (cellDTO.getCoordinates() == null || cellDTO.getValue() == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Missing parameters\"}");
                return;
            }

            int[] coordinates = coordinates(cellDTO.getCoordinates());
            if (coordinates == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Invalid coordinates\"}");
                return;
            }

            Value cellValue = Value.valueOf(cellDTO.getValue());
            Game game = (Game) getServletContext().getAttribute("game");

            if (game == null) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\": \"Game not initialized\"}");
                return;
            }

            gameService = new GameServiceImpl(game);
            gameService.move(coordinates[0], coordinates[1], cellValue);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\": \"success\"}");
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid value parameter\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Unexpected server error\"}");
        }
    }

    private int[] coordinates (String value) {
        return switch (value) {
            case "0" -> new int[]{0, 0};
            case "1" -> new int[]{0, 1};
            case "2" -> new int[]{0, 2};
            case "3" -> new int[]{1, 0};
            case "4" -> new int[]{1, 1};
            case "5" -> new int[]{1, 2};
            case "6" -> new int[]{2, 0};
            case "7" -> new int[]{2, 1};
            case "8" -> new int[]{2, 2};
            default -> null;
        };
    }
}
