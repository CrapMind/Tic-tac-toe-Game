package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.controller.util.Coordinates;
import org.example.dto.CellDTO;
import org.example.model.Game;
import org.example.model.util.Value;
import org.example.service.GameService;

import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/rest/move")
public class MoveServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CellDTO cellDTO = mapper.readValue(request.getReader(), CellDTO.class);

            if (cellDTO.getCoordinates() == null || cellDTO.getValue() == null) {
                sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, Map.of("error", "Missing parameters"));
                return;
            }

            Value cellValue = Value.valueOf(cellDTO.getValue());
            Coordinates coordinates = Coordinates.getByNumber(cellDTO.getCoordinates());

            Game game = (Game) getServletContext().getAttribute("game");

            if (game == null) {
                sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Map.of("error", "Game not initialized"));
                return;
            }

            GameService gameService = (GameService) getServletContext().getAttribute("game-service");
            gameService.move(coordinates.getX(), coordinates.getY(), cellValue);

            if (!gameService.isFinish()) {
                sendResponse(response, HttpServletResponse.SC_OK, Map.of("value", "success"));
            } else {
                sendResponse(response, HttpServletResponse.SC_OK, Map.of("status", "end"));
            }

        } catch (IllegalArgumentException | NullPointerException e) {
            sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, Map.of("error", "Invalid value parameter"));
        } catch (Exception e) {
            sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Map.of("error", "Unexpected server error"));
        }
    }

    private void sendResponse(HttpServletResponse response, int status, Map<String, String> message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(status);
        response.getWriter().write(mapper.writeValueAsString(message));
    }
}
