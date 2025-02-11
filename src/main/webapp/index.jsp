<%@ page import="org.example.service.GameService" %>
<%@ page import="org.example.model.util.Value" %>

<%
    GameService gameService = (GameService) application.getAttribute("game-service");
    boolean isGameFinished = gameService != null && gameService.isFinish();
    boolean isDraw = isGameFinished && gameService.getWinnerValue().equals(Value.EMPTY);
    String winner = "";
    if (isGameFinished) {
        winner = isDraw ? "DRAW!" : gameService.getWinnerValue().toString();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tic Tac Toe</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="icon" type="image/x-icon" href="favicon.ico">
    <link href="css/main.css" rel="stylesheet">
</head>
<body>

<p id="winner">
    <%= isGameFinished ? "Winner is: " + winner : "" %>
</p>

<table id="game">
    <tr>
        <td class="cell" id="0"></td>
        <td class="cell" id="1"></td>
        <td class="cell" id="2"></td>
    </tr>
    <tr>
        <td class="cell" id="3"></td>
        <td class="cell" id="4"></td>
        <td class="cell" id="5"></td>
    </tr>
    <tr>
        <td class="cell" id="6"></td>
        <td class="cell" id="7"></td>
        <td class="cell" id="8"></td>
    </tr>
</table>

<script>
    const BASE_URL = "http://localhost:8080";
    let cross = true;
    let gameOver = <%= isGameFinished %>;
    let isDraw = <%= isDraw %>;

    $(document).ready(function () {
        const CELLS = document.querySelectorAll('.cell');

        CELLS.forEach(cell => {
            cell.addEventListener('click', () => {
                if (!cell.textContent && !gameOver) {
                    cell.textContent = cross ? 'X' : 'O';
                    move(cell);
                }
            });
        });
        if (gameOver) {
            setTimeout(winAnimation, 500);
        }
    });

    function move(cell) {
        cell.value = cross ? 'X' : 'O';
        cross = !cross;

        $.ajax({
            type: "POST",
            url: BASE_URL + `/rest/move`,
            contentType: "application/json",
            data: JSON.stringify({coordinates: cell.id, value: cell.value})
        }).then(res => {
            if (res === "end") {
                gameOver = true;
                location.reload();
            }
        });
    }

    function winAnimation() {
        const CELLS = document.querySelectorAll('.cell');

        if (isDraw) {
            CELLS.forEach(cell => {
                cell.innerHTML = '<img src="images/draw.jpg" alt="Draw" width="100" height="100">';
            });
        } else {
            CELLS.forEach(cell => {
                cell.textContent = "<%= winner %>";
            });
        }

        CELLS.forEach(cell => {
            setTimeout(() => {
                cell.classList.add("animate");
            }, Math.random() * 1000);
        });
    }
</script>

</body>
</html>
